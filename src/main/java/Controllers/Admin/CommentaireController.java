package Controllers.Admin;

import entities.Conversation;
import entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import services.MessageService;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CommentaireController implements Initializable {

    private static MessageService ms = new MessageService();
    @FXML
    private Button RepondreBT;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField titre;
    @FXML
    private VBox pnItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}