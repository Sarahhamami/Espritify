package Controllers.Admin;

import Controllers.Alert;
import entities.Categorie;
import entities.Cours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import services.CategorieService;
import services.CoursService;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class Ajoutercours implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ChoiceBox<Categorie> categorie;

    @FXML
    private TextField contenu;

    @FXML
    private TextField titre;

    @FXML
    private Label erreurtitre;

    @FXML
    private Label erreurcontenu;

    private Stage stage;


    CoursService cs=new CoursService();
    CategorieService cats=new CategorieService();

    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    @FXML
    void ajoutercours(ActionEvent event) throws IOException {
        String titrefield=titre.getText();
        String contenufield=contenu.getText();

        if(titrefield.isEmpty()|| !titrefield.matches("[a-zA-Z]+") || titrefield.length() > 20)
        {
            erreurtitre.setText("entrez les donnees correctement");
            addBtn.setDisable(true);
        }
        else if(contenufield.isEmpty()){
            erreurcontenu.setText("veuillez remplir ce champ");
            addBtn.setDisable(true);

        }
        else addBtn.setDisable(false);

        boolean addedSuccessfully = cs.add(new Cours(titrefield,false,contenufield,categorie.getValue()));
        Node node = (Node) event.getSource();
        Window ownerWindow = node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Alert.fxml"));
        AnchorPane popupContent = loader.load();
        Popup popup = new Popup();
        popup.getContent().add(popupContent);
        Alert controller = loader.getController();
        if (addedSuccessfully) {
            controller.setCustomMsg("Ajout avec succès");
            controller.setCustomTitle("Succès!");
            controller.setImageView("/images/approuve.png");
            controller.setClosePopupStyle("button-success");
        } else {
            controller.setCustomMsg("Échec de l'ajout du cours");
            controller.setCustomTitle("Échec!");
            controller.setImageView("/images/rejete.png");
            controller.setClosePopupStyle("button-failed");
        }
        controller.setPopup(popup);
        popup.show(ownerWindow);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Categorie> categorieList = cats.readAll();
        ObservableList<Categorie> observableResponseList = FXCollections.observableArrayList(categorieList);
        categorie.setItems(observableResponseList);

        categorie.setConverter(new StringConverter<Categorie>() {
            @Override
            public String toString(Categorie categorie) {
                return categorie!= null ? categorie.getType() : null;
            }

            @Override
            public Categorie fromString(String s) {
                return null;
            }
        });
    }

    public void upload(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(stage); // Use the stage passed from the main application
        if (selectedFile != null) {
            // Get the target resource directory path
            String resourcePath = "C:/xampp/htdocs/ons/images/"; // Modify this according to your project structure
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
                displayImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void displayImage(Image image) {
        // Create an ImageView to display the image
        ImageView imageView = new ImageView(image);

        // Create a new stage to display the image
        Stage imageStage = new Stage();
        imageStage.setTitle("Selected Image");
        imageStage.setScene(new Scene(new Group(imageView), image.getWidth(), image.getHeight()));
        imageStage.show();
    }


}

