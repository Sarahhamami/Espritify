package Controllers.Enseignant;

import Controllers.Admin.Ajoutercours;
import Controllers.Admin.ReclamerController;
import Controllers.AjouterQuizzController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.example.MyListener;
import entities.Cours;
import services.CoursService;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FrontProf implements Initializable {
    @FXML
    private VBox chosenCoursCard;
    @FXML
    private Label coursNameLabel;
    @FXML
    private ImageView coursImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private TextField searchfield;

    CoursService cs = new CoursService();
    private List<Cours> courses = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private void setChosenCours(Cours cours) {
            coursNameLabel.setText(cours.getTitre());
            image = new Image("file:///C:/xampp/htdocs/ons/images/" + cours.getContenu());
            coursImg.setImage(image);
            chosenCoursCard.setStyle("-fx-background-color: #960000; -fx-background-radius: 30;");
    }

    public void initializeData() {
        courses = cs.readAll();

        if (!courses.isEmpty()) {
            setChosenCours(courses.get(0));
        }

        myListener = cours -> setChosenCours(cours);

        if (!courses.isEmpty()) {
            int column = 0;
            int row = 0;
            try {
                for (Cours cours : courses) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Enseignant/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setRatingControl(cours.getRate());  // Set the rating
                    System.out.println(cours.getRate());
                    itemController.setTitre(cours.getTitre());
                    itemController.setContenu(cours.getContenu());
                    itemController.setData(cours, myListener);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeData();
        cherchercours();
    }

    @FXML
    private void meet() {
        // Handle meet action
    }

    @FXML
    void ajouter() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ajoutercours.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            Ajoutercours controller = loader.getController();
            controller.setPopup(popup);
            Stage primaryStage = (Stage) chosenCoursCard.getScene().getWindow();
            popup.show(primaryStage);
            popup.setOnHidden(event -> initializeData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cherchercours() {
        String searchTerm = searchfield.getText().toLowerCase();
        grid.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Cours cours : courses) {
            if (cours.getTitre().toLowerCase().contains(searchTerm)) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Enseignant/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setTitre(cours.getTitre());
                    itemController.setContenu(cours.getContenu());
                    itemController.setData(cours, myListener);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void OpenReclamation(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ReclamerPU.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            ReclamerController controller= loader.getController();
            controller.setPopup(popup);
            Stage primaryStage = (Stage) searchfield.getScene().getWindow();
            popup.show(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private WebView webView;



    public void openMeet(javafx.event.ActionEvent actionEvent) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://meet.google.com/");

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
}
