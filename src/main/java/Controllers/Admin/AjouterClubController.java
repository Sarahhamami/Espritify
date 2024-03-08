package Controllers.Admin;

import entities.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.ClubService;

import javax.swing.*;
import java.awt.*;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.scene.image.Image;


public class AjouterClubController
{
    private final ClubService cs = new ClubService();
    private final AdminClubController ac = new AdminClubController();

    @FXML
    private Button btnAjout;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField intituleClubTF;


    @FXML
    private TextField emailClubTF;


    @FXML
    private TextField logoClubTF;

    @FXML
    private TextField pageFbTF;

    @FXML
    private TextField pageInstaTF;


    @FXML
    private Popup popup;
    @FXML
    private VBox pnItems ;
    @FXML
    private ImageView imageView;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }
    @FXML
    private void closePopup() {
        popup.hide();
    }



    @FXML
    public void AjouterClub(ActionEvent actionEvent) {

        String  intitule = this.intituleClubTF.getText();
        String  email = this.emailClubTF.getText();
        String  logo =  this.logoClubTF.getText();
        String  pagefb = this.pageFbTF.getText();
        String  pageinsta = this.pageInstaTF.getText();

        if (!intitule.isEmpty() && !email.isEmpty()&&!logo.isEmpty() && !pagefb.isEmpty() && !pageinsta.isEmpty()) {
            cs.add(new Club(intituleClubTF.getText(), emailClubTF.getText(), logoClubTF.getText(), pageFbTF.getText(), pageInstaTF.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Club ajouté avec succès ");
            alert.showAndWait();

        }

        else {
            Alert  alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText((String)null);
            alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
            alert.showAndWait();
        }

    }

    @FXML
    public void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers Image", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                // Copier l'image vers le répertoire xampp/htdocs
                String targetPath = "C:/xampp/htdocs/" + selectedFile.getName(); // Adapté à votre chemin d'accès xampp
                Files.copy(selectedFile.toPath(), new File(targetPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Enregistrer le chemin d'accès dans le champ texte
                logoClubTF.setText(targetPath);

                String imagePath = "http://localhost/" + selectedFile.getName(); // URL de l'image dans le répertoire htdocs de XAMPP

                // Créez un objet Image avec le chemin d'accès
                Image image = new Image(imagePath);

                // Affichez cette image dans un ImageView
                imageView.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer les erreurs d'écriture du fichier
            }
        }
    }



}

