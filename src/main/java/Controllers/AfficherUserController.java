package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AfficherUserController {

    @FXML
    private TextField AfficherTF;

    public void setAfficherTF(String afficherTF) {
        this.AfficherTF.setText(afficherTF);
    }
}
