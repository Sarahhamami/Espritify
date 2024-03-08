package Controllers;

import entities.PasswordResetToken;
import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import org.mindrot.jbcrypt.BCrypt;
import service.UtilisateurService;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ResetPasswordController implements Initializable{
    @FXML
    private Button resetPasswordButton;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private PasswordField code;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    @FXML
    private void resetPassword(ActionEvent event) throws IOException
    {


        UtilisateurService su = new UtilisateurService();

        String mail = su.getUserEmailByToken(code.getText());
        Utilisateur user = su.getUserByEmail(mail);
        // Validate the user input
        if (!isValidCode(code.getText().trim())) {
            // Look up the user's password reset token in the database

            PasswordResetToken resetToken = su.getPasswordResetToken(code.getText());
            System.out.println("Before");
            if (resetToken != null) {
                // Check if the token has expired
                long now = System.currentTimeMillis();
                long elapsedTime = now - resetToken.getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                long expiryTime = TimeUnit.MINUTES.toMillis(30); // Token expires after 30 minutes
                System.out.println("now: " + now + ", token timestamp: " + resetToken.getTimestamp() + ", elapsed time: " + elapsedTime);
                if (elapsedTime <= expiryTime) {
                    // Show a dialog box to allow the user to enter their new password
//                    PasswordDialog passwordDialog = new PasswordDialog();
//                    Optional<String> newPasswordResult = passwordDialog.showAndWait();

//                    if (newPasswordResult.isPresent()) {
//                        String newPassword = newPasswordResult.get();
//                    String hashedPassword = PasswordHasher.hashPassword(newPasswordField.getText());

                    // Update the user's password in the database
                    String hashedPassword = BCrypt.hashpw(newPasswordField.getText(), BCrypt.gensalt());
                    su.updateUserPassword(user.getId(), hashedPassword);

                    // Display a message to the user indicating that their password has been reset
//                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
//                    successAlert.setTitle("Password Reset");
//                    successAlert.setHeaderText("Password Reset Successful");
//                    successAlert.setContentText("Your password has been reset.");
//                    successAlert.showAndWait();
                } else {
                    // Display an error message if the token has expired
                    Alert expiredAlert = new Alert(Alert.AlertType.ERROR);
                    expiredAlert.setTitle("Password Reset");
                    expiredAlert.setHeaderText("Token Expired");
                    expiredAlert.setContentText("Sorry, the password reset token has expired.");
                    expiredAlert.showAndWait();
                }
            }
            else {
                // Display an error message if the user enters an invalid code

                Alert invalidCodeAlert = new Alert(Alert.AlertType.ERROR);
                invalidCodeAlert.setTitle("Password Reset");
                invalidCodeAlert.setHeaderText("Invalid Code");
                invalidCodeAlert.setContentText("Sorry, the code you entered is invalid.");
                invalidCodeAlert.showAndWait();
            }
        }
        reset();



        }


    public void reset() {

        String token = code.getText();
        UtilisateurService su = new UtilisateurService();
        // Verify that the token is valid and has not expired
        PasswordResetToken resetToken = su.getPasswordResetToken(token);
        System.out.println(resetToken == null || resetToken.isExpired());
        if (resetToken == null || resetToken.isExpired()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Reset");
            alert.setHeaderText("Invalid or Expired Token");
            alert.setContentText("Sorry, the password reset link you clicked is no longer valid. Please try again.");
            alert.showAndWait();
            return;
        }

        // Validate the user input to ensure the new password meets any requirements (e.g. minimum length, complexity)
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if (!isValidPassword(newPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Reset");
            alert.setHeaderText("Invalid Password");
            alert.setContentText("Sorry, the new password you entered is not valid. Please try again.");
            alert.showAndWait();
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Reset");
            alert.setHeaderText("Passwords do not match");
            alert.setContentText("Sorry, the new passwords you entered do not match. Please try again.");
            alert.showAndWait();
            return;
        }

        // Update the user's account information in the database with the new password
        su.updateUserPassword(resetToken.getUserId(), newPassword);

        // Display a success message to the user and prompt them to log in again using their new password
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Reset");
        alert.setHeaderText("Password reset successful");
        alert.setContentText("Your password has been reset. Please log in again using your new password.");
        alert.showAndWait();

        su.deleteToken(resetToken.getUserId());

        // Close the password reset screen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/acueil.fxml"));
            Parent root=loader.load();
            AccuielController auc=loader.getController();
            code.getScene().setRoot(root);
        } catch (IOException e) {

        }

    }

    private String getResetTokenFromURL() {
        // Parse the reset token from the URL of the password reset screen
        String url = getClass().getResource("../resources/ResetPassword.fxml").toString();
        int tokenIndex = url.indexOf("token=") + 6;
        return url.substring(tokenIndex);
    }

    private boolean isValidPassword(String password) {
        // Validate the new password to ensure it meets any requirements (e.g. minimum length, complexity)
        // You can customize this method to suit your application's password policy
     return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*\\d.*");
        //return  true;
    }

    private boolean isValidCode(String code) {
// Regular expression pattern for validating password reset codes
        String trimmedCode = code.trim();
        String pattern = "[a-zA-Z0-9]{6}";

        // Validate the code against the pattern
        return trimmedCode.matches(pattern);
    }


}

