package Controllers;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.UtilisateurService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class confirmController {

    @FXML
    private Button btnrez;

    @FXML
    private TextField mail;

    @FXML
    void cof(ActionEvent event) throws IOException {
        showForgotPasswordDialog();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResetPassword.fxml"));
        Parent root=loader.load();
        ResetPasswordController auc=loader.getController();
        btnrez.getScene().setRoot(root);
    }

    private void showForgotPasswordDialog() {



            // Do something with the username string
            // Validate the user input
            if (isValidEmail(mail.getText())) {
                // Look up the user's account information in the database
                UtilisateurService su = new UtilisateurService();
                Utilisateur user = su.getUserByEmail(mail.getText());

                if (user != null) {
                    // Generate a unique token or code and store it in the database with the user's ID and a timestamp
                    String token = generateUniqueToken();
                    long timestamp = System.currentTimeMillis();
                    su.insertPasswordResetToken(user.getId(), token, timestamp, mail.getText());

                    // Send an email to the user with a link to a new window or dialog box that allows the user to enter and confirm a new password
                    sendPasswordResetEmail(user.getEmail(), token);

                    // Display a message to the user indicating that an email has been sent
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Reset");
                    alert.setHeaderText("Email Sent");
                    alert.setContentText("Please check your email for instructions on resetting your password.");
                    alert.showAndWait();

//                    // Show a dialog box to allow the user to enter the code and their new password
//                    TextInputDialog resetDialog = new TextInputDialog();
//                    resetDialog.setTitle("Reset Password");
//                    resetDialog.setHeaderText("Enter the code sent to your email and your new password");
//
//                    resetDialog.setContentText("Code:");

//                    Optional<String> resetResult = resetDialog.showAndWait();


                }}

    }

    private void sendPasswordResetEmail(String email, String code) {
        // Use a JavaMail library or API to send an email to the user with a link to a new window or dialog box that allows the user to enter and confirm a new password
        // You will need to configure the email server and credentials for your application
        // Here is an example using the JavaMail API:
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("abdeljawed.chlibi@esprit.tn", "azdjhfpnlfepmabo");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("abdeljawed.chlibi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Password Reset");
            message.setText("Please enter the following code to reset your password:\n\n"
                    + code);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidCode(String code) {
// Regular expression pattern for validating password reset codes
        String trimmedCode = code.trim();
        String pattern = "[a-zA-Z0-9]{6}";

        // Validate the code against the pattern
        return trimmedCode.matches(pattern);
    }

    public boolean isValidEmail(String email) {
//        try {

//            new InternetAddress(email, true).validate();
            return true;
//        } catch (AddressException e) {
//            return true;
//        }
    }

    private String generateUniqueToken() {
// Generate a random alphanumeric string of length 32
        String characters = "0123456789";
        StringBuilder token = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            token.append(characters.charAt(index));
        }
        return token.toString();
    }

}
