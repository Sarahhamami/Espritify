package Controllers.Etudiant;

import Controllers.Admin.Ajoutercours;
import Controllers.Admin.ReclamerController;
import Controllers.AjouterQuizzController;
import Controllers.Enseignant.ItemController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import okhttp3.RequestBody;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.MyListener;
import entities.Cours;
import services.CoursService;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FrontEtud implements Initializable {
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

    private Stage primaryStage;

    private static final String API_KEY = "28f3f5fdf3644946806ce4f2671b5ab2";
    private static final String location = "southafricanorth";

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

    public void Traduire() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Translator");

        TextField inputTextArea = new TextField();
        inputTextArea.setPromptText("Enter text to translate");

        ChoiceBox<String> sourceLanguageChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(
                "en", "fr", "es", "ar"));
        sourceLanguageChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<String> targetLanguageChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(
                "en", "fr", "es", "ar"));
        targetLanguageChoiceBox.getSelectionModel().selectFirst();

        Button translateButton = new Button("Translate");

        Label translatedTextLabel = new Label("Translated Text:");
        Label translatedText = new Label();

        translateButton.setOnAction(event -> {
            String sourceLanguage = sourceLanguageChoiceBox.getValue();
            String targetLanguage = targetLanguageChoiceBox.getValue();
            String inputText = inputTextArea.getText();

            System.out.println("Source Language: " + sourceLanguage); // Print the selected source language

            try {
                String url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=" + sourceLanguage + "&to=" + targetLanguage;

                System.out.println("API URL: " + url); // Print the API request URL

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Ocp-Apim-Subscription-Key", API_KEY)
                        .addHeader("Ocp-Apim-Subscription-Region", location)
                        .addHeader("Content-Type", "application/json")
                        .post(RequestBody.create(okhttp3.MediaType.parse("application/json"), "[{\"Text\": \"" + inputText + "\"}]"))
                        .build();

                Response response = client.newCall(request).execute();
                String responseBody = response.body().string();

                JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                JsonArray translations = jsonObject.getAsJsonArray("translations");
                String translatedTextValue = translations.get(0).getAsJsonObject().get("text").getAsString();

                // Update translated text label
                translatedText.setText(translatedTextValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Create layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(new Label("Source Language:"), 0, 0);
        inputGrid.add(sourceLanguageChoiceBox, 1, 0);
        inputGrid.add(new Label("Target Language:"), 0, 1);
        inputGrid.add(targetLanguageChoiceBox, 1, 1);
        inputGrid.add(inputTextArea, 0, 2, 2, 1);
        inputGrid.add(translateButton, 0, 3, 2, 1);
        inputGrid.add(translatedTextLabel, 0, 4);
        inputGrid.add(translatedText, 1, 4);

        root.getChildren().add(inputGrid);

        Scene scene = new Scene(root, 400, 250);

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(scene);
        popupStage.show();
    }

    public void Retour(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Accueil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private WebView webView;
    public void openMeet(ActionEvent actionEvent) {
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
