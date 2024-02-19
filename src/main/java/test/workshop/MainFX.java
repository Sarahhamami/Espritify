package test.workshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.workshop/afficherQuizz.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root); //chargement de l'interface graphique
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion Quizz");
        primaryStage.show();
    }
}
