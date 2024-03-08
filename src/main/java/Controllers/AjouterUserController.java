package Controllers;

import entities.Utilisateur;
import helper.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.UtilisateurService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
        us.add(new Utilisateur(nomTF.getText(), prenomTF.getText(), emailTF.getText(), imagePath, "responsable_societe" , Integer.parseInt(telTF.getText()), mdpTF.getText() ));
    }

    if (ensi.isSelected()) {
        us.add(new Utilisateur(nomTF.getText(), prenomTF.getText(), emailTF.getText(), imagePath, "admin" , Integer.parseInt(telTF.getText()), mdpTF.getText()));
    }

    if (etu.isSelected()) {
        us.add(new Utilisateur(nomTF.getText(), prenomTF.getText(), emailTF.getText(), imagePath, "etudiant" , Integer.parseInt(telTF.getText()), mdpTF.getText()));
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
    @FXML
    private Button imageButton;
    private Stage stage;
    private String imagePath;
    @FXML
    private TextField imagePathTextField;
    public void uploadImage(ActionEvent actionEvent) {


            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

            File selectedFile = fileChooser.showOpenDialog(stage); // Use the stage passed from the main application
            if (selectedFile != null) {
                // Get the target resource directory path
                String resourcePath = "C:/xampp/htdocs/"; // Modify this according to your project structure
                File resourceDirectory = new File(resourcePath);

                // Ensure the resource directory exists, create if necessary
                if (!resourceDirectory.exists()) {
                    resourceDirectory.mkdirs();
                }
                if (actionEvent.getSource()==imageButton) {
                    imagePath = resourcePath + selectedFile.getName();
                    File destinationFile = new File(imagePath);

                    try {
                        // Copy the selected file to the resource directory
                        Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Image uploaded to: " + imagePath);
                        imagePathTextField.setText(selectedFile.getName());

                        // Optionally, load and display the image
                        Image image = new Image(destinationFile.toURI().toString());
                        ImageView imageView = new ImageView(image);
                        // You can add this imageView to your layout for display
                        displayImage(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }}


        }
    private void displayImage(Image image) {
        // Create an ImageView to display the image
        ImageView imageView = new ImageView(image);

        // Create a new stage to display the image
        Stage imageStage = new Stage();
        imageStage.setTitle("Selected Image");
        imageStage.setScene(new Scene(new Group(imageView), image.getWidth(), image.getHeight()));
        imageStage.show();
    }

}
