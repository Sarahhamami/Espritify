package Controllers.Admin;

import entities.Message;
import entities.Reponse_rec;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ReclamationService;
import services.ReponseRecService;

import java.io.IOException;

public class ReponseController {
    ReponseRecService rrs=new ReponseRecService();
    @FXML
    private Label toplabell;
    @FXML
    private Button RepondreBT;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField desc;

    @FXML
    private TextField titre;
    private int setId_reponse;
    public int getSetId_reponse() {
        return setId_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.setId_reponse = id_reponse;
    }
    @FXML
    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    void Repondre(ActionEvent event) {

        String titleText = titre.getText().trim();
        String descText = desc.getText().trim();
        if (titleText.isEmpty() || descText.isEmpty()) {

            toplabell.setText("Veuillez insere les deux champs");
            toplabell.setStyle("-fx-text-fill: red;");


            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {

                toplabell.setText("Inserer la reponse ici");
                toplabell.setStyle("-fx-text-fill: black;");
            });
            pause.play();
        }else {
            rrs.add(new Reponse_rec(setId_reponse, titre.getText(), desc.getText()));
        }
    }

    @FXML
    void closePopup(ActionEvent event) {

        popup.hide();
    }


}
