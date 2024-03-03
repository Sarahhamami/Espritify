package Controllers;

import entities.Utilisateur;
import helper.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import service.UtilisateurService;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.util.regex.Pattern;

public class AjouterUserController {

    private final UtilisateurService us=new UtilisateurService();
    @FXML
    private RadioButton ensi;

    @FXML
    private RadioButton etu;
    @FXML
    private RadioButton rs;

    @FXML
    private TextField emailTF;

    @FXML
    private PasswordField mdpTF;

    @FXML
    private TextField niveauTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private TextField telTF;
    @FXML
    private TextField IDFX;
    Window window;
    @FXML
    void AjouterUser(ActionEvent event) throws IOException {

        if(!patternMatches(emailTF.getText())){
            AlertHelper.showAlert(Alert.AlertType.ERROR,  window, "Error",
                    "Invalid email .");
        }
        if(!isNumeric(telTF.getText())){
            AlertHelper.showAlert(Alert.AlertType.ERROR,  window, "Error",
                    "numero de telephone Invalid .");
            return;
        }
        if((emailTF.getText().equals("") &&mdpTF.getText().equals("")&&niveauTF.getText().equals("")&&nomTF.getText().equals(""))){
            AlertHelper.showAlert(Alert.AlertType.ERROR,  window, "Error",
                    "pas de champ vide.");
     return;
        }
if (us.exsitemail(emailTF.getText())){
            AlertHelper.showAlert(Alert.AlertType.ERROR,  window, "Error",
                    "user deja existe.");
        }
else {
    if (rs.isSelected()) {
        us.add(new Utilisateur(nomTF.getText(), prenomTF.getText(), emailTF.getText(), mdpTF.getText(), Integer.parseInt(telTF.getText()), niveauTF.getText(), "responsable_societe"));
    }

    if (ensi.isSelected()) {
        us.add(new Utilisateur(nomTF.getText(), prenomTF.getText(), emailTF.getText(), mdpTF.getText(), Integer.parseInt(telTF.getText()), niveauTF.getText(), "admin"));
    }

    if (etu.isSelected()) {
        us.add(new Utilisateur(nomTF.getText(), prenomTF.getText(), emailTF.getText(), mdpTF.getText(), Integer.parseInt(telTF.getText()), niveauTF.getText(), "etudiant"));
    }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/acueil.fxml"));
    Parent root = loader.load();
    AccuielController auc = loader.getController();
    nomTF.getScene().setRoot(root);

}
    }

    @FXML
    void supprimer(ActionEvent event) {

        us.delete(new Utilisateur(Integer.parseInt(IDFX.getText())));
    }
    public boolean patternMatches(String emailAddress) {
          final String regex = "^(.+)@(.+)$";
        return Pattern.compile(regex) .matcher(emailAddress) .matches(); }

    public static boolean isNumeric(String str) {
        if (str == null) return false; // handle null-pointer
        if (str.length() == 0) return false; // handle empty strings
        // to make sure that the input is numeric, we have to go through the characters
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false; // we found a non-digit character so we can early return
        }
        return true;
    }


}
