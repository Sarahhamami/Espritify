package Controllers.Etudiant;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import entities.Club;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.image.BufferedImage;
import java.io.File;

public class FrontItemClubController {

    public ImageView ImageViewLogo;
    public ImageView qrCodeImageViewFb;
    public ImageView qrCodeImageViewInsta;
    @FXML
    private Button btnparticiper;

    @FXML
    private Label email;

    @FXML
    private Label intitule;

    @FXML
    private HBox itemC;

    @FXML
    private Label logo;

    @FXML
    private Label pageInsta;

    @FXML
    private Label pagefb;
    private Club club;
    private int clubId;



    public void setClub(Club club) {
        this.club = club;
        // Set the text fields with the Club object's data
        intitule.setText(club.getIntitule());
        email.setText(club.getEmailClub());
       // logo.setText(club.getLogo());
        //pagefb.setText(club.getPageFb());
        //pageInsta.setText(club.getPageInsta());
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
