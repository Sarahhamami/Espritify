package Controllers;

import Controllers.Admin.OverViewController;
import Controllers.Enseignant.AccueilController;
import Controllers.Enseignant.FrontProf;
import Controllers.Etudiant.Accueil;
import entities.UserSession;
import helper.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import service.UtilisateurService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AccuielController implements Initializable {
    private final UtilisateurService us=new UtilisateurService();

    @FXML
    private Button rest;

    @FXML
    private Button btn;
    @FXML
    private Button btnaz;

    @FXML
    private TextField mail;


    @FXML
    private PasswordField password;
    Window window;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    void AjouterUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
        Parent root=loader.load();
        AjouterUserController auc=loader.getController();
        btnaz.getScene().setRoot(root);


    }


    @FXML
    void gotoConfimer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Email.fxml"));
        Parent root=loader.load();
        confirmController auc=loader.getController();
        rest.getScene().setRoot(root);
    }


    void goToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Accueil.fxml"));
        Parent root=loader.load();
       Accueil a =loader.getController();
        rest.getScene().setRoot(root);
    }
    void goToResponsable() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Responsable.fxml"));
        Parent root=loader.load();
        ResponssableController auc=loader.getController();
        rest.getScene().setRoot(root);
    }

    void goToEnseignant() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Enseignant/front_prof.fxml"));
        Parent root=loader.load();
        FrontProf auc=loader.getController();

        rest.getScene().setRoot(root);

    }
    void goAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/OverView.fxml"));
        Parent root=loader.load();
        OverViewController auc=loader.getController();

        rest.getScene().setRoot(root);

    }

    @FXML
    void login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
        Parent root = loader.load();
        us.displayAll();
      //  UserSession.cleanUserSession();
        AfficherUserController auc = loader.getController();
        if (us.authenticate(mail.getText(), password.getText()) != 0) {
            UserSession u =UserSession.getInstace( mail.getText(), us.role(us.authenticate(mail.getText(), password.getText())));
            System.out.println(u);
            if (us.role(us.authenticate(mail.getText(), password.getText())).equals("etudiant")) {
//                auc.setAfficherTF(" bienvnu etudiant");
//                btn.getScene().setRoot(root);
                goToHome();
             }else if (us.role(us.authenticate(mail.getText(), password.getText())).equals("admin")) {
//                auc.setAfficherTF(" bienvnu etudiant");
//                btn.getScene().setRoot(root);
                  goAdmin();
            }

            else if (us.role(us.authenticate(mail.getText(), password.getText())).equals("responsable_societe")) {
               //auc.setAfficherTF(" bienvnu responsable societe");
              //btn.getScene().setRoot(root);
                goToResponsable();


            } else {
                goToEnseignant();
            }
        } else if (mail.getText().equals("") || password.getText().equals("")) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "insert l'email et mot de passe.");
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Invalid email and password.");

        }
    }




}


