package Controllers;

import entities.Quizz;
import services.QuizzServices;
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

public class UpdateQuizzController {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField description;

    @FXML
    private TextField sujet;
    private int id;

    @FXML
    private Button updateButton;

    private Popup popup;

    QuizzServices qs= new QuizzServices();

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setId(int id) { this.id=id; }

    public void setSujet(String sujet) {
        this.sujet.setText(sujet);
    }

    @FXML
    void UpdateQuizz(ActionEvent event) throws IOException {
        boolean updatedSuccessfully = qs.update(new Quizz(id, sujet.getText(), description.getText()));

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
