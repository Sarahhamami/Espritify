package Controllers.Admin;

import Controllers.Etudiant.Accueil;
import Controllers.Etudiant.EntretienController;
import Controllers.Etudiant.FrontEtud;
import Controllers.Etudiant.StageController;
import entities.Conversation;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.BadWordFilterService;
import services.ConversationService;
import services.MessageService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class DiscussionInternController implements Initializable {
    ConversationService cs=new ConversationService();
    MessageService ms=new MessageService();
    String[] badWordsArray = {"3asba", "nayek", "maboun","fuck","pute","karazt","5ra","zabour","zebi","asba","karazt","fucker","motherfucker","suck","dick"};
    BadWordFilterService filter = new BadWordFilterService(badWordsArray);

    ItemConv ic=new ItemConv();

    @FXML
    private Button btnCustomers;


    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnRec;

    @FXML
    private Button btnReponseRec;

    @FXML
    private Button btnSettings;
    @FXML
    private Button btnCours;

    @FXML
    private Button btnSignout;

    @FXML
    private Button undobtn;


    @FXML
    private VBox pnItems;


    public void setPnItems(VBox pnItems) {
        this.pnItems = pnItems;
    }

    public VBox getPnItems() {
        return pnItems;
    }

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;
    @FXML
    private Label discussion;

    @FXML
    private Button btnEntretien;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btndiscussion  ;

    @FXML
    private Button btnQuizz;

    @FXML
    private Button btnotherQuizz;
    @FXML
    private Button btnClub;

    @FXML
    private Button btnStage;

    public Label getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Label discussion) {
        this.discussion = discussion;
    }


    @FXML
    void clickAdd(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Conversation> c= cs.readAll();

        for (int i = 0; i < c.size(); i++) {
            try {
                final int j = i;
                Conversation Conversation = c.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/itemConv.fxml"));
                Node node = loader.load();

                ((Label) node.lookup("#nom")).setText(Conversation.getNom_user() +"  "+ Conversation.getPrenom_user());
                ((Label) node.lookup("#desc")).setText(Conversation.getDescription());
                ((Label) node.lookup("#titre")).setText(Conversation.getTitre());
                ((Label) node.lookup("#date")).setText(Conversation.getDate());
                ((Label) node.lookup("#like")).setText(Conversation.getLikes()+" "+"likes");

                ItemConv irc=loader.getController();
                irc.setId_conv(Conversation.getId());
                irc.setId_user(Conversation.getId_user());
                if(Conversation.getId_user()==56) {

                    irc.getModifierbutton().setVisible(true);
                    irc.getSupprimer().setVisible(true);
                }
                pnItems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //zone commentaire
        TextField addCnnvdesc = new TextField();
        TextField addCnvtitre = new TextField();
        addCnvtitre.setPromptText("inserer le titre ...");
        addCnnvdesc.setPromptText("Ecrire Votre Commentaire ...");
        addCnvtitre.setStyle("-fx-background-color: transparent;  -fx-border-color: #990000;-fx-border-width: 0px 0px 2px 0px;");
        addCnnvdesc.setStyle("-fx-background-color: transparent; -fx-border-color: #990000; -fx-border-width: 0px 0px 2px 0px;");
        addCnvtitre.setMaxWidth(200);

        Button addCommentButton = new Button("Lancer une discussion");

        addCommentButton.setOnAction(e -> {
            if(addCnvtitre.getText().isEmpty()||addCnnvdesc.getText().isEmpty())
            {
                discussion.setText("Veuillez insere le titre et la description");
                discussion.setStyle("-fx-text-fill: red;");


                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(ee -> {

                    discussion.setText("Discussion");
                    discussion.setStyle("-fx-text-fill: black;");
                });
                pause.play();
            } else if (filter.hasBadWord(addCnvtitre.getText())|| filter.hasBadWord(addCnnvdesc.getText())) {
                discussion.setText("Contenu inapproprié détecté!");
                discussion.setStyle("-fx-text-fill: red;");

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(ee -> {
                    discussion.setText("Discussion");
                    discussion.setStyle("-fx-text-fill: black;");
                });
                pause.play();
            } else {
                cs.add(new Conversation(56, addCnvtitre.getText(), addCnnvdesc.getText()));
                addCnnvdesc.clear();
                addCnvtitre.clear();
            }
        });
        VBox addCommentBox = new VBox(addCnvtitre,addCnnvdesc, addCommentButton);

        addCommentBox.setSpacing(15);
        pnItems.getChildren().add(addCommentBox);


    }

    @FXML
    void undo(ActionEvent event) throws IOException {


        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
        Parent root =loader.load();
        DiscussionInternController ars=loader.getController();
        undobtn.getScene().setRoot(root);


        undobtn.setVisible(false);
    }

    public void showundobtn(){
        undobtn.setVisible(true);
    }

    @FXML
    void envoyerRec(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ReclamerPU.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            ReclamerController controller = loader.getController();
            controller.setPopup(popup);
            Stage primaryStage = (Stage) discussion.getScene().getWindow();
            popup.show(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Accueil.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (actionEvent.getSource() == btnStage) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Stage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnCours) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Front_Etud.fxml"));
                Parent root = loader.load();
                FrontEtud controller=loader.getController();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnClub) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/club.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnEntretien) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Entretien.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btndiscussion) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnQuizz) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Quizzes.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnotherQuizz) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/otherQuizzes.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

