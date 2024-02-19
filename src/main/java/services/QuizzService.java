package services;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entites.Quizz;
import utils.dbconnection;

public class QuizzService implements IService<Quizz> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public QuizzService() {
        conn = dbconnection.getInstance().getCnx();
    }

    public void add(Quizz q) {
        String req = "insert into quizz(question) values ('" + q.getQuestion() + "') ";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //add avec prepare statement
    public void addpst(Quizz q) {
        String req = "insert into quizz(question) values (?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, q.getQuestion());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Quizz q) {
        int id = q.getId();
        String req = "delete from quizz where id='" + id + "'";
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

    public void update(Quizz q) {
        int id = q.getId();
        String question= q.getQuestion();
        String req = "update quizz set question = ? where id='" + id + "'";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, question);
            int nbligne = pst.executeUpdate();
            if (nbligne == 0) {
                System.out.println("Aucune modification effectuée pour cette requête");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Quizz> readAll() {
        String req = "select * from quizz";
        List<Quizz> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Quizz(rs.getInt("id"),rs.getString("question")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Quizz readById(int id) {
        String req = "SELECT * FROM quizz WHERE id = ?";
        Quizz quizz = null;
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    quizz = new Quizz(rs.getInt("id"), rs.getString("question"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quizz;
    }
}