package services;

import entities.Message;
import utils.DataSources;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageService {   private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    public MessageService() {
        conn= DataSources.getInstance().getCnx();
    }



    public void add(Message message) {

        String requete="insert into message (id_user,id_conv,description) values ("+ message.getId_user() +","+message.getId_conv()+",'" +message.getDescription()+ "')";


        try {
            ste =conn.createStatement();
            ste.execute(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean delete(int id) {
        return false;
    }


    public void  delete(Message message) {



        String requete = "DELETE FROM message WHERE id = " + message.getId() + "";

        try {
            ste =conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void update(Message message) {
        String requete= "UPDATE message SET date=current_timestamp,description='"+message.getDescription()+"' where id= " +message.getId()  ;
        try {
            ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public List<Message> readAll() {
        String requete="SELECT message.id,message.id_conv,message.id_user,utilisateur.nom as nom,utilisateur.prenom as prenom,message.date,message.description FROM message JOIN conversation ON message.id_conv= conversation.id JOIN utilisateur ON message.id_user = conversation.id_user;";
        List<Message> list=new ArrayList<>();

        try {
            ste=conn.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while (rs.next()){
                list.add(new Message(rs.getInt("id"),rs.getInt("id_user"),rs.getInt("id_conv"),rs.getString("description"),rs.getString("date"),rs.getString("nom"),rs.getString("prenom")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public Message readById(int id) {
        Message resultMsg = null;
        String requete = "SELECT message.id, message.id_conv, message.id_user, utilisateur.nom as nom, utilisateur.prenom as prenom, message.date, message.description FROM message JOIN utilisateur ON message.id_user = utilisateur.id WHERE message.id_conv = " +id;

        try  {
            ste=conn.createStatement();
            ResultSet resultSet = ste.executeQuery(requete);
            if (resultSet.next()) {
                resultMsg = new Message(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3), resultSet.getString("description"),resultSet.getString("date"));
            }
            else{
                System.out.println("can't find Conversation");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return resultMsg;
    }


    public List<Message> readAllByID(int id) {
        String requete="SELECT message.id, message.id_conv, message.id_user, utilisateur.nom as nom, utilisateur.prenom as prenom, message.date, message.description FROM message JOIN utilisateur ON message.id_user = utilisateur.id WHERE message.id_conv = " +id;
        List<Message> list=new ArrayList<>();

        try {
            ste=conn.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while (rs.next()){
                list.add(new Message(rs.getInt("id"),rs.getInt("id_user"),rs.getInt("id_conv"),rs.getString("description"),rs.getString("date"),rs.getString("nom"),rs.getString("prenom")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
