package services;
import entities.Categorie;
import utils.DataSources;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public CategorieService() {
        conn= DataSources.getInstance().getCnx();
    }
    public boolean add(Categorie c )
    {
        String requete="insert into categorie (type) values ('"+c.getType()+"') ";
        try {
            ste=conn.createStatement();
           int rows= ste.executeUpdate(requete);
           if (rows>0){
               return true;
           }
           else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(Categorie c) {
        String requete= "delete from categorie where id='"+c.getId()+"'";
        try {
            ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Categorie c) {
        int id = c.getId();
        String type= c.getType();
        String req = "update categorie set type = ? where id= ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, type);
            pst.setInt(2, id);
            int nbligne = pst.executeUpdate();
            if (nbligne == 0) {
                System.out.println("Aucune modification effectuée pour cette requête");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Categorie> readAll() {
        String requete="select * from categorie";
        List<Categorie> list=new ArrayList<>();
        try {
            ste= conn.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while(rs.next())
            {
                list.add(new Categorie(rs.getInt("id"),rs.getString("type")));

            }

            //pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Categorie readByid(int id) {
        String req = "SELECT * FROM categorie WHERE id = ?";
        Categorie categorie = null;
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categorie = new Categorie(rs.getInt("id"),rs.getString("type"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categorie;
    }



}
