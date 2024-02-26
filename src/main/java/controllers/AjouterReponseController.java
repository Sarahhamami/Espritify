package controllers;

import entities.Reponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Window;
import services.ReponseServices;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterReponseController implements Initializable{

    @FXML
    private Label errorLabel;

    @FXML
    private Button addBtn;
    ReponseServices rs= new ReponseServices();

    @FXML
    private TextField contenu;

    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    @FXML
    void AjouterReponse(ActionEvent event) throws IOException {

        boolean addedSuccessfully = false;
        String contenuText = contenu.getText();

        if (contenuText.isEmpty() || !contenuText.matches("[a-zA-Z]+") || contenuText.length() > 6) {
            errorLabel.setText("Veuillez entrer un contenu valide");
            addBtn.setDisable(true);
        } else {
            addBtn.setDisable(false);
        }

        contenu.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 6) {
                errorLabel.setText("Veuillez entrer un contenu valide");
                addBtn.setDisable(true);
            } else {
                errorLabel.setText("");
                addBtn.setDisable(false);
            }
        });

        if (!addBtn.isDisabled()) {
            addedSuccessfully = rs.add(new Reponse(contenuText));
        }


        Node node = (Node) event.getSource();
        Window ownerWindow = node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Alert.fxml"));
        AnchorPane popupContent = loader.load();
        Popup popup = new Popup();
        popup.getContent().add(popupContent);
        Alert controller = loader.getController();
        if (addedSuccessfully) {
            controller.setCustomMsg("Ajout avec succès");
            controller.setCustomTitle("Succès!");
            controller.setImageView("/images/approuve.png");
            controller.setClosePopupStyle("button-success");
        } else {
            controller.setCustomMsg("Échec de l'ajout du Reponse");
            controller.setCustomTitle("Échec!");
            controller.setImageView("/images/rejete.png");
            controller.setClosePopupStyle("button-failed");
        }
        controller.setPopup(popup);
        popup.show(ownerWindow);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}