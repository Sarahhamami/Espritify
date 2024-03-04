package Controllers.Admin;

import com.twilio.Twilio;
import entities.Conversation;
import entities.Message;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ConversationService;
import services.MessageService;
import services.SMSSenderService;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
public class ItemConv implements Initializable {

    private static ConversationService cs=new ConversationService();
    private static MessageService ms=new MessageService();
    private DiscussionInternController ds;
    SMSSenderService smss=new SMSSenderService();
    public void setDiscussionInternController(DiscussionInternController ds) {
        this.ds = ds;
    }

    @FXML
    private Button supprimer;

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    @FXML
    private Label desc;

    public Label getDesc() {
        return desc;
    }

    public void setDesc(Label desc) {
        this.desc = desc;
    }

    @FXML
    private Button modifierbutton;
    public Button getModifierbutton() {
        return modifierbutton;
    }
    @FXML
    private Label date;
    @FXML
    private ToggleButton likebtn;

    @FXML
    private Label nom;

    @FXML
    private Label titre;

    private boolean liked=false;

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
                } else if(ds.filter.hasBadWord(addCommentTF.getText())) {

                    ds.getDiscussion().setText("Contenu inapproprié détecté!");
                    ds.getDiscussion().setStyle("-fx-text-fill: red;");

                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(ee -> {
                        ds.getDiscussion().setText("Discussion");
                        ds.getDiscussion().setStyle("-fx-text-fill: black;");
                    });
                    pause.play();

                } else{
                    ms.add(new Message(56, getId_conv(), addCommentTF.getText()));
                    Twilio.init(smss.ACCOUNT_SID, smss.AUTH_TOKEN);
                    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                                    new com.twilio.type.PhoneNumber("+21695103375"),
                                    new com.twilio.type.PhoneNumber("+19282385386"),
                                    "ahmed hmid a commenté sur votre publication ," +
                                            "veuillez consulter votre compte !"
                            )
                            .create();

                    System.out.println(message.getSid());
                }
            });
            VBox addCommentBox = new VBox(addCommentTF, addCommentButton);

            addCommentBox.setSpacing(15);
            ds.getPnItems().getChildren().add(addCommentBox);

    }

    @FXML
    private void like() {
        liked = !liked;

        String imagePath = liked ? "/images/coeur1.png" : "/images/coeur.png";

        try {
            URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                likebtn.setGraphic(new ImageView(new Image(imageUrl.toExternalForm())));

                // Update the likes column in the database
                cs.updateLikesInDatabase(liked,id_conv);
            } else {
                System.out.println("Image not found: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modifier(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/EditerCmntPU.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            EditerCmntController controller = loader.getController();
            controller.setPopup(popup);
            controller.getTitre().setText(titre.getText());
            controller.getDesc().setText(desc.getText());
            controller.setId_conv(id_conv);
            Stage primaryStage = (Stage) titre.getScene().getWindow();
            popup.show(primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void supprimer(ActionEvent event) throws IOException {

        cs.delete(new Conversation(id_conv));
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
        Parent root =loader.load();
        DiscussionInternController ars=loader.getController();
        titre.getScene().setRoot(root);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}
