package Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TranslatorApp extends Application {

    private static final String API_KEY = "28f3f5fdf3644946806ce4f2671b5ab2";
    private static final String location = "southafricanorth";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Translator");

        TextField inputTextArea = new TextField();
        inputTextArea.setPromptText("Enter text to translate");

        ChoiceBox<String> sourceLanguageChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(
                "Auto Detect", "English", "French", "Spanish", "Arabic"));
        sourceLanguageChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<String> targetLanguageChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(
                "English", "French", "Spanish", "Arabic"));
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
                //String url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=" + sourceLanguage + "&to=" + targetLanguage;
                String url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=fr&to=ar";

                System.out.println("API URL: " + url); // Print the API request URL

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Ocp-Apim-Subscription-Key", API_KEY)
                        .addHeader("Ocp-Apim-Subscription-Region", location)
                        .addHeader("Content-Type", "application/json")
                        .post(okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json"), "[{\"Text\": \"" + inputText + "\"}]"))
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
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
