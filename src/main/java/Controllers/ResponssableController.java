package Controllers;



import entities.UserSession;
import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import service.UtilisateurService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResponssableController implements Initializable {
    @FXML
    private ImageView PreviewImage;

    @FXML
    private Button btn2;

    @FXML
    private Text emailtx;

    @FXML
    private Text nomtx;

    @FXML
    private Text prenomtx;

    @FXML
    private Text roletx;

    @FXML
    private Text teltx;





    public void initialize(URL location, ResourceBundle resources) {
        UtilisateurService u = new UtilisateurService();
        Utilisateur user = u.getUserByEmail(UserSession.userName);
        displayUser(user);
    }
    public void  displayUser(Utilisateur user) {
        nomtx.setText(user.getNom());
        prenomtx.setText(user.getPrenom());
        teltx.setText(Integer.toString(user.getTel()));
        emailtx.setText(user.getEmail());
        roletx.setText(user.getRole());

        File imageFile = new File("C:\\Users\\hamdo\\IdeaProjects\\base\\src\\main\\avatar\\" + user.getImage());
        System.out.println("C:\\Users\\hamdo\\IdeaProjects\\base\\src\\main\\avatar\\" + user.getImage());
        Image image = new Image(imageFile.toURI().toString());
        PreviewImage.setImage(image);

    }


    @FXML
    void goToResp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditProfile.fxml"));
        Parent root=loader.load();
        EditProfileController auc=loader.getController();
        btn2.getScene().setRoot(root);
    }

}
