package Controllers.Admin;

import entities.Reclamation;
import entities.Reponse_rec;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ReclamationService;

import javax.swing.*;
import java.io.IOException;

public class ReclamerController {

    private final ReclamationService rs=new ReclamationService();
    @FXML
    private Button RepondreBT;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField desc;

    @FXML
    private TextField titre;

    @FXML
    private Label toplabell;
     private javafx.stage.Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    void Reclamer(ActionEvent event) {

        if (titre.getText().isEmpty() || desc.getText().isEmpty()) {

            toplabell.setText("Veuillez insere les deux champs");
            toplabell.setStyle("-fx-text-fill: red;");


            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {

                toplabell.setText("Inserer Votre Reclamation details");
                toplabell.setStyle("-fx-text-fill: black;");
            });
            pause.play();
        }else {
            rs.add(new Reclamation(56, titre.getText(), desc.getText()));
            titre.clear();
            desc.clear();
            popup.hide();
        }

    }

    @FXML
    void closePopup(ActionEvent event) {

        popup.hide();
    }





}
