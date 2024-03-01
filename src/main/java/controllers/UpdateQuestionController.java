package Controllers;

import Entities.Question;
import Services.QuestionServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.io.IOException;

public class UpdateQuestionController {

    @FXML
    private TextField bonRep;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField contenu;

    @FXML
    private TextField rep1;

    @FXML
    private TextField rep2;

    @FXML
    private TextField rep3;

    private int id;

    @FXML
    private Button updateButton;

    QuestionServices qs = new QuestionServices();

    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    public void setId(int id) {
        this.id= id;
    }
    public void setContenu(String contenu) {
        this.contenu.setText(contenu);
    }
    public void setRep1(String rep1) {
        this.rep1.setText(rep1);
    }
    public void setRep2(String rep2) {
        this.rep2.setText(rep2);
    }
    public void setRep3(String rep3) {
        this.rep3.setText(rep3);
    }
    public void setBonRep(String bonRep) {this.bonRep.setText(bonRep);}

    @FXML
    void UpdateQuestion(ActionEvent event) throws IOException {
        boolean updatedSuccessfully = qs.update(new Question(id, contenu.getText(), rep1.getText(), rep2.getText(), rep3.getText(), bonRep.getText()));

        Node node = (Node) event.getSource();
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
