package services;

import entities.Dossier_stage;
import entities.Entretien;
import utils.DataSources;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntretienService implements IService<Entretien>{

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    public EntretienService() {
        conn= DataSources.getInstance().getCnx();

    }


    @Override
    public boolean add(Entretien entretien) {
        String requete = "INSERT INTO `entretien` (`id_user`, `id_stage`, `type`, `description`, `date`, `lieu`, `etat`) VALUES (?,?,?,?,?,?,?);";

        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, entretien.getId_user());
            pst.setInt(2, entretien.getId_stage());
            pst.setString(3, entretien.getType());
            pst.setString(4,entretien.getDescription());
            pst.setDate(5, new java.sql.Date(entretien.getDateEntre().getTime()));
            pst.setString(6, entretien.getLieu());
            if (entretien.isEtat()){
                pst.setInt(7, 1);
            }
            else pst.setInt(7,0);

            int rowsAffected = pst.executeUpdate();
            // If rowsAffected > 0, it means the item was successfully added
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging purposes
            return false; // Return false indicating failure
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
    public boolean deleteEntre(int id_user, int id_offre){
        String requete= "delete from entretien where id_user='"+id_user+"' and id_stage='"+id_offre+"'  ";
        try {
            ste=conn.createStatement();

            int rowsAffected = ste.executeUpdate(requete);
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(Entretien entretien) {
        String requete= "update entretien set type=? , description=?, date=? , lieu=?, etat=? where id_user=? and id_stage=?";
        try {
            pst=conn.prepareStatement(requete);
            pst.setInt(6, entretien.getId_user());
            pst.setInt(7, entretien.getId_stage());
            pst.setString(1, entretien.getType());
            pst.setString(2,entretien.getDescription());
            pst.setDate(3, new java.sql.Date(entretien.getDateEntre().getTime()));
            pst.setString(4, entretien.getLieu());
            if (entretien.isEtat()){
                pst.setInt(5, 1);
            }
            else pst.setInt(5,0);



            int rowsAffected = pst.executeUpdate();
            // If rowsAffected > 0, it means the item was successfully added
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Entretien> readAll() {
        String requete = "SELECT * FROM `entretien`";
        List<Entretien> lst = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                lst.add(new Entretien(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getBoolean(7)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    @Override
    public Entretien readById(int id) {
        return null;
    }

}
