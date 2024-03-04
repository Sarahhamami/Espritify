package services;
import  utils.DataSources;
import entities.Reclamation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    public ReclamationService() {
        conn= DataSources.getInstance().getCnx();
    }



    public void add(Reclamation reclamation) {
        String requete="insert into reclamation (id_user,titre,description,etat)" +
                " values ("+ reclamation.getId_user() +",'"+reclamation.getTitre()+"','"+reclamation.getDescription()+"','non traité')";

        try {
            ste =conn.createStatement();
            ste.executeUpdate(requete);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void delete(Reclamation reclamation) {

        String requete = "DELETE FROM reclamation WHERE id = " + reclamation.getId() + "";

        try {
            ste =conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void update(Reclamation reclamation) {
        String requete= "UPDATE reclamation SET titre='"+reclamation.getTitre()+"',description='"+reclamation.getDescription()+"',date=current_date where id= " +reclamation.getId()  ;
        try {
            ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Reclamation> readAll() {
        String requete="SELECT reclamation.id as id,id_user,description,titre,date,etat,utilisateur.nom as nom,utilisateur.prenom as prenom from reclamation INNER JOIN utilisateur on reclamation.id_user=utilisateur.id ";
        List<Reclamation> list=new ArrayList<>();

        try {
            ste=conn.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while (rs.next()){
                list.add(new Reclamation(rs.getInt("id"),rs.getInt("id_user"),
                        rs.getString("titre"), rs.getString("description"),
                        rs.getString("etat"),rs.getString("date"),
                        rs.getString("nom"),rs.getString("prenom")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public Reclamation readById(int id) {
        Reclamation resultReclamation = null;
        String requete = "SELECT * FROM reclamation WHERE ID = " + id;

        try  {
            ste=conn.createStatement();
            ResultSet resultSet = ste.executeQuery(requete);
            if (resultSet.next()) {
                resultReclamation = new Reclamation(resultSet.getInt("id"),resultSet.getInt("id_user"), resultSet.getString("titre"), resultSet.getString("description"),resultSet.getString("etat"),resultSet.getString("date"));
            }
            else{
                System.out.println("can't find reclamation");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return resultReclamation;
    }


    public List<Reclamation> readAllByID(int id) {
        return null;
    }

    public int countTraite() {
        int nbr = 0;
        String requete = "SELECT COUNT(*) FROM reclamation WHERE etat = 'traité'";
        try {
            ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(requete);
            if (resultSet.next()) {
                nbr = resultSet.getInt(1); // Get the count from the first column
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbr;
    }
    public int countNonTraite() {
        int nbr = 0;
        String requete = "SELECT COUNT(*) FROM reclamation WHERE etat = 'non traité'";
        try {
            ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(requete);
            if (resultSet.next()) {
                nbr = resultSet.getInt(1); // Get the count from the first column
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbr;
    }
    public int countRec() {
        int nbr = 0;
        String requete = "SELECT COUNT(*) FROM reclamation ";
        try {
            ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(requete);
            if (resultSet.next()) {
                nbr = resultSet.getInt(1); // Get the count from the first column
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbr;
    }

}
