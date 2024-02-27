package Controllers;

import entities.Categorie;
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
import services.CategorieService;

import java.io.IOException;

public class Ajoutercat {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField type;

    @FXML
    private Label erreurtype;

    private Popup popup;
    private final CategorieService cats = new CategorieService();
    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    @FXML
    void ajoutercat(ActionEvent event) throws IOException {
        String typefield=type.getText();
        boolean addedSuccessfully = false;
        if(typefield.isEmpty()|| !typefield.matches("[a-zA-Z]+") || typefield.length() > 20)
        {
            erreurtype.setText("entrez les donnees correctement");
            addBtn.setDisable(true);
        }
        else addBtn.setDisable(false);
        type.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || !newValue.matches("[a-zA-Z0-9]+") || newValue.length() < 5) {
                erreurtype.setText("Veuillez entrer un contenu valide");
                addBtn.setDisable(true);
            } else {
                erreurtype.setText("");
                addBtn.setDisable(false);
            }
        });
        if (!addBtn.isDisabled()) {
            addedSuccessfully = cats.add(new Categorie(typefield));
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
            controller.setCustomMsg("Échec de l'ajout de la categorie");
            controller.setCustomTitle("Échec!");
            controller.setImageView("/images/rejete.png");
            controller.setClosePopupStyle("button-failed");
        }
        controller.setPopup(popup);
        popup.show(ownerWindow);

    }



}
