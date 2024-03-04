package services;

import entities.Club;
import entities.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSources;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubService implements IService<Club> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public ClubService() {
        conn = DataSources.getInstance().getCnx();
    }


    @Override
    public void add(Club club) {
        String requete = "insert into club (intitule,logo,emailClub,pageFb,pageInsta) values " +
                "('" + club.getIntitule() + "','" + club.getLogo() + "', '" + club.getEmailClub() + "', '" + club.getPageFb() + "', '" + club.getPageInsta() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ObservableList<Club> readAll() {
        ObservableList<Club> listc = FXCollections.observableArrayList();
        String requete = "select * from club";
        // List<Club> listc = new ArrayList<>();

        try (PreparedStatement pst = conn.prepareStatement(requete);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Club club = new Club(
                        rs.getInt("id"),
                        rs.getString("intitule"),
                        rs.getString("emailClub"),
                        rs.getString("logo"),
                        rs.getString("pageFb"),
                        rs.getString("pageInsta")
                );
                listc.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception ou la propager si nécessaire
        }
        return listc;
    }

    @Override
    public Club readById(int id) {
        return null;
    }

    @Override
    public void update(Club club) {
        String requete = "UPDATE club SET intitule = ? ,emailClub = ? , logo = ?, pageFb = ? , pageInsta = ? WHERE id = ?  ";
        try {
            pst = conn.prepareStatement(requete);
            pst.setString(1, club.getIntitule());
            pst.setString(2, club.getEmailClub());
            pst.setString(3, club.getLogo());
            pst.setString(4, club.getPageFb());
            pst.setString(5, club.getPageInsta());
            pst.setInt(6, club.getId());
            pst.executeUpdate();
            //ResultSet rs =  pst.executeQuery(requete);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Club club) {
        String requete = "DELETE from club WHERE id = ?";
        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, club.getId());
            pst.executeUpdate();
            readAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public int obtenirIdClubParIntitule(String intituleClub) {
        String requete = "SELECT id FROM club WHERE intitule = ?";
        int idClub = -1; // Valeur par défaut si aucune correspondance trouvée

        try (PreparedStatement pst = conn.prepareStatement(requete)) {
            pst.setString(1, intituleClub);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    idClub = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception ou la propager si nécessaire
        }

        return idClub;
    }

    public Map<Integer, String> obtenirClubsMap() {
        Map<Integer, String> clubsMap = new HashMap<>();
        String requete = "SELECT id, intitule FROM club";

        try (PreparedStatement pst = conn.prepareStatement(requete);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int idClub = rs.getInt("id");
                String intituleClub = rs.getString("intitule");

                clubsMap.put(idClub, intituleClub);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception ou la propager si nécessaire
        }
        return clubsMap;
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

   /* public List<Evenement> getEventsForClub(int clubId) {
        List<Evenement> events = new ArrayList<>();
        try {
            // Requête SQL pour récupérer les événements associés à un club donné
            String requete = "SELECT id, titre,description,lieu,dateDebut,dateFin FROM evenement WHERE id_club = ?";
            try {
                pst = conn.prepareStatement(requete);
                pst.setInt(1,clubId);
                ResultSet  rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String titre = rs.getString("titre");
                    String description = rs.getString("description");
                    String lieu = rs.getString("lieu");
                    String dateDebut = String.valueOf(rs.getDate("dateDebut"));
                    String dateFin = String.valueOf(rs.getDate("dateFin"));


                    events.add(new Evenement(id, titre,description,lieu,dateDebut,dateFin));
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
*/

    /*public List<Club> listClub() {
        String requete = "SELECT club.id, intitule, emailClub, logo, pageFb, pageInsta, evenement.id AS idE, titre, description, lieu, dateDebut, dateFin " +
                "FROM club INNER JOIN evenement ON club.id = evenement.id_club";
        List<Club> listC = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);

            while (rs.next()) {
                int clubId = rs.getInt("id");
                String intitule = rs.getString("intitule");
                String emailClub = rs.getString("emailClub");
                String logo = rs.getString("logo");
                String pageFb = rs.getString("pageFb");
                String pageInsta = rs.getString("pageInsta");

                int eventId = rs.getInt("idE");
                String titre = rs.getString("titre");
                String description = rs.getString("description");
                String lieu = rs.getString("lieu");
                LocalDate dateDebut = rs.getDate("dateDebut").toLocalDate();
                LocalDate dateFin = rs.getDate("dateFin").toLocalDate();

                Evenement evenement = new Evenement(eventId, titre, description, lieu, dateDebut, dateFin);
                Club club = new Club(clubId, emailClub, intitule, logo, pageFb, pageInsta);

                // Add the club to the list
                listC.add(club);

                // You may also need to associate the event with the club here
                // club.addEvent(evenement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listC;
    }*/
   /* public List<Evenement> listEvenements() {
        String requete = "SELECT intitule, evenement.id AS idE, titre, description, lieu, dateDebut, dateFin FROM club INNER JOIN evenement ON club.id = evenement.id_club";
        List<Evenement> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);

            while (rs.next()) {
               // int clubId = rs.getInt("id");
                String intitule = rs.getString("intitule");
                int eventId = rs.getInt("idE");
                String titre = rs.getString("titre");
                String description = rs.getString("description");
                String lieu = rs.getString("lieu");
                LocalDate dateDebut = rs.getDate("dateDebut").toLocalDate();
                LocalDate dateFin = rs.getDate("dateFin").toLocalDate();

                Evenement evenement = new Evenement(eventId, titre, description, lieu, dateDebut, dateFin);
              //  Club club = new Club(clubId, emailClub, intitule, logo, pageFb, pageInsta);

                // Add the club to the list
                list.add(evenement);

                // You may also need to associate the event with the club here
                // club.addEvent(evenement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }*/
}
