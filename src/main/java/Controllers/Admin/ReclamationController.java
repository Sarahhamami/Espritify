package Controllers.Admin;

import entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ReclamationService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReclamationController implements Initializable {
    public static ReclamationService rs=new ReclamationService();

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


    @FXML
    void clickAdd(ActionEvent event) {

    }
    @FXML
    private Button btnReponseRec;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
    public void refresh(){
        List<Reclamation> R= rs.readAll();

        for (int i = 0; i < R.size(); i++) {
            try {
                final int j = i;
                Reclamation Reclamation = R.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/itemRec.fxml"));
                Node node = loader.load();

                ((Label) node.lookup("#nom")).setText(Reclamation.getNom_user());
                ((Label) node.lookup("#prenom")).setText(Reclamation.getPrenom_user());
                ((Label) node.lookup("#titre")).setText(Reclamation.getTitre());
                ((Label) node.lookup("#description")).setText(Reclamation.getDescription());
                ((Label) node.lookup("#date")).setText(Reclamation.getDate());
                ((Label) node.lookup("#etat")).setText(Reclamation.getEtat());
                ItemRecController irc=loader.getController();
                irc.setid_Sup(Reclamation.getId());
                irc.setId_user_reponse(Reclamation.getId());
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
            search(); // Call search method whenever text changes in the filterField
        });
    }
    @FXML
    void search() {
        List<Reclamation> listRec = rs.readAll();
        ObservableList<Reclamation> observableList = FXCollections.observableList(listRec);
        FilteredList<Reclamation> filteredData = new FilteredList<>(observableList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(reclamation.getId()).toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getTitre().toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getNom_user().toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getPrenom_user().toLowerCase().contains(lowerCaseFilter)
                        ;
            });

            // Clear previous items in the UI
            pnItems.getChildren().clear();

            // Update UI with the filtered data
            for (Reclamation Rec : filteredData) {
                // Initialize node for each item
                Node node = null; // Update this line based on your actual FXML structure
                try {
                    node = FXMLLoader.load(getClass().getResource("/Admin/ItemRec.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (node != null) {
                    ((Label) node.lookup("#nom")).setText(Rec.getNom_user());
                    ((Label) node.lookup("#prenom")).setText(Rec.getPrenom_user());
                    ((Label) node.lookup("#titre")).setText(Rec.getTitre());
                    ((Label) node.lookup("#description")).setText(Rec.getDescription());
                    ((Label) node.lookup("#date")).setText(Rec.getDate());
                    ((Label) node.lookup("#etat")).setText(Rec.getEtat());
                    // Update other fields as needed
                    pnItems.getChildren().add(node); // Add the node to the UI
                }
            }
        });
    }

    @FXML
    void ReponseRec(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
        Parent root =loader.load();
        ReponseReclamationController ars=loader.getController();
        filterField.getScene().setRoot(root);

    }

    @FXML
    void Reclamation(ActionEvent event) throws IOException {


        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
        Parent root =loader.load();
        ReclamationController ars=loader.getController();
        filterField.getScene().setRoot(root);

    }


    @FXML
    void GoDiscussion(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
        Parent root =loader.load();
        DiscussionInternController ars=loader.getController();
        filterField.getScene().setRoot(root);

    }

    @FXML
    void GoGestMessagerie(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
        Parent root =loader.load();
        GestionMessagerie ars=loader.getController();
        filterField.getScene().setRoot(root);

    }


}
