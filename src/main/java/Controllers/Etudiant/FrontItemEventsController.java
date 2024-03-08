package Controllers.Etudiant;

import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import service.UtilisateurService;
import services.ParticipationService;

import java.time.LocalDate;
import java.util.Date;

public class FrontItemEventsController {

    @FXML
    private Button btnparticiper;

    @FXML
    private Label id;

    @FXML
    private Label club;

    @FXML
    private Label dateDebut;

    @FXML
    private Label dateFin;

    @FXML
    private Label description;

    @FXML
    private HBox itemC;

    @FXML
    private Label lieu;

    @FXML
    private Label titre;

    private int evenementID;

    public Evenement getEvent() {
        Evenement evenement = new Evenement();
        evenement.setId(evenementID);
        evenement.setTitre(titre.getText());
        evenement.setDescription(lieu.getText());
        evenement.setDateDebut(LocalDate.parse(dateDebut.getText()));
        evenement.setDateFin(LocalDate.parse(dateFin.getText()));

        return evenement;
    }
    @FXML
    private void initialize() {
        itemC.setOnMouseClicked(event -> handleItemClick());
    }

    private void handleItemClick() {
        System.out.println("ID du evenement sélectionné : " + evenementID);
        // Vous pouvez maintenant effectuer d'autres actions avec l'ID du club
        // Par exemple, vous pouvez récupérer les détails du club à partir de la base de données
        // en utilisant cet ID.
    }

    public void setEvenementId(int id) {
        this.evenementID = id;
    }

    @FXML
    void participer(ActionEvent event) {
        ParticipationService ps = new ParticipationService();
        Evenement currentEvent = getEvent();
       currentEvent.getId();
        currentEvent.getId_club();
        currentEvent.getTitre();
        currentEvent.getDescription();
        currentEvent.getLieu();
        currentEvent.getDateDebut();
        currentEvent.getDateFin();
        Evenement evenement = new Evenement( currentEvent.getId(),currentEvent.getId_club(),currentEvent.getTitre(),currentEvent.getDescription(),currentEvent.getLieu(),currentEvent.getDateDebut(),currentEvent.getDateFin());

        UtilisateurService u = new UtilisateurService();
        Utilisateur user = u.getUserByEmail(UserSession.userName);
        user.getId();
        System.out.println(currentEvent.getId());
        ps.participer(user,evenement);


    }
    public void setEventDetails(Evenement event) {
        titre.setText(event.getTitre());
        description.setText(event.getDescription());
        lieu.setText(event.getLieu());
        dateDebut.setText(String.valueOf(event.getDateDebut()));
        dateFin.setText(String.valueOf(event.getDateFin()));

    }

}
