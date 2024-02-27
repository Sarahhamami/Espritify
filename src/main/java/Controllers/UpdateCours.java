package Controllers;

import entities.Cours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.CoursService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UpdateCours {

    @FXML
    private Button addBtn;

    @FXML
    private Button addBtn1;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField contenu;

    @FXML
    private Label erreurcontenu;

    @FXML
    private Label erreurtitre;

    @FXML
    private TextField titre;

    private int id;

    private Stage stage;

    public void setId(int id) {
        this.id = id;
    }

    CoursService cs=new CoursService();
    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    public void setContenu(String contenu) {
        this.contenu.setText(contenu);
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    @FXML
    void modifiercours(ActionEvent event) throws IOException {


            boolean updatedSuccessfully = cs.update(new Cours(id,titre.getText(),false, contenu.getText()));


            Node node = (Node) event.getSource();
            Window ownerWindow = node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Alert.fxml"));
            AnchorPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            Alert controller = loader.getController();
            if (updatedSuccessfully) {
                controller.setCustomMsg("Update avec succès");
                controller.setCustomTitle("Succès!");
                controller.setImageView("/images/approuve.png");
                controller.setClosePopupStyle("button-success");
            } else {
                controller.setCustomMsg("Échec de l'update du quizz");
                controller.setCustomTitle("Échec!");
                controller.setImageView("/images/rejete.png");
                controller.setClosePopupStyle("button-failed");
            }
            controller.setPopup(popup);
            popup.show(ownerWindow);
        }


    public void upload(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(stage); // Use the stage passed from the main application
        if (selectedFile != null) {
            // Get the target resource directory path
            String resourcePath = "C:/xampp/htdocs"; // Modify this according to your project structure
            File resourceDirectory = new File(resourcePath);

            // Ensure the resource directory exists, create if necessary
            if (!resourceDirectory.exists()) {
                resourceDirectory.mkdirs();
            }
            String path = resourcePath + selectedFile.getName();
            File destinationFile = new File(path);

            try {
                // Copy the selected file to the resource directory
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image uploaded to: " + path);
                contenu.setText(selectedFile.getName());

                // Optionally, load and display the image
                Image image = new Image(destinationFile.toURI().toString());
                ImageView imageView = new ImageView(image);
                // You can add this imageView to your layout for display
                //displayImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



}
