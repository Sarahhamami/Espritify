package services;

import entities.Reponse;
import utils.dbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReponseServices implements IService<Reponse> {

    private static Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public ReponseServices() { conn = dbConnection.getInstance().getCnx(); }

    @Override
    public boolean add(Reponse rep) {
        String req = "insert into reponse(contenu) values ('" + rep.getContenu() + "') ";
        try {
            ste = conn.createStatement();
            int rows=ste.executeUpdate(req);
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
    public void delete(Reponse rep) {
        int id = rep.getId_rep();
        String req = "delete from reponse where id_rep='" + id + "'";
        try {
            ste = conn.createStatement();
            int nbLigne = ste.executeUpdate(req);
            if (nbLigne == 0) {
                throw new IllegalArgumentException("La reponse d'id " + id + " n'existe pas");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Reponse rep) {
        int id = rep.getId_rep();
        String reponse= rep.getContenu();
        String req = "update reponse set contenu = ? where id_rep='" + id + "'";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, reponse);
            int nbligne = pst.executeUpdate();
            if (nbligne == 0) {
                System.out.println("Aucune modification effectuée pour cette requête");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Reponse> readAll() {
        String req = "select * from reponse";
        List<Reponse> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Reponse(rs.getInt("id_rep"),rs.getString("contenu")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Reponse readById(int id) {
        String req = "SELECT * FROM reponse WHERE id_rep = ?";
        Reponse reponse = null;
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    reponse = new Reponse(rs.getInt("id_rep"), rs.getString("contenu"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reponse;
    }
}
