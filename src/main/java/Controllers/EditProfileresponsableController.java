package Controllers;


import entities.UserSession;
import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import service.UtilisateurService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditProfileresponsableController {

    @FXML
    private ImageView ImagePreview;

    @FXML
    private TextField emailtx;

    @FXML
    private TextField nomtx;

    @FXML
    private TextField prenomtx;

    @FXML
    private TextField teltx;

    private File selectedImageFile;

    @FXML
    void handleImageUpload(ActionEvent event) throws IOException {
        File dest = new File("C:\\Users\\hamdo\\IdeaProjects\\base\\src\\main\\avatar");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(null);
        if (selectedImageFile != null) {
            FileUtils.copyFileToDirectory(selectedImageFile, dest);
            Image image = new Image(selectedImageFile.toURI().toString());
            ImagePreview.setImage(image);
        }





    }

    private void loadUserData() {
        UtilisateurService u = new UtilisateurService();
        Utilisateur user = u.getUserByEmail(UserSession.userName);
        nomtx.setText(user.getNom());
        prenomtx.setText(user.getPrenom());
        teltx.setText(Integer.toString(user.getTel()));
        emailtx.setText(user.getEmail());

        File imageFile = new File("C:\\Users\\hamdo\\IdeaProjects\\base\\src\\main\\avatar\\" + user.getImage());
        Image image = new Image(imageFile.toURI().toString());
        ImagePreview.setImage(image);
    }
    public void initialize(URL location, ResourceBundle resources) {

        loadUserData();
    }

    @FXML
    void updateProfile(ActionEvent event) throws IOException, SQLException {
        Utilisateur updatedUser = new Utilisateur();
        if (nomtx.getText().isEmpty() || prenomtx.getText().isEmpty() || teltx.getText().isEmpty() || emailtx.getText().isEmpty() ) {

            Alert expiredAlert = new Alert(Alert.AlertType.ERROR);
            expiredAlert.setTitle("Mise a jour profile");
            expiredAlert.setHeaderText("Error");
            expiredAlert.setContentText("Please fill in all required fields.");
            expiredAlert.showAndWait();
            return;
        }
        String email= emailtx.getText();
        updatedUser.setEmail(emailtx.getText());
        updatedUser.setNom(nomtx.getText());
        updatedUser.setPrenom(prenomtx.getText());
        updatedUser.setTel(Integer.parseInt(teltx.getText()));
        String cleanurl = ImagePreview.getImage().getUrl().substring("file:/C:/Users/hamdo/IdeaProjects/base/src/main/avatar/".length());
        System.out.println("preview "+cleanurl);
        System.out.println("preview2 "+ImagePreview.getImage().getUrl());

        updatedUser.setImage(cleanurl);

        if (selectedImageFile != null) {
            File dest = new File("C:\\Users\\hamdo\\IdeaProjects\\base\\src\\main\\avatar");
            FileUtils.copyFileToDirectory(selectedImageFile, dest);
            updatedUser.setImage(selectedImageFile.getName());
        }

        UtilisateurService us=new UtilisateurService();
        us.updateProfile(updatedUser, UserSession.userName);
        loadUserData();
        Alert expiredAlert = new Alert(Alert.AlertType.CONFIRMATION);
        expiredAlert.setTitle("Mise a jour profile");
        expiredAlert.setHeaderText("Succus");
        expiredAlert.setContentText("mise a jour effectué.");
        expiredAlert.showAndWait();
        if (!email.equals(UserSession.userName)) {
            UserSession.userName=email;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Responsable.fxml"));
            Parent root = loader.load();
            HomeController auc = loader.getController();
            emailtx.getScene().setRoot(root);


        }

    }

    }


