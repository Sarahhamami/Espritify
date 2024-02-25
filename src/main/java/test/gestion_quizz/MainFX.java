package test.gestion_quizz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainFX extends Application {
    private double x,y;
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/afficherQuestion.fxml"));
        stage.setScene(new Scene(root));
        //set stage borderless
        //stage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
        stage.show();



    }
    public static void main(String[] args) {
        launch(args);
    }
}
