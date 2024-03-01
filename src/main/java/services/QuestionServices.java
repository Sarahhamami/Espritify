package Services;

import Entities.Question;
import Utils.dbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionServices implements IService<Question>{

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public QuestionServices() {
        conn = dbConnection.getInstance().getCnx();
    }


    @Override
    public boolean add(Question q) {
        String req = "insert into questions(contenu,rep1,rep2,rep3,bonneReponse) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, q.getContenu());
            pst.setString(2, q.getReponse1());
            pst.setString(3, q.getReponse2());
            pst.setString(4, q.getReponse3());
            pst.setString(5, q.getBonneReponse());
            int rows=pst.executeUpdate();
            if(rows>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Question q) {
        int id = q.getId_question();
        String req = "delete from questions where id_question='" + id + "'";
        try {
            ste = conn.createStatement();
            int nbLigne = ste.executeUpdate(req);
            if (nbLigne == 0) {
                throw new IllegalArgumentException("La question d'id " + id + " n'existe pas");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean update(Question q) {
        int id = q.getId_question();
        String contenu = q.getContenu();
        String rep1 = q.getReponse1();
        String rep2 = q.getReponse2();
        String rep3 = q.getReponse3();
        String bon = q.getBonneReponse();

        String req = "UPDATE questions SET contenu = ?, rep1 = ?, rep2 = ?, rep3 = ?, bonneReponse = ? WHERE id_question = ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, contenu);
            pst.setString(2, rep1);
            pst.setString(3, rep2);
            pst.setString(4, rep3);
            pst.setString(5, bon);
            pst.setInt(6, id);
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
    public List<Question> readAll() {
        String req = "SELECT * FROM questions";
        List<Question> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                int id_Que = rs.getInt("id_question");
                String contenu = rs.getString("contenu");
                String rep1 = rs.getString("rep1");
                String rep2 = rs.getString("rep2");
                String rep3 = rs.getString("rep3");
                String bonne_rep = rs.getString("bonneReponse");
                list.add(new Question(id_Que, contenu, rep1,rep2,rep3,bonne_rep));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Question readById(int id) {
        String req = "SELECT * FROM questions WHERE id_question = ?";
        Question question= null;
        try (PreparedStatement pst = conn.prepareStatement(req)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int id_Que = rs.getInt("id_question");
                    String contenu = rs.getString("contenu");
                    String rep1 = rs.getString("rep1");
                    String rep2 = rs.getString("rep2");
                    String rep3 = rs.getString("rep3");
                    String bonne_rep = rs.getString("bonneReponse");
                    question = new Question(id_Que, contenu, rep1,rep2,rep3,bonne_rep);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return question;
    }
}
