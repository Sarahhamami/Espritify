package Controllers.Etudiant;

import Controllers.Admin.AddDossierController;
import entities.UserSession;
import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import service.UtilisateurService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Accueil implements Initializable {

    @FXML
    private Button btnMenus;
    @FXML
    private Button btnDossierStage;

    @FXML
    private Button btnEntretien;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btndiscussion  ;
    @FXML
    private Button btnCours;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnStage;

    @FXML
    private Button btnQuizz;

    @FXML
    private Button btnotherQuizz;
    @FXML
    private Button btnClub;

    @FXML
    private Label nomUser;

    @FXML
    private Label bonjour;
    private int id;

    public int getId() {
        return id;
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Accueil.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnClub) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/club.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (actionEvent.getSource() == btnStage) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Stage.fxml"));
                Parent root = loader.load();
                StageController controller=loader.getController();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnCours) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Front_Etud.fxml"));
                Parent root = loader.load();
                FrontEtud controller=loader.getController();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnEntretien) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Entretien.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btndiscussion) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnQuizz) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Quizzes.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnotherQuizz) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/otherQuizzes.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void voiceAssistant(ActionEvent actionEvent) {
        System.out.println("voice asistant clicked");

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/VoiceAssistantpopup.fxml"));
            AnchorPane popupContent = loader.load();

            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            VoiceAssistantpopup controller = loader.getController();

            controller.setPopup(popup);
            Stage primaryStage = (Stage) btnOverview.getScene().getWindow();
            popup.show(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void GoDiscussion(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtilisateurService u = new UtilisateurService();
        Utilisateur user = u.getUserByEmail(UserSession.userName);
        nomUser.setText(user.getNom()+ " " +user.getPrenom());
        bonjour.setText("Bonjour, "+user.getNom()+" "+user.getPrenom()+" dans Espritify!");

    }
}
