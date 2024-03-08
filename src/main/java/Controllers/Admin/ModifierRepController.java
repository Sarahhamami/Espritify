package Controllers.Admin;

import entities.Reponse_rec;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.util.Duration;
import services.ReponseRecService;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierRepController implements Initializable {

    private static ReponseRecService rrs=new ReponseRecService();
    int id_rep;
    public int getid_rep() {
        return id_rep;
    }

    public void setid_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField desc;

    @FXML
    private Button modifierBtn;

    @FXML
    private TextField titre;


    @FXML
    private Label toplabel;

    private Popup popup;

    public Popup getPopup() {
        return popup;
    }

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    void closePopup(ActionEvent event) {
      popup.hide();

    }

    public TextField getDesc() {
        return desc;
    }

    public void setDesc(TextField desc) {
        this.desc = desc;
    }

    public TextField getTitre() {
        return titre;
    }

    public void setTitre(TextField titre) {
        this.titre = titre;
    }

    @FXML
    void modifierRep(ActionEvent event) throws IOException {

        String titleText = titre.getText().trim();
        String descText = desc.getText().trim();

        if (titleText.isEmpty() || descText.isEmpty()) {

            toplabel.setText("Veuillez insere les deux champs");
            toplabel.setStyle("-fx-text-fill: red;");


            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {

                toplabel.setText("Modifier Votre Reponse ici !");
                toplabel.setStyle("-fx-text-fill: black;");
            });
            pause.play();
        } else {
            rrs.update(new Reponse_rec(titre.getText(), id_rep, desc.getText()));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
