package Controllers.Admin;

import entities.Evenement;
import entities.Participation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ParticipationService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminParticipantController implements Initializable {


    @FXML
    private AnchorPane Anchor;

    @FXML
    private Label TotalStage;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private TextField filterField;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;


    ParticipationService ps = new ParticipationService();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Participation> p = ps.getParticipants();

        for (int i = 0; i < p.size(); i++) {
            try {
                final int j = i;
                Participation participant = p.get(i);
                Node node = FXMLLoader.load(getClass().getResource("/Admin/participantItem.fxml"));


               // ((Label) node.lookup("#id_user")).setText(String.valueOf(participant.getId_user()));
               // ((Label) node.lookup("#id_event")).setText(String.valueOf(participant.getId_evenement()));
                ((Label) node.lookup("#nom")).setText(participant.getNomUtilisateur());
                ((Label) node.lookup("#email")).setText(participant.getEmailUtilisateur());
                ((Label) node.lookup("#titre")).setText(participant.getTitreEvenement());
                ((Label) node.lookup("#lieu")).setText(participant.getLieuEvenement());
                ((Label) node.lookup("#datedebut")).setText(String.valueOf(participant.getDateDebut()));
                ((Label) node.lookup("#datefin")).setText(String.valueOf(participant.getDateFin()));

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


    }
    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        // this will be transformed based on the work of each button
        if (actionEvent.getSource() == btnCustomers) {


        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            // pnlOverview.setStyle("-fx-background-color : #02030A");
            //  pnlOverview.toFront();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Admin/AdminClub.fxml"));
                Node node = null;

                // node = loader.load();
                Parent root = loader.load();
                AdminClubController ac = loader.getController();
                pnItems.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if(actionEvent.getSource()==btnOrders)
        {
            //    pnlOrders.setStyle("-fx-background-color : #464F67");
            //   pnlOrders.toFront();
            //loadEvenementContent();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Admin/AdminEvenement.fxml"));
                Node node = null;

                // node = loader.load();
                Parent root = loader.load();
                AdminEvenementController ec = loader.getController();
                pnItems.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
