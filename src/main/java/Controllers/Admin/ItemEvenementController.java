package Controllers.Admin;

import entities.Club;
import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.ClubService;
import services.EvenementService;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ItemEvenementController {

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private Label date_debut;

    @FXML
    private Label date_fin;

    @FXML
    private Label description;

    @FXML
    private Label lieu;

    @FXML
    private Label id;

    @FXML
    private Label id_club;

    @FXML
    private HBox itemC;

    @FXML
    private Label titre;
    private int evenementId;
    private int evenementClubId;
    private int clubId;

    Evenement evenement = new Evenement();
    private final EvenementService es = new EvenementService();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    private void initialize() {
        itemC.setOnMouseClicked(event -> handleItemClick());
    }
    private void handleItemClick() {
        System.out.println("ID du evenement sélectionné : " + clubId);
        // Vous pouvez maintenant effectuer d'autres actions avec l'ID du club
        // Par exemple, vous pouvez récupérer les détails du club à partir de la base de données
        // en utilisant cet ID.
    }

    public void setEvenementId(int id) {
        this.evenementId = id;
    }
    public void setEvenementClubId(int idclub) {
        this.evenementClubId = idclub;
    }

    public Evenement getEvenements() {
        Evenement evenement = new Evenement();
      //  evenement.setId(Integer.parseInt(id.getText()));
       // evenement.setId_club(Integer.parseInt(id_club.getText()));
        evenement.setId(evenementId);
        evenement.setTitre(titre.getText());
        evenement.setDescription(description.getText());
        evenement.setLieu(lieu.getText());
        evenement.setDateDebut(LocalDate.parse(date_debut.getText()));
        evenement.setDateFin(LocalDate.parse(date_fin.getText()));

        return evenement;
    }
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;

        // Set the text fields with the Club object's data

        titre.setText(evenement.getTitre());
        description.setText(evenement.getDescription());
        lieu.setText(evenement.getLieu());
        date_debut.setText(String.valueOf(evenement.getDateDebut()));
        date_fin.setText(String.valueOf(evenement.getDateFin()));
    }


    @FXML
    void deleteItem(ActionEvent event) {
        Evenement evenement = getEvenements();
        es.delete(evenement);
    }

    @FXML
    void updateItem(ActionEvent event) {
        try {
            Evenement currentEvent = getEvenements();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/updateEvenement.fxml"));
            BorderPane popupContent = null;
            try {
                popupContent = loader.load();
            }catch (IOException e){
                throw new RuntimeException(e);
            }

            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            UpdateEvenementController controller = loader.getController();
            controller.setEventItem(
                    evenementId,
                    evenementClubId,
                    currentEvent.getTitre(),
                    currentEvent.getDescription(),
                    currentEvent.getLieu(),
                    currentEvent.getDateDebut(),
                    currentEvent.getDateFin()
            );
       controller.setPopup(popup);
            Stage primaryStage = (Stage) itemC.getScene().getWindow();
            popup.show(primaryStage);


        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

    }

}
