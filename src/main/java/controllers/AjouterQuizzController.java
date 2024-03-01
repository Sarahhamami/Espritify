package Controllers;

import Entities.Question;
import Entities.Quizz;
import Services.QuestionServices;
import Services.QuizzServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterQuizzController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField description;

    @FXML
    private Label errordesc;

    @FXML
    private Label errorquestion;

    @FXML
    private Label errorsujet;

    @FXML
    private ChoiceBox<Question> questions;

    @FXML
    private TextField sujet;

    QuizzServices qz = new QuizzServices();
    QuestionServices qs = new QuestionServices();

    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Question> QuestionList = qs.readAll();
        ObservableList<Question> observableResponseList = FXCollections.observableArrayList(QuestionList);
        questions.setItems(observableResponseList);

        questions.setConverter(new StringConverter<Question>() {
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
        String DescText = description.getText();
        String QuesText = questions.getValue().toString();

        if (sujetText.isEmpty() || !sujetText.matches("[a-zA-Z]+") || sujetText.length() > 8) {
            errorsujet.setText("Veuillez entrer un sujet valide");
            addBtn.setDisable(true);
        }else if(DescText.isEmpty() || !DescText.matches("[a-zA-Z]+") || DescText.length() > 8){
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
        description.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() > 5) {
                errordesc.setText("Veuillez entrer une description valide");
                addBtn.setDisable(true);
            } else {
                errorsujet.setText("");
                addBtn.setDisable(false);
            }
        });
        if (!addBtn.isDisabled()) {
            addedSuccessfully = qz.add(new Quizz(questions.getValue(),sujet.getText(), description.getText()));
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
