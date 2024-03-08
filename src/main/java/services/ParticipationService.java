package services;

import entities.Evenement;
import entities.Participation;
import entities.User;
import entities.Utilisateur;
import utils.DataSources;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipationService {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    Utilisateur user;

    public ParticipationService(){
        conn = DataSources.getInstance().getCnx();
    }

    public Utilisateur getUser() {
        return this.user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void participer(Utilisateur user, Evenement evenement) {
        try {
            String requete = "INSERT INTO participation_evenement(id_user, id_evenement) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.setInt(2, evenement.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Participation> getParticipants() {
        List<Participation> participants = new ArrayList<>();
        try {
            String requete = "SELECT u.id, u.nom ,u.email ,e.titre, e.lieu, e.dateDebut, e.dateFin " +
                    "FROM participation_evenement p " +
                    "INNER JOIN utilisateur u ON p.id_user = u.id " +
                    "INNER JOIN evenement e ON p.id_evenement = e.id";
            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(); // Utilise executeQuery() pour obtenir les résultats

            // Parcourir le ResultSet et ajouter les informations des participants à la liste
            while (rs.next()) {

                int userId = rs.getInt("u.id");
                String nomUtilisateur = rs.getString("u.nom");
                String emailUtilisateur = rs.getString("u.email");
                String titreEvenement = rs.getString("e.titre");
                String lieuEvenement = rs.getString("e.lieu");
                Date dateDebut = rs.getDate("e.dateDebut");
                Date dateFin = rs.getDate("e.dateFin");
                Participation participantInfo = new Participation( nomUtilisateur, emailUtilisateur, titreEvenement, lieuEvenement, dateDebut, dateFin);



                participants.add(participantInfo);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des participants : " + ex.getMessage());
        }
        return participants;
    }

}
