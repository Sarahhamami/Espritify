package Controllers;

import Entities.Question;
import Services.QuestionServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.io.IOException;

public class AjouterQuestionController {

    @FXML
    private Button addBtn;

    @FXML
    private TextField bonRep;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField contenu;

    @FXML
    private Label errorbonrep;

    @FXML
    private Label errorcontenu;

    @FXML
    private Label errorrep1;

    @FXML
    private Label errorrep2;

    @FXML
    private Label errorrep3;

    @FXML
    private TextField rep1;

    @FXML
    private TextField rep2;

    @FXML
    private TextField rep3;

    QuestionServices qs = new QuestionServices();

    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }



    @FXML
    void AjouterQuestion(ActionEvent event) throws IOException {
        boolean addedSuccessfully = false;
        String contenuu = contenu.getText();
        String reponse1 = rep1.getText();
        String reponse2 = rep2.getText();
        String reponse3 = rep3.getText();
        String bonneReponse = bonRep.getText();


        if (contenuu.isEmpty() || !contenuu.matches("[a-zA-Z]+") || contenuu.length() > 8) {
            errorcontenu.setText("Veuillez entrer un contenu valide");
            addBtn.setDisable(true);
        }else if(reponse1.isEmpty() || !reponse1.matches("[a-zA-Z]+") || reponse1.length() > 8){
            errorrep1.setText("Veuillez entrer un reponse valide");
            addBtn.setDisable(true);
        }else if(reponse2.isEmpty() || !reponse2.matches("[a-zA-Z]+") || reponse2.length() > 8){
            errorrep2.setText("Veuillez entrer un reponse valide");
            addBtn.setDisable(true);
        } else if(reponse3.isEmpty() || !reponse3.matches("[a-zA-Z]+") || reponse3.length() > 8){
            errorrep3.setText("Veuillez entrer un reponse valide");
            addBtn.setDisable(true);
        }else if(bonneReponse.isEmpty() || !bonneReponse.matches("[a-zA-Z]+") || bonneReponse.length() > 8){
            errorbonrep.setText("Veuillez entrer un reponse valide");
            addBtn.setDisable(true);
        } else {
            addBtn.setDisable(false);
        }


        contenu.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 5) {
                errorcontenu.setText("Veuillez entrer un contenu valide");
                addBtn.setDisable(true);
            } else {
                errorcontenu.setText("");
                addBtn.setDisable(false);
            }
        });
        rep1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 5) {
                errorrep1.setText("Veuillez entrer un reponse valide");
                addBtn.setDisable(true);
            } else {
                errorrep1.setText("");
                addBtn.setDisable(false);
            }
        });
        rep2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 5) {
                errorrep2.setText("Veuillez entrer un reponse valide");
                addBtn.setDisable(true);
            } else {
                errorrep2.setText("");
                addBtn.setDisable(false);
            }
        });
        rep3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 5) {
                errorrep3.setText("Veuillez entrer un reponse valide");
                addBtn.setDisable(true);
            } else {
                errorrep3.setText("");
                addBtn.setDisable(false);
            }
        });
        bonRep.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 5) {
                errorbonrep.setText("Veuillez entrer un reponse valide");
                addBtn.setDisable(true);
            } else {
                errorbonrep.setText("");
                addBtn.setDisable(false);
            }
        });
        if (!addBtn.isDisabled()) {
            addedSuccessfully = qs.add(new Question(contenu.getText(),rep1.getText(),rep2.getText(),rep3.getText(),bonRep.getText()));
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
            controller.setCustomMsg("Échec de l'ajout du Question");
            controller.setCustomTitle("Échec!");
            controller.setImageView("/images/rejete.png");
            controller.setClosePopupStyle("button-failed");
        }
        controller.setPopup(popup);
        popup.show(ownerWindow);

    }

}
