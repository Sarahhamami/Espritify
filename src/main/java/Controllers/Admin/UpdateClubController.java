package Controllers.Admin;

import entities.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.ClubService;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class UpdateClubController {

    @FXML
    private Button btnupdate;

    @FXML
    private Button cancelBtn;
    @FXML
    private TextField idTF;

    @FXML
    private TextField emailClubTF;

    @FXML
    private TextField intituleClubTF;

    @FXML
    private TextField logoClubTF;

    @FXML
    private TextField pageFbTF;

    @FXML
    private TextField pageInstaTF;

    @FXML
    private ImageView imageView;

    @FXML
    private Popup popup;
    private final ClubService cs = new ClubService();
    Club club;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }
    @FXML
    private void closePopup() {
        popup.hide();
    }
    private int clubId;

    // Méthode pour initialiser l'ID du club
    /*public void initClubId(int clubId) {
        this.clubId = clubId;
    }*/

    List<Club> listC = cs.readAll();


    @FXML
    void update(ActionEvent event) {
      //  int value1 = (Integer.parseInt(idTF.getText()));


        // Définissez l'ID du club pour cet élément
        String  intitule = intituleClubTF.getText();
        String  email = emailClubTF.getText();
        String  logo =  logoClubTF.getText();
        String  pagefb = pageFbTF.getText();
        String  pageinsta = pageInstaTF.getText();


        if (!intitule.isEmpty() && !email.isEmpty()&&!logo.isEmpty() && !pagefb.isEmpty() && !pageinsta.isEmpty()) {
            cs.update(new Club(clubId,intitule, email, logo, pagefb,pageinsta)); // Assuming OffreStage constructor accepts ID, title, and description
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("club modifié avec succès ");
            alert.showAndWait();
           // Stage stage = (Stage)this.btnupdate.getScene().getWindow();
           // stage.close();
        }

        else {
            Alert  alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText((String)null);
            alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
            alert.showAndWait();
        }


    }


    public void setItem(int clubId, String intitule, String emailClub, String logo, String pageFb, String pageInsta) {

        // Set the text fields with the Club object's data
        this.clubId = clubId;
        this.intituleClubTF.setText(intitule);
        this.emailClubTF.setText(emailClub);
        this.logoClubTF.setText(logo);
       // logo.setText(club.getLogo());
        //String imagePath =logo;
        File file = new File(logo);
        // Créer l'objet Image en utilisant le chemin d'accès récupéré
        Image image = new Image(file.toURI().toString());
        // Afficher cette image dans un ImageView
        //ImageView imageView = new ImageView(image);
        //Image image = new Image(imagePath);
       // System.out.println(club.getLogo().toString());
        // Affichez cette image dans un ImageView
        imageView.setImage(image);
        this.pageFbTF.setText(pageFb);
        this.pageInstaTF.setText(pageInsta);

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
