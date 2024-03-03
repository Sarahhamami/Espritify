package service;

import entities.PasswordResetToken;
import entities.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;
import utils.DataSources;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService implements Iservice<Utilisateur> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public UtilisateurService() {
        conn = DataSources.getInstance().getCnx();
    }

    @Override
    public void add(Utilisateur utilisateur) {
        String hashedPassword = BCrypt.hashpw(utilisateur.getMdp(), BCrypt.gensalt());
        String requete = "insert into utilisateur (nom,prenom,email,mdp,tel,niveau,role) values ('" + utilisateur.getNom() + "','" + utilisateur.getPrenom() + "','" + utilisateur.getEmail() + "','" + hashedPassword + "'," + utilisateur.getTel() + ",'" + utilisateur.getNiveau() + "','" + utilisateur.getRole() + "')";


        try {
            ste = conn.createStatement();
            ste.execute(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Utilisateur utilisateur) {
        String requete = "DELETE FROM utilisateur WHERE id = " + utilisateur.getId() + "";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Utilisateur utilisateur) {
        String requete = "UPDATE utilisateur SET nom='" + utilisateur.getNom() + "',prenom='" + utilisateur.getPrenom() + "' where id= " + utilisateur.getId();
        try {
            ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void updateProfile(Utilisateur utilisateur, String email) throws SQLException {
        String query = "UPDATE utilisateur SET image = ?, nom = ?, prenom = ?, email = ? ,tel = ? WHERE email = ?";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, utilisateur.getImage());

        ps.setString(2, utilisateur.getNom());
        ps.setString(3, utilisateur.getPrenom());
        ps.setString(4, utilisateur.getEmail());
        ps.setInt(5, utilisateur.getTel());
        ps.setString(6, email);
        ps.executeUpdate();

    }

    @Override
    public List<Utilisateur> displayAll() {
        String requete = "select * from utilisateur";
        List<Utilisateur> list = new ArrayList<>();

        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Utilisateur displayByid(int id) {
        Utilisateur resultUser = null;
        String requete = "SELECT * FROM utilisateur WHERE ID = " + id;

        try {
            ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(requete);
            if (resultSet.next()) {
                resultUser = new Utilisateur(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"));
            } else {
                System.out.println("can't find reclamation");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return resultUser;
    }

    public int authenticate(String email, String password) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM utilisateur WHERE email = ? ");
            stmt.setString(1, email);
//            stmt.setString(2, password);


            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("mdp");
                if (BCrypt.checkpw(password, hashedPassword))

                    return rs.getInt("id");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String role(int id) {
        try {

            PreparedStatement stmt1 = conn.prepareStatement("SELECT role FROM utilisateur where id=?");
            stmt1.setInt(1, id);

            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                return rs.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "not found";
    }

    public void updateUserPassword(int userId, String newPassword) {

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE utilisateur SET mdp = ? WHERE id = ?");
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            stmt.setString(1, hashedPassword);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
//                    stmt.close();
                }
                if (conn != null) {
//                    conn.close();
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertPasswordResetToken(int userId, String token, long timestamp, String email) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO password_reset_token(token,user_id, timestamp,email) VALUES (?, ?, ?,?)");
            stmt.setInt(2, userId);
            stmt.setString(1, token);
            stmt.setTimestamp(3, new Timestamp(timestamp));
            stmt.setString(4, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // handle the exception as appropriate for your application
        }
    }

    public PasswordResetToken getPasswordResetToken(String token) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM password_reset_token WHERE token = ?");
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            PasswordResetToken passwordResetToken = null;

            if (resultSet.next()) {
                passwordResetToken = new PasswordResetToken();
                passwordResetToken.setToken(resultSet.getString("token"));
                passwordResetToken.setUserId(resultSet.getInt("user_id"));
                System.out.println(resultSet.getInt("user_id"));
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                passwordResetToken.setTimestamp(localDateTime);
                System.out.println(resultSet.getString("token"));
                return passwordResetToken;

            }
//            resultSet.close();
//            statement.close();
            return passwordResetToken;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Utilisateur getUserByEmail(String email) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                return new Utilisateur(resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("mdp"),
                        resultSet.getInt("id"),
                        resultSet.getInt("tel"),
                        resultSet.getString("niveau"),
                        resultSet.getString("image"),
                        resultSet.getString("role"));


            }
//            resultSet.close();
//            statement.close();
            return new Utilisateur();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserEmailByToken(String token) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT email FROM password_reset_token WHERE token = ?");
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                return resultSet.getString("email");

            }
//            resultSet.close();
//            statement.close();
            return "not found";
        } catch (SQLException e) {
            e.printStackTrace();
            return "sstem error";
        }
    }


    public void deleteToken(int id) {
        String requete = "DELETE FROM password_reset_token WHERE user_id = " + id + "";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int session(int id) {
        try {
            if (id != 0)
                return id;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean exsitemail(String email) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM utilisateur WHERE email = ? ");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
