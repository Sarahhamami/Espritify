package Controllers.Admin;

import entities.Conversation;
import entities.Reclamation;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ConversationService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionMessagerie implements Initializable {

    private static ConversationService rs=new ConversationService();

    private int id_msg;

    public int getId_msg() {
        return id_msg;
    }

    public void setId_msg(int id_msg) {
        this.id_msg = id_msg;
    }

    @FXML
    private Button undobtn;

    @FXML

private Label TotalStage;

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
private Button btnSignout;

@FXML
private TextField filterField;

@FXML
private VBox pnItems;

    public VBox getPnItems() {
        return pnItems;
    }
    public void setPnItems(VBox pnItems) {
        this.pnItems = pnItems;
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
    private Button btnOverview;
    @FXML
    private Button btnStage;
    @FXML
    private Button btnQuestion;
    @FXML
    private Button btnQuizz;
    @FXML
    private Button btnDossierStage;
    @FXML
    private Button btnEntretien;
    @FXML
    private Button btnRepRec;
    @FXML
    private Button btnMessg;
    @FXML
    private Button btnCours;
    @FXML
    private Button btnClub;
    @FXML
    private Button btnParticipant;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnCategorie;

    @FXML
    public void handleClicks(ActionEvent actionEvent) {

        // this will be transformed based on the work of each button
        if (actionEvent.getSource() == btnDossierStage) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/DossierStage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnRec) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnRepRec) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnMessg) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
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

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AfficherQuizz.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnQuestion) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AfficherQuestion.fxml"));
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

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Entretien.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == btnOverview) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/OverView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == btnParticipant) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AdminParticipants.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == btnEvent) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AdminEvenement.fxml"));
                Parent root = loader.load();
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

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AdminClub.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource()==btnStage)
        {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Stage.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
        if(actionEvent.getSource()==btnCours)
        {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AfficherCours.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
        if(actionEvent.getSource()==btnCategorie)
        {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AfficherCategorie.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
    }
    @FXML
    private Label titre;

    public Label getTitre() {
        return titre;
    }

    public void setTitre(Label titre) {
        this.titre = titre;
    }

    @FXML
    void GoDiscussion(ActionEvent event) {

            }

@FXML
    void GoGestMesg(ActionEvent event) {

            }

@FXML
    void Reclamation(ActionEvent event) throws IOException {

    FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
    Parent root =loader.load();
    ReclamationController ars=loader.getController();
    pnItems.getScene().setRoot(root);
            }

@FXML
    void ReponseRec(ActionEvent event) throws IOException {
    FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
    Parent root =loader.load();
    ReponseReclamationController ars=loader.getController();
    pnItems.getScene().setRoot(root);
            }

@FXML
    void clickAdd(ActionEvent event) {

            }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Conversation> R= rs.readAll();

        for (int i = 0; i < R.size(); i++) {
            try {
                final int j = i;
                Conversation Conversation = R.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ItemGestConv.fxml"));
                Node node = loader.load();

                ((Label) node.lookup("#nom")).setText(Conversation.getNom_user());
                ((Label) node.lookup("#prenom")).setText(Conversation.getPrenom_user());
                ((Label) node.lookup("#titre")).setText(Conversation.getTitre());
                ((Label) node.lookup("#description")).setText(Conversation.getDescription());
                ((Label) node.lookup("#date")).setText(Conversation.getDate());
                ItemGestConvController irc=loader.getController();
                irc.setId_conv(Conversation.getId());

                // Give the items some effect
                node.setOnMouseEntered(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });
                node.setOnMouseExited(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });

                pnItems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            //search(); // Call search method whenever text changes in the filterField
        });
    }


    @FXML
    void undo(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
        Parent root =loader.load();
        GestionMessagerie ars=loader.getController();
        undobtn.getScene().setRoot(root);

        undobtn.setVisible(false);

    }
    public void showundobtn(){
        undobtn.setVisible(true);
    }


}