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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private Button btnRec;
    @FXML
    private Button btnRepRec;
    @FXML
    private Button btnMessg;
    @FXML
    private Button btnCours;
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

}
