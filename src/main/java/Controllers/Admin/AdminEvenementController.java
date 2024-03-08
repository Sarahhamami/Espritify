package Controllers.Admin;

import entities.Club;
import entities.Evenement;
import entities.OffreStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.EvenementService;

public class AdminEvenementController implements Initializable{

    @FXML
    private TextField filterField;
    @FXML
    private VBox pnItems ;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

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
    private AnchorPane Anchor;
    public Button getBtnOrders() {
        return btnOrders;
    }
    private final EvenementService es = new EvenementService();

    private EvenementService evenementService = new EvenementService();
    private Club selectedClub;
 ItemClubController ic = new ItemClubController();
    AdminClubController ac = new AdminClubController();

    @FXML
    private AnchorPane itemClubPane; // Assuming you have an AnchorPane in your AdminEvenement.fxml to load itemClub.fxml


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Evenement> e = es.readAll();
        for (Evenement evenement : e) {
            // Créez un FXMLLoader pour charger le fichier FXML de l'élément de club
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ItemEvenement.fxml"));
            HBox item = null;
            Node node = null;

            try {
                item = loader.load();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Obtenez une référence au contrôleur ItemClubController
            ItemEvenementController controller = loader.getController();

            // Définissez l'ID du club pour cet élément
            controller.setEvenementId(evenement.getId());
            controller.setEvenement(evenement);

            // Ajoutez l'élément à votre liste de clubs
            pnItems.getChildren().add(item);
        }


        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            search(); // Call search method whenever text changes in the filterField
        });


    }

    // This method is called from AdminClubController to pass the selected club
    public void setSelectedClub(Club club) {
        Club selectedClub = ic.getC();
        loadEventsForClub(selectedClub);
    }
    private void loadEventsForClub(Club club) {
        ObservableList<Evenement> events = evenementService.getEventsForClub(club);
        displayEvents(events);
    }

    private void displayEvents(ObservableList<Evenement> events) {
        pnItems.getChildren().clear(); // Clear previous items in the UI
        for (Evenement evenement : events) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ItemEvenement.fxml"));
                Node node = loader.load();

                // Set event details to UI components in the node
                ((Label) node.lookup("#titre")).setText(evenement.getTitre());
                ((Label) node.lookup("#description")).setText(evenement.getDescription());
                // Set other event details as needed

                pnItems.getChildren().add(node); // Add the node to the UI
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void search() {
        List<Evenement> listE = es.readAll();
        ObservableList<Evenement> observableList = FXCollections.observableList(listE);
        FilteredList<Evenement> filteredData = new FilteredList<>(observableList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(evenement -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(evenement.getId()).toLowerCase().contains(lowerCaseFilter)
                        || evenement.getTitre().toLowerCase().contains(lowerCaseFilter)
                        || evenement.getDescription().toLowerCase().contains(lowerCaseFilter)   || evenement.getLieu().toLowerCase().contains(lowerCaseFilter);
            });

            // Clear previous items in the UI
            pnItems.getChildren().clear();

            // Update UI with the filtered data
            for (Evenement evenement : filteredData) {
                // Initialize node for each item
                Node node = null; // Update this line based on your actual FXML structure
                try {
                    node = FXMLLoader.load(getClass().getResource("/Admin/ItemEvenement.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (node != null) {
                    ((Label) node.lookup("#titre")).setText(evenement.getTitre());
                    ((Label) node.lookup("#description")).setText(evenement.getDescription());

                    // Update other fields as needed
                    pnItems.getChildren().add(node); // Add the node to the UI
                }
            }
        });
    }




    @FXML
    void AjouterEvenement(ActionEvent event) {
        try {
            // Load the FXML file for the popup content
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ajouterEvenement.fxml"));
            BorderPane popupContent = loader.load();

            // Create the popup and set its content
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            AjouterEvenementController controller = loader.getController();
            controller.setPopup(popup);

            // Obtain the primary stage
            Stage primaryStage = (Stage) btnDossierStage.getScene().getWindow();

            // Show the popup
            popup.show(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}



