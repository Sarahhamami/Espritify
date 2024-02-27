package services;
import entities.Reponse_rec;
import utils.DataSources;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReponseRecService implements IService<Reponse_rec> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public ReponseRecService() {
        conn = DataSources.getInstance().getCnx();
    }

    @Override
    public void add(Reponse_rec reponseRec) {

        String requete = "insert into reponse_rec (id_rec,titre,description) values (" + reponseRec.getId_rec() + ",'" + reponseRec.getTitre() + "','" + reponseRec.getDesc() + "')";
        String requete1= "update reclamation set etat='traité' where id=" + reponseRec.getId_rec() + "";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(requete);
            ste.executeUpdate(requete1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Reponse_rec reponseRec) {

        String requete = "DELETE FROM reponse_rec WHERE id = " + reponseRec.getId() + "";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Reponse_rec reponseRec) {

        String requete= "UPDATE reponse_rec SET titre='"+reponseRec.getTitre()+"',description='"+reponseRec.getDesc()+"',date=current_date where id= " +reponseRec.getId()  ;
        try {
            ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Reponse_rec> readAll() {
        String requete = "SELECT reponse_rec.id,reponse_rec.id_rec,reponse_rec.titre,reponse_rec.description,reponse_rec.date,reclamation.titre as titre_reclamation from reponse_rec INNER JOIN reclamation on reponse_rec.id_rec=reclamation.id;";
        List<Reponse_rec> list = new ArrayList<>();

        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Reponse_rec(rs.getInt("id"),rs.getInt("id_rec"),rs.getString("titre"),rs.getString("titre_reclamation"),rs.getString("description"),rs.getString("date")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Reponse_rec readById(int id) {
        Reponse_rec resultRep = null;
        String requete = "SELECT * FROM reponse_rec WHERE ID = " + id;

        try {
            ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(requete);
            if (resultSet.next()) {
                resultRep = new Reponse_rec(resultSet.getInt("id"), resultSet.getInt("id_rec"), resultSet.getString("titre"), resultSet.getString("description"),resultSet.getString("date"));
            } else {
                System.out.println("can't find reclamation");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return resultRep;
    }

    @Override
    public List<Reponse_rec> readAllByID(int id) {
        return null;
    }
}
