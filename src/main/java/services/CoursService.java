package services;

import entities.Categorie;
import entities.Cours;
import entities.Question;
import utils.DataSources;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursService {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public CoursService() {
        conn= DataSources.getInstance().getCnx();
    }

    public boolean add(Cours c) {
        String req = "insert into cours(titre,etat,contenu,id_cat,rate) values (?,?,?,?,0)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, c.getTitre());
            pst.setBoolean(2, c.isEtat());
            pst.setString(3, c.getContenu());
            pst.setInt(4, c.getcat().getId());
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

    public void delete(Cours c) {
        String req = "delete from cours where id='" + c.getId() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean update(Cours c) {
        int id = c.getId();
        String titre= c.getTitre();
        Boolean etat=c.isEtat();
        String contenu = c.getContenu();

        String req = "UPDATE cours SET titre=?,etat=?,contenu = ? WHERE id = ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, titre);
            pst.setBoolean(2, etat);
            pst.setString(3, contenu);
            pst.setInt(4, id);
            int nbligne = pst.executeUpdate();
            if (nbligne == 0) {
                System.out.println("Aucune modification effectuée pour cette requête");
                return false;
            }
            else return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean updateRate(Cours c) {
        int id = c.getId();
        String titre= c.getTitre();
        Boolean etat=c.isEtat();
        String contenu = c.getContenu();

        String req = "UPDATE cours SET titre=?,etat=?,contenu = ? WHERE id = ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, titre);
            pst.setBoolean(2, etat);
            pst.setString(3, contenu);
            pst.setInt(4, id);
            int nbligne = pst.executeUpdate();
            if (nbligne == 0) {
                System.out.println("Aucune modification effectuée pour cette requête");
                return false;
            }
            else return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Cours> readAll() {
        String req = "SELECT c.id, c.titre, c.etat, c.contenu, c.id_cat,c.rate, cat.* FROM cours c JOIN categorie cat ON c.id_cat = cat.id";
        List<Cours> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                Boolean etat = rs.getBoolean("etat");
                String contenu = rs.getString("contenu");
                int rate = rs.getInt("rate");
                Categorie categorie = new Categorie(rs.getInt("id"), rs.getString("type"));


                list.add(new Cours(id,titre,etat, contenu, rate, categorie));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Cours readById(int id) {
        String req = "SELECT c.id, c.titre, c.etat, c.contenu, c.id_cat, cat.* FROM cours c JOIN categorie cat ON c.id_cat = cat.id WHERE c.id = ?";
        Cours cours= null;
        try (PreparedStatement pst = conn.prepareStatement(req)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String titre = rs.getString("titre");
                    Boolean etat = rs.getBoolean("etat");
                    String contenu = rs.getString("contenu");
                    Categorie categorie = new Categorie(rs.getInt("id"), rs.getString("type"));
                    cours = new Cours(id,titre,etat, contenu,categorie);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cours;
    }



}
