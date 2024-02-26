package services;

import entities.Question;
import entities.Reponse;
import utils.dbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionServices implements IService<Question> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public QuestionServices() {
        conn = dbConnection.getInstance().getCnx();
    }

    @Override
    public boolean add(Question q) {
        String req = "insert into question(contenu,reponse,bonne_rep,num_Que) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, q.getContenu());
            pst.setInt(2, q.getReponse().getId_rep());
            pst.setString(3, q.isBonne_rep());
            pst.setInt(4, q.getNumQue());
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
        int id = q.getId_Que();
        String req = "delete from question where id_Que='" + id + "'";
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
        int id = q.getId_Que();
        String contenu = q.getContenu();
        String bon = q.isBonne_rep();
        int num = q.getNumQue();
        String req = "UPDATE question SET contenu = ?, bonne_Rep = ?, num_Que = ? WHERE id_Que = ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, contenu);
            pst.setString(2, bon);
            pst.setInt(3, num);
            pst.setInt(4, id);
            int nbligne = pst.executeUpdate();
            if (nbligne == 0) {
                System.out.println("Aucune modification effectuée pour cette requête");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }

    @Override
    public List<Question> readAll() {
        String req = "SELECT q.id_Que, q.contenu, q.reponse, q.bonne_rep, q.num_Que, r.* FROM question q JOIN reponse r ON q.reponse = r.id_rep";
        List<Question> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                int id_Que = rs.getInt("id_Que");
                String contenu = rs.getString("contenu");
                Reponse reponse = new Reponse(rs.getInt("id_rep"), rs.getString("reponse"));
                String bonne_rep = rs.getString("bonne_rep");
                int num_Que = rs.getInt("num_Que");
                list.add(new Question(id_Que, contenu, reponse, bonne_rep, num_Que));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Question readById(int id) {
        String req = "SELECT q.id_Que, q.contenu, q.reponse, q.bonne_rep, q.num_Que, r.* FROM question q JOIN reponse r ON q.reponse = r.id_rep WHERE q.id_Que = ?";
        Question question= null;
        try (PreparedStatement pst = conn.prepareStatement(req)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String contenu = rs.getString("contenu");
                    Reponse reponse = new Reponse(rs.getInt("id_rep"), rs.getString("reponse"));
                    String bonne_rep = rs.getString("bonne_rep");
                    int num_Que = rs.getInt("num_Que");
                    question = new Question(id, contenu, reponse, bonne_rep, num_Que);
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
