package services;

import entities.Club;
import entities.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSources;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EvenementService implements IService<Evenement> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public EvenementService() {
        conn = DataSources.getInstance().getCnx();
    }


    @Override
    public void add(Evenement evenement) {
        // il faut afficher l'evenement selon l'id club ,

      /*  String requete = "INSERT INTO evenements (id_club, titre, description ,lieu, dateDebut, dateFin )VALUES" +
                "('"+evenement.getId_club()+" ',' "+evenement.getTitre()+" ',' " + evenement.getDescription()+"',' " +evenement.getLieu()+"','"+evenement.getDateDebut()+"','"+evenement.getDateFin()+"')";

       */

        String requete = "INSERT INTO evenement (id_club, titre, description, lieu, dateDebut, dateFin) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(requete)) {
            ps.setInt(1, evenement.getId_club());
            ps.setString(2, evenement.getTitre());
            ps.setString(3, evenement.getDescription());
            ps.setString(4, evenement.getLieu());
            ps.setDate(5, java.sql.Date.valueOf(evenement.getDateDebut()));
            ps.setDate(6, java.sql.Date.valueOf(evenement.getDateFin()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de l'événement", e);
        }

    }

    @Override
    public void delete(Evenement evenement) {
        String requete = "DELETE from evenement WHERE id = ?";
        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, evenement.getId());
            pst.executeUpdate();
            readAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Evenement evenement) {
        String requete = "UPDATE evenement SET id_club = ? , titre = ? , description = ? ,lieu = ? ,dateDebut = ?, dateFin = ? WHERE id = ?  ";
        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, evenement.getId_club());
            pst.setString(2, evenement.getTitre());
            pst.setString(3, evenement.getDescription());
            pst.setString(4, evenement.getLieu());
            pst.setString(5, evenement.getDateDebut().toString());
            pst.setString(6, evenement.getDateFin().toString());
            pst.setInt(7, evenement.getId());
            pst.executeUpdate();
            //ResultSet rs =  pst.executeQuery(requete);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Evenement> readAll() {

        ObservableList<Evenement> listE = FXCollections.observableArrayList();
        String requete = "SELECT * from evenement";
        try {
            pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                //listE.add(new Evenement(rs.getInt("id"),rs.getInt("id_club"),rs.getString("titre"),rs.getString("description"),rs.getString("lieu"),rs.getDate("dateDebut").toLocalDate(),rs.getDate("dateFin").toLocalDate()));
                listE.add(new Evenement(rs.getInt("id"), rs.getInt("id_club"), rs.getString("titre"), rs.getString("description"), rs.getString("lieu"), rs.getDate("dateDebut").toLocalDate(), rs.getDate("dateFin").toLocalDate()));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listE;


    }

    public ObservableList<Evenement> search(String keyword) {
        // Supposons que vous ayez une liste de tous les clubs
        // Méthode à implémenter pour obtenir tous les clubs
        List<Evenement> allEvents = readAll();

        ObservableList<Evenement> filteredevenements = FXCollections.observableArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        for (Evenement evenement : allEvents) {
            String formattedDateDebut = formatter.format(evenement.getDateDebut());
            String formattedDateFin = formatter.format(evenement.getDateFin());
            if (evenement.getTitre().toLowerCase().contains(keyword.toLowerCase()) ||
                    evenement.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                    formattedDateDebut.toLowerCase().contains(keyword.toLowerCase()) ||
                    formattedDateFin.toLowerCase().contains(keyword.toLowerCase())) {
                filteredevenements.add(evenement);
            }
        }

        return filteredevenements;
    }


    @Override
    public Evenement readById(int id) {
        return null;
    }


    public ObservableList<Evenement> getEventsForClub(Club club) {
        ObservableList<Evenement> eventsForClub = FXCollections.observableArrayList();
        String requete = "SELECT * FROM evenement WHERE id_club = ?";
        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, club.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evenement evenement = new Evenement(
                        rs.getInt("id"),
                        rs.getInt("id_club"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getString("lieu"),
                        rs.getDate("dateDebut").toLocalDate(),
                        rs.getDate("dateFin").toLocalDate()
                );
                eventsForClub.add(evenement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eventsForClub;
    }


    public String getIntituleClub() {

        ObservableList<Club> listE = FXCollections.observableArrayList();
        String intituleClub = null;

        String requete = "select intitule from club INNER join evenement where evenement.id_club= club.id;";
        try {
            pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                //listE.add(new Evenement(rs.getInt("id"),rs.getInt("id_club"),rs.getString("titre"),rs.getString("description"),rs.getString("lieu"),rs.getDate("dateDebut").toLocalDate(),rs.getDate("dateFin").toLocalDate()));
                intituleClub = rs.getString("intitule");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return intituleClub;


    }

    public List<Evenement> getCalendarActivitiesMonth(ZonedDateTime mth) throws SQLException {
        String req = "select * from Evenement where Month(dateDebut) = ? and year(dateDebut)=?";

        PreparedStatement pst = conn.prepareStatement(req);
        pst.setObject(1, mth.getMonthValue());
        pst.setObject(2, mth.getYear());
        ResultSet rs = pst.executeQuery();
        List<Evenement> Evenements = new ArrayList<>();
        while (rs.next()) {
            Evenement p = new Evenement();
            p.setId(rs.getInt("id"));
            p.setId_club(rs.getInt("id_club"));
            p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("description"));
            p.setLieu(rs.getString("lieu"));
            p.setDateDebut(LocalDate.parse(rs.getString("dateDebut")));
            //p.setDateFin(LocalDate.parse(rs.getString("dateFin")));


            Evenements.add(p);
        }
        return Evenements;
    }



}
