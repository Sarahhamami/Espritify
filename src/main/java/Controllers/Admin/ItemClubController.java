package Controllers.Admin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.javafx.charts.Legend;
import entities.Club;
import entities.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.ClubService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemClubController {



    @FXML
    private Button btnShowEvents;


    private Club club;

    public Label id;
    @FXML
    private Button deleteItem;

    @FXML
    private Label email;

    @FXML
    private Label intitule;

    @FXML
    public HBox itemC;

    @FXML
    private Label logo;

    @FXML
    private Label pageFb;

    @FXML
    private Label pageInsta;

    @FXML
    private Button updateItem;

    @FXML
    private Button btnOrders;
    @FXML
    private ImageView qrCodeImageViewInsta;
    @FXML
    private ImageView qrCodeImageViewFb;
    @FXML
    private ImageView ImageViewLogo;


    private final ClubService cs = new ClubService();
    private int clubId;


    public Club getC() {
        Club club = new Club();
        //club.setId(Integer.parseInt(id.getText()));
        club.setId(clubId);
        club.setIntitule(intitule.getText());
        club.setEmailClub(email.getText());
        club.setLogo(logo.getText());
       // club.setPageInsta(pageInsta.getText());
       // club.setPageFb(pageFb.getText());
        return club;
    }




    @FXML
    private void initialize() {
        itemC.setOnMouseClicked(event -> handleItemClick());
    }

    private void handleItemClick() {
        System.out.println("ID du club sélectionné : " + clubId);
        // Vous pouvez maintenant effectuer d'autres actions avec l'ID du club
        // Par exemple, vous pouvez récupérer les détails du club à partir de la base de données
        // en utilisant cet ID.
    }

    public void setClubId(int id) {
        this.clubId = id;
    }


    @FXML
    void deleteItem(ActionEvent event) {
        Club club = getC();
        cs.delete(club);

    }


    @FXML
    void updateItem(ActionEvent event) {
        try {
            // Load the FXML file for the popup content

            Club currentClub = getC();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/updateClub.fxml"));
            BorderPane popupContent = null;
            try {
                popupContent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Create the popup and set its content
            Popup popup = new Popup();
            popup.getContent().add(popupContent);

            UpdateClubController controller = loader.getController();
            controller.setItem(
                     clubId,
                    currentClub.getIntitule(),
                    currentClub.getEmailClub(),
                    currentClub.getLogo(),
                    currentClub.getPageFb(),
                    currentClub.getPageInsta()

            );
            controller.setPopup(popup);

            // Obtain the primary stage
            Stage primaryStage = (Stage) itemC.getScene().getWindow();
            // Show the popup
            popup.show(primaryStage);


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    //erreur 0 update


    public void setClub(Club club) {
        this.club = club;
        // Set the text fields with the Club object's data
        intitule.setText(club.getIntitule());
        email.setText(club.getEmailClub());
         //logo.setText(club.getLogo());
        String imagePath = club.getLogo();
        File file = new File(imagePath);
        // Créer l'objet Image en utilisant le chemin d'accès récupéré
        Image image = new Image(file.toURI().toString());
        System.out.println(club.getLogo().toString());
        // Affichez cette image dans un ImageView
        ImageViewLogo.setImage(image);
        String urlInsta = club.getPageInsta(); // Récupérez l'URL à partir du club (à adapter selon votre modèle de données)
        String urlFb = club.getPageInsta();

        // Générer le QR code et l'afficher
        Image qrCodeImageInsta = generateQRCode(urlInsta);
        Image qrCodeImageFb = generateQRCode(urlFb);

        if (qrCodeImageInsta != null && qrCodeImageFb != null ) {
            qrCodeImageViewInsta.setImage(qrCodeImageInsta);
            qrCodeImageViewFb.setImage(qrCodeImageFb);


        } else {
            System.err.println("Impossible de générer le code QR pour l'URL : " + urlInsta + urlFb);
        }
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
