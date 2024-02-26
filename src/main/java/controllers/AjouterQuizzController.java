package controllers;

import entities.Question;
import entities.Quizz;
import entities.Reponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.StringConverter;
import services.QuestionServices;
import services.QuizzService;
import services.ReponseServices;

import java.io.IOException;
import java.util.List;

public class AjouterQuizzController {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField desc;

    @FXML
    private ChoiceBox<Question> question;

    @FXML
    private TextField sujet;

    @FXML
    private Label errordesc;
    @FXML
    private Label errorsujet;
    @FXML
    private Label errorrep;

    QuestionServices qs = new QuestionServices();
    QuizzService qz = new QuizzService();

    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    @FXML
    public void initialize() {
        List<Question> QuestionList = qs.readAll();
        ObservableList<Question> observableResponseList = FXCollections.observableArrayList(QuestionList);
        question.setItems(observableResponseList);

        question.setConverter(new StringConverter<Question>() {
            @Override
            public String toString(Question question) {
                return question!= null ? question.getContenu() : null;
            }


            @Override
            public Question fromString(String s) {
                return null;
            }
        });

    }

    @FXML
    void AjouterQuizz(ActionEvent event) throws IOException {
        boolean addedSuccessfully = false;
        String sujetText = sujet.getText();
        String DescText = desc.getText();
        String QuesText = question.getValue().toString();

        if (sujetText.isEmpty() || !sujetText.matches("[a-zA-Z]+") || sujetText.length() > 6) {
            errorsujet.setText("Veuillez entrer un sujet valide");
            addBtn.setDisable(true);
        }else if(DescText.isEmpty() || !DescText.matches("[a-zA-Z]+") || DescText.length() > 6){
            errordesc.setText("Veuillez entrer une description valide");
            addBtn.setDisable(true);
        } else if(QuesText.isEmpty()){
            errordesc.setText("Veuillez selectionner une case");
            addBtn.setDisable(true);
        }else {
            addBtn.setDisable(false);
        }
        sujet.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 5) {
                errorsujet.setText("Veuillez entrer un contenu valide");
                addBtn.setDisable(true);
            } else {
                errorsujet.setText("");
                addBtn.setDisable(false);
            }
        });
        desc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 5) {
                errordesc.setText("Veuillez entrer un contenu valide");
                addBtn.setDisable(true);
            } else {
                errorsujet.setText("");
                addBtn.setDisable(false);
            }
        });
        if (!addBtn.isDisabled()) {
            addedSuccessfully = qz.add(new Quizz(sujet.getText(),desc.getText(), (Question) question.getValue()));
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
            controller.setCustomMsg("Échec de l'ajout du Quizz");
            controller.setCustomTitle("Échec!");
            controller.setImageView("/images/rejete.png");
            controller.setClosePopupStyle("button-failed");
        }
        controller.setPopup(popup);
        popup.show(ownerWindow);
    }

}
