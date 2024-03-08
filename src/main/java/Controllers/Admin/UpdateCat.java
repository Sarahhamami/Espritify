package Controllers.Admin;

import Controllers.Alert;
import entities.Categorie;
import entities.Cours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.CategorieService;
import services.CoursService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UpdateCat {

    @FXML
    private Button addBtn;

    @FXML
    private Button addBtn1;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField contenu;

    @FXML
    private Label erreurcontenu;

    @FXML
    private Label erreurtitre;

    @FXML
    private TextField type;

    private int id;

    private Stage stage;

    public void setId(int id) {
        this.id = id;
    }

    CategorieService cs=new CategorieService();
    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }


    public void setTitre(String type) {
        this.type.setText(type);
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    @FXML
    void modifiercours(ActionEvent event) throws IOException {


        boolean updatedSuccessfully = cs.update(new Categorie(id,type.getText()));


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
