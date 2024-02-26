package controllers;

import entities.Question;
import entities.Quizz;
import entities.Reponse;
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
import services.QuizzService;
import services.ReponseServices;

import java.io.IOException;
import java.util.EventObject;

public class UpdateQuizzController {

    @FXML
    private ChoiceBox<Question> Question;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField description;

    @FXML
    private TextField id;

    @FXML
    private TextField sujet;

    private Popup popup;

    private Quizz quizzToUpdate;

    QuizzService qs= new QuizzService();



    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    public void setQuizzToUpdate(Quizz quizz) {
        this.quizzToUpdate = quizz;
    }

    @FXML
    public void UpdateQuizz(ActionEvent actionEvent) throws IOException {
        System.out.println(quizzToUpdate);

        id.setText(String.valueOf(quizzToUpdate.getId_quizz()));
        sujet.setText(quizzToUpdate.getSujet());
        description.setText(quizzToUpdate.getDescript());

        boolean updatedSuccessfully = qs.update(new Quizz(quizzToUpdate.getId_quizz(), sujet.getText(), description.getText()));


        Node node = (Node) actionEvent.getSource();
        Window ownerWindow = node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Alert.fxml"));
        AnchorPane popupContent = loader.load();
        Popup popup = new Popup();
        popup.getContent().add(popupContent);
        Alert controller = loader.getController();
        if (updatedSuccessfully) {
            controller.setCustomMsg("Update avec succès");
            controller.setCustomTitle("Succès!");
            controller.setImageView("/images/approuve.png");
            controller.setClosePopupStyle("button-success");
        } else {
            controller.setCustomMsg("Échec de l'update du quizz");
            controller.setCustomTitle("Échec!");
            controller.setImageView("/images/rejete.png");
            controller.setClosePopupStyle("button-failed");
        }
        controller.setPopup(popup);
        popup.show(ownerWindow);
    }


}
