package Controllers.Admin;

import entities.Reclamation;
import entities.Reponse_rec;
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
import services.ReponseRecService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReponseReclamationController implements Initializable {

    ReponseRecService rrs = new ReponseRecService();
    @FXML
    private Label TotalStage;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnPackages;

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

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        refresh();
    }

    public void refresh() {
        List<Reponse_rec> RR = rrs.readAll();

        for (int i = 0; i < RR.size(); i++) {
            try {
                final int j = i;
                Reponse_rec RepReclamation = RR.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/itemRep.fxml"));
                Node node = loader.load();

                ((Label) node.lookup("#titrerec")).setText(RepReclamation.getTitre());
                ((Label) node.lookup("#titrerep")).setText(RepReclamation.getTitre_rec());
                ((Label) node.lookup("#descriptionrep")).setText(RepReclamation.getDesc());
                ((Label) node.lookup("#data")).setText(RepReclamation.getDate());

                ItemRepController irepc=loader.getController();
                irepc.setid_rep(RepReclamation.getId());

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
    void ReponseRec(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
        Parent root = loader.load();
        ReponseReclamationController ars = loader.getController();
        filterField.getScene().setRoot(root);

    }

    @FXML
    void clickAdd(ActionEvent event) {

    }

    @FXML
    void search() {
        List<Reponse_rec> listRep = rrs.readAll();
        ObservableList<Reponse_rec> observableList = FXCollections.observableList(listRep);
        FilteredList<Reponse_rec> filteredData = new FilteredList<>(observableList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Reponse_rec -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(Reponse_rec.getId()).toLowerCase().contains(lowerCaseFilter)
                        || Reponse_rec.getTitre().toLowerCase().contains(lowerCaseFilter)
                        || Reponse_rec.getDesc().toLowerCase().contains(lowerCaseFilter)
                        || Reponse_rec.getTitre_rec().toLowerCase().contains(lowerCaseFilter)
                        ;
            });

            // Clear previous items in the UI
            pnItems.getChildren().clear();

            // Update UI with the filtered data
            for (Reponse_rec Rep : filteredData) {
                // Initialize node for each item
                Node node = null; // Update this line based on your actual FXML structure
                try {
                    node = FXMLLoader.load(getClass().getResource("/Admin/ItemRep.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (node != null) {
                    ((Label) node.lookup("#titrerec")).setText(Rep.getTitre_rec());
                    ((Label) node.lookup("#titrerep")).setText(Rep.getTitre());
                    ((Label) node.lookup("#descriptionrep")).setText(Rep.getDesc());
                    ((Label) node.lookup("#data")).setText(Rep.getDate());
                    // Update other fields as needed
                    pnItems.getChildren().add(node); // Add the node to the UI
                }
            }
        });
    }
    @FXML
    void Reclamation(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
        Parent root =loader.load();
        ReclamationController rc=loader.getController();
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
    public void gooverview(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/OverView.fxml"));
        Parent root =loader.load();
        OverViewController ars=loader.getController();
        filterField.getScene().setRoot(root);
    }
    @FXML
    public void gogestionstage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Stage.fxml"));
        Parent root =loader.load();
        StageController ars=loader.getController();
        filterField.getScene().setRoot(root);
    }
    @FXML
    public void godossierstage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/DossierStage.fxml"));
        Parent root =loader.load();
        DossierStage ars=loader.getController();
        filterField.getScene().setRoot(root);
    }
    @FXML
    public void goentretien(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Entretien.fxml"));
        Parent root =loader.load();
        EntretienController ars=loader.getController();
        filterField.getScene().setRoot(root);
    }

    public void godisc(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
        Parent root =loader.load();
        GestionMessagerie ars=loader.getController();
        filterField.getScene().setRoot(root);
    }
}
