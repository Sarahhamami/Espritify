package services;

import entities.Question;
import entities.Quizz;
import Utils.dbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizzServices implements IService<Quizz>{

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public QuizzServices() {
        this.conn = dbConnection.getInstance().getCnx();
    }


    @Override
    public boolean add(Quizz q) {
        String req = "insert into quizz(id_question,sujet,description) values (?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, q.getId_question().getId_question());
            pst.setString(2, q.getSujet());
            pst.setString(3, q.getDescription());
            int rows = pst.executeUpdate();
            if(rows > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    //@Override
    public void delete(Quizz q) {
        int id = q.getId_quizz();
        String req = "delete from quizz where id_quizz='" + id + "'";
        try {
            ste = conn.createStatement();
            int nbLigne = ste.executeUpdate(req);
            if (nbLigne == 0) {
                throw new IllegalArgumentException("Le quizz d'id " + id + " n'existe pas");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean update(Quizz q) {
        int id = q.getId_quizz();
        String sujet = q.getSujet();
        String desc = q.getDescription();
        String req = "UPDATE quizz SET sujet = ?, description = ? WHERE id_quizz = ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, sujet);
            pst.setString(2, desc);
            pst.setInt(3, id);
            int nbligne = pst.executeUpdate();
            if (nbligne == 0) {
                System.out.println("Aucune modification effectuée pour cette requête");
                return false;
            }else{
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Quizz> readAll() {
        String req = "SELECT q.*, qq.* FROM quizz q JOIN questions qq ON q.id_question = qq.id_question";
        List<Quizz> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                int id_quizz = rs.getInt("id_quizz");
                String sujet = rs.getString("sujet");
                String desc = rs.getString("description");
                Question question = new Question(rs.getInt("id_question"), rs.getString("contenu"),rs.getString("rep1"),rs.getString("rep2"),rs.getString("rep3"),rs.getString("bonneReponse"));
                list.add(new Quizz(id_quizz,question ,sujet, desc));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Quizz readById(int id) {
        String req = "SELECT q.*, qq.* FROM quizz q JOIN questions qq ON q.id_question = qq.id_question WHERE id_quizz = ?";
        Quizz quizz= null;
        try (PreparedStatement pst = conn.prepareStatement(req)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String sujet = rs.getString("sujet");
                    String description = rs.getString("description");
                    Question question = new Question(rs.getInt("id_question"), rs.getString("contenu"),rs.getString("rep1"),rs.getString("rep2"),rs.getString("rep3"),rs.getString("bonneReponse"));
                    quizz = new Quizz(id, question, sujet, description);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quizz;
    }
}
