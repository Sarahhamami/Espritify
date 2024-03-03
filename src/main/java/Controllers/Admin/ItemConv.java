package Controllers.Admin;

import entities.Conversation;
import entities.Message;
import entities.Reclamation;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.MessageService;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ItemConv implements Initializable {

private static MessageService ms=new MessageService();
    private DiscussionInternController ds;


    public void setDiscussionInternController(DiscussionInternController ds) {
        this.ds = ds;
    }

    @FXML
    private Label date;

    @FXML
    private Label nom;

    @FXML
    private Label titre;

    private int id_conv;
    private int id_user;

    public int getId_conv() {
        return id_conv;
    }

    public void setId_conv(int id_conv) {
        this.id_conv = id_conv;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @FXML
        void commenter(ActionEvent event) throws IOException {


            FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
            Parent root =loader.load();
            DiscussionInternController ars=loader.getController();
            setDiscussionInternController(ars);  // Set the controller in ItemConv
            date.getScene().setRoot(root);
            ds.getPnItems().getChildren().clear();
            ds.showundobtn();


            List<Message> c= ms.readAllByID(getId_conv());

            for (int i = 0; i < c.size(); i++) {
                try {

                    Message Message = c.get(i);
                    FXMLLoader Itemmoader = new FXMLLoader(getClass().getResource("/Admin/ItemCommentaire.fxml"));
                    Node node = Itemmoader.load();
                    ((Label) node.lookup("#nom")).setText(Message.getNom_commentor()+" "+Message.getPrenom_commentor());
                    ((Label) node.lookup("#date")).setText(Message.getDate());
                    ((Label) node.lookup("#description")).setText(Message.getDescription());
                    ds.getPnItems().getChildren().add(node);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //zone commentaire
            TextField addCommentTF = new TextField();
            addCommentTF.setPromptText("Ecrire Votre Commentaire ...");
            addCommentTF.setStyle("-fx-background-color: transparent; -fx-border-color: #990000; -fx-border-width: 0px 0px 2px 0px;");

            Button addCommentButton = new Button("commenter");

            addCommentButton.setOnAction(e -> {
                if (addCommentTF.getText().isEmpty()) {
                    ds.getDiscussion().setText("Veuillez insere Votre Commentaire");
                    ds.getDiscussion().setStyle("-fx-text-fill: red;");
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(ez -> {

                        ds.getDiscussion().setText("Discussion");
                        ds.getDiscussion().setStyle("-fx-text-fill: black;");
                    });
                    pause.play();
                }

                else{
                    ms.add(new Message(56, getId_conv(), addCommentTF.getText()));
                    addCommentTF.clear();
                }
            });
            VBox addCommentBox = new VBox(addCommentTF, addCommentButton);

            addCommentBox.setSpacing(15);
            ds.getPnItems().getChildren().add(addCommentBox);



    }



    @FXML
    void like(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }


}
