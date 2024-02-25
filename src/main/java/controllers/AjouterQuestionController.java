package controllers;

import entities.Question;
import entities.Reponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Window;
import services.QuestionServices;
import services.ReponseServices;

import java.io.IOException;
import java.util.List;

public class AjouterQuestionController {

    @FXML
    private Button addBtn;

    @FXML
    private TextField bon_rep;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField contenu;

    @FXML
    private TextField num_Que;

    @FXML
    private ChoiceBox<Reponse> reponse;

    QuestionServices qs = new QuestionServices();
    ReponseServices rs = new ReponseServices();

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
        List<Reponse> responseList = rs.readAll();
        ObservableList<Reponse> observableResponseList = FXCollections.observableArrayList(responseList);
        reponse.setItems(observableResponseList);
    }

    @FXML
    void AjouterQuestion(ActionEvent event) throws IOException {
        boolean addedSuccessfully = qs.add(new Question(contenu.getText(),reponse.getValue(),bon_rep.getText(),Integer.parseInt(num_Que.getText())));
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
