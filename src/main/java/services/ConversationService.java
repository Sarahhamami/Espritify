package services;

import entities.Conversation;
import entities.Reclamation;
import utils.DataSources;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversationService implements IService<Conversation>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public ConversationService() {
        conn= DataSources.getInstance().getCnx();
    }
    @Override
    public void add(Conversation conversation) {
        String requete="insert into conversation (id_user,titre,description) values ("+ conversation.getId_user() +",'"+conversation.getTitre()+"','"+conversation.getDescription()+"')";


        try {
            ste =conn.createStatement();
            ste.execute(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Conversation conversation) {
        String requete = "DELETE FROM conversation WHERE id = " + conversation.getId() + "";

        try {
            ste =conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Conversation conversation) {
        String requete= "UPDATE conversation SET titre='"+conversation.getTitre()+"',description='"+conversation.getDescription()+"',date=current_timestamp where id= " +conversation.getId()  ;
        try {
            ste=conn.createStatement();
            ste.execute(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Conversation> readAll() {
        String requete="SELECT conversation.id,conversation.id_user,conversation.titre,conversation.description,conversation.date,utilisateur.nom as nom,utilisateur.prenom as prenom from conversation INNER JOIN utilisateur on conversation.id_user=utilisateur.id;";
        List<Conversation> list=new ArrayList<>();

        try {
            ste=conn.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while (rs.next()){
                list.add(new Conversation(rs.getInt("id"),rs.getInt("id_user"),rs.getString("titre"),rs.getString("description"),rs.getString("date"),rs.getString("nom"),rs.getString("prenom")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Conversation readById(int id) {

        Conversation resultConv = null;
        String requete = "SELECT * FROM conversation WHERE ID = " + id;

        try  {
            ste=conn.createStatement();
            ResultSet resultSet = ste.executeQuery(requete);
            if (resultSet.next()) {
                resultConv = new Conversation(resultSet.getInt("id"),resultSet.getInt("id_user"), resultSet.getString("titre"), resultSet.getString("description"),resultSet.getString("date"));
            }
            else{
                System.out.println("can't find Conversation");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return resultConv;
    }

    @Override
    public List<Conversation> readAllByID(int id) {
        return null;
    }
}
