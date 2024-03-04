package Controllers.Admin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import entities.Club;
import entities.Evenement;
import entities.OffreStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.List;
import java.util.ResourceBundle;

import javafx.stage.Popup;
import javafx.stage.Stage;
import services.ClubService;
import services.EvenementService;


public class AdminClubController implements Initializable{

    @FXML
    private Button listEvents;

    @FXML
    private HBox itemC;

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
    private AnchorPane Anchor;
    @FXML
    private ImageView qrCodeImageView;



    private final ClubService cs = new ClubService();

    private ItemClubController ic = new ItemClubController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        // this will be transformed based on the work of each button
        if (actionEvent.getSource() == btnCustomers) {
           //loadParticipantContent();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Admin/AdminParticipants.fxml"));
                Node node = null;

                // node = loader.load();
                Parent root = loader.load();
                AdminParticipantController pc = loader.getController();
                pnItems.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (actionEvent.getSource() == btnMenus) {
            // pnlMenus.setStyle("-fx-background-color : #53639F");
            //pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            // pnlOverview.setStyle("-fx-background-color : #02030A");
            //  pnlOverview.toFront();
          //  loadAdminClubContent();


        }
         if(actionEvent.getSource()==btnOrders)
        {
            //    pnlOrders.setStyle("-fx-background-color : #464F67");
            //   pnlOrders.toFront();
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
    public void initializeTable(){
        List<Club> o= cs.readAll();

        for (Club club : o) {
            // Créez un FXMLLoader pour charger le fichier FXML de l'élément de club
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ItemClub.fxml"));
            HBox item = null;
            Node node = null;

            try {
                item = loader.load();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Obtenez une référence au contrôleur ItemClubController
            ItemClubController controller = loader.getController();

            // Définissez l'ID du club pour cet élément
            controller.setClubId(club.getId());

            controller.setClub(club);


            // Ajoutez l'élément à votre liste de clubs
            pnItems.getChildren().add(item);
        }


        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            search(); // Call search method whenever text changes in the filterField
        });

    }
    public void AjouterClub(ActionEvent event) {

        try {
            // Load the FXML file for the popup content
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ajouterClub.fxml"));
            BorderPane popupContent = loader.load();

            // Create the popup and set its content
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            AjouterClubController controller = loader.getController();
            controller.setPopup(popup);

            // Obtain the primary stage
            Stage primaryStage = (Stage) btnOrders.getScene().getWindow();

            // Show the popup
            popup.show(primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void search() {
        List<Club> listC =  cs.readAll();
        ObservableList<Club> observableList = FXCollections.observableList(listC);
        FilteredList<Club> filteredData = new FilteredList<>(observableList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(club -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(club.getId()).toLowerCase().contains(lowerCaseFilter)
                        || club.getIntitule().toLowerCase().contains(lowerCaseFilter) || club.getEmailClub().toLowerCase().contains(lowerCaseFilter) || club.getLogo().toLowerCase().contains(lowerCaseFilter)
                        || club.getPageFb().toLowerCase().contains(lowerCaseFilter) || club.getPageInsta().toLowerCase().contains(lowerCaseFilter);

            });

            // Clear previous items in the UI
            pnItems.getChildren().clear();

            // Update UI with the filtered data
            for (Club club : filteredData) {
                // Initialize node for each item
                Node node = null; // Update this line based on your actual FXML structure
                try {
                    node = FXMLLoader.load(getClass().getResource("/Admin/ItemClub.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (node != null) {
                    ((Label) node.lookup("#intitule")).setText(club.getIntitule());
                    ((Label) node.lookup("#email")).setText(club.getEmailClub());

                    // Update other fields as needed
                    pnItems.getChildren().add(node); // Add the node to the UI
                }
            }
        });
    }

    private Image generateQRCode(String url) {
        try {
            // Créer le BitMatrix pour représenter le code QR
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300);

            // Convertir le BitMatrix en BufferedImage
            BufferedImage bufferedImage = toBufferedImage(bitMatrix);

            // Convertir le BufferedImage en Image de JavaFX
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage toBufferedImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

}


    







