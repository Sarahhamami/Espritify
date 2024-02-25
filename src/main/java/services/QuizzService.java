package services;

import entities.Question;
import entities.Quizz;
import entities.Reponse;
import utils.dbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizzService implements IService<Quizz> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public QuizzService() {
        this.conn = dbConnection.getInstance().getCnx();
    }

    @Override
    public boolean add(Quizz q) {
        String req = "insert into quizz(sujet,description,id_Question) values (?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, q.getSujet());
            pst.setString(2, q.getDescript());
            pst.setInt(3, q.getQuestion().getId_Que());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void delete(Quizz q) {
        int id = q.getId_quizz();
        String req = "delete from quizz where id_Quizz='" + id + "'";
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
    public void update(Quizz q) {
        int id = q.getId_quizz();
        String sujet = q.getSujet();
        String desc = q.getDescript();
        String req = "UPDATE quizz SET sujet = ?, description = ? WHERE id_Quizz = ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, sujet);
            pst.setString(2, desc);
            pst.setInt(3, id);
            int nbligne = pst.executeUpdate();
            if (nbligne == 0) {
                System.out.println("Aucune modification effectuée pour cette requête");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Quizz> readAll() {
        String req = "SELECT qz.id_Quizz, qz.sujet, qz.description, qz.id_question, q.*, r.* FROM quizz qz JOIN question q ON qz.id_question = q.id_Que JOIN reponse r ON q.reponse = r.id_rep";
        List<Quizz> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                int id_Quizz = rs.getInt("id_Quizz");
                String sujet = rs.getString("sujet");
                String desc = rs.getString("description");
                int id_Question = rs.getInt("id_Question");
                String contenu = rs.getString("contenu");
                Reponse reponse = new Reponse(rs.getInt("id_rep"), rs.getString("contenu"));
                String bonne_Rep = rs.getString("bonne_Rep");
                int num_Que = rs.getInt("num_Que");
                Question question = new Question(id_Question, contenu, reponse, bonne_Rep, num_Que);
                Quizz quizz = new Quizz(id_Quizz, sujet, desc, question);
                list.add(quizz);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public Quizz readById(int id) {
        String req = "SELECT qz.id_Quizz, qz.sujet, qz.description, qz.id_question, q.*, r.* FROM quizz qz JOIN question q ON qz.id_question = q.id_Que JOIN reponse r ON q.reponse = r.id_rep WHERE qz.id_Quizz = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String sujet = rs.getString("sujet");
                String desc = rs.getString("description");
                int id_Question = rs.getInt("id_Question");
                String contenu = rs.getString("contenu");
                Reponse reponse = new Reponse(rs.getInt("id_rep"), rs.getString("reponse"));
                String bonne_Rep = rs.getString("bonne_Rep");
                int num_Que = rs.getInt("num_Que");
                Question question = new Question(id_Question, contenu, reponse, bonne_Rep, num_Que);
                return new Quizz(id, sujet, desc, question);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}