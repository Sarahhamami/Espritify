package Controllers;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PasswordDialog extends Dialog<String> {

    public PasswordDialog() {
        setTitle("Reset Password");
        setHeaderText("Enter your new password");

        // Set the button types
        ButtonType resetButtonType = new ButtonType("Reset", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(resetButtonType, ButtonType.CANCEL);

        // Create the password fields and add them to the dialog
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Password:"), 0, 0);
        grid.add(passwordField, 1, 0);
        grid.add(new Label("Confirm Password:"), 0, 1);
        grid.add(confirmPasswordField, 1, 1);

        // Enable/disable the reset button depending on whether the password fields match
        Node resetButton = getDialogPane().lookupButton(resetButtonType);
        resetButton.setDisable(true);

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            resetButton.setDisable(!newValue.equals(confirmPasswordField.getText()));
        });

        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            resetButton.setDisable(!newValue.equals(passwordField.getText()));
        });

        getDialogPane().setContent(grid);

        setResultConverter(dialogButton -> {
            if (dialogButton == resetButtonType) {
                return passwordField.getText();
            }
            return null;
        });
    }
}