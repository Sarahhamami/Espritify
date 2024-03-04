package Controllers;

import entities.Question;
import entities.Quizz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.QuizzServices;
import javafx.event.ActionEvent;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class Quizzes {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Text question;

    @FXML
    private Button reponse1;

    @FXML
    private Button reponse2;

    @FXML
    private Button reponse3;

    @FXML
    private Button retour;

    private List<Quizz> quizzList;
    private int currentIndex;
    private int score = 0;

    private QuizzServices qs = new QuizzServices();

    public void initialize() {
        quizzList = qs.readAll();
        currentIndex = 0;
        updateUI();
    }

    private void updateUI() {
        if (quizzList.isEmpty()) {
            question.setText("Aucun quizz disponible");
            reponse1.setText("");
            reponse2.setText("");
            reponse3.setText("");
            return;
        }

        Quizz currentQuizz = quizzList.get(currentIndex);
        Question currentQuestion = currentQuizz.getId_question();
        question.setText(currentQuestion.getContenu());

        List<String> reponses = new ArrayList<>();
        reponses.add(currentQuizz.getId_question().getReponse1());
        reponses.add(currentQuizz.getId_question().getReponse2());
        reponses.add(currentQuizz.getId_question().getReponse3());

        for (int i = 0; i < 3; i++) {
            if (i < reponses.size()) {
                switch (i) {
                    case 0:
                        reponse1.setText(reponses.get(i));
                        break;
                    case 1:
                        reponse2.setText(reponses.get(i));
                        break;
                    case 2:
                        reponse3.setText(reponses.get(i));
                        break;
                }
            } else {
                switch (i) {
                    case 0:
                        reponse1.setText("");
                        break;
                    case 1:
                        reponse2.setText("");
                        break;
                    case 2:
                        reponse3.setText("");
                        break;
                }
            }
        }
    }

    @FXML
    void verifyResponses(ActionEvent event) throws IOException {
        Quizz currentQuizz = quizzList.get(currentIndex);
        Question currentQuestion = currentQuizz.getId_question();

        String selectedResponse = ((Button) event.getSource()).getText();
        String correctResponse = currentQuestion.getBonneReponse();

        boolean isCorrect = selectedResponse.equals(correctResponse);

        if(isCorrect){
            score++;
        }

        currentIndex++;
        if (currentIndex < quizzList.size()) {
            updateUI();
        } else {
            showFinalResult();
        }
    }


    public void onResponseClicked(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        clickedButton.getText();
        verifyResponses(event);
    }

    private void showFinalResult() {
        double percentageScore = ((double) score / quizzList.size()) * 100;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Alert.fxml"));
            AnchorPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            Alert controller = loader.getController();

            if (percentageScore > 50) {
                controller.setCustomMsg("Félicitations! Vous avez répondu correctement à "+score+" questions sur "+quizzList.size()+".\n Votre score est de "+percentageScore+". ");
                controller.setCustomTitle("Vous êtes approuvé!");
                controller.setImageView("/images/approuve.png");
                controller.setClosePopupStyle("button-success");
            } else {
                controller.setCustomMsg("Désolé! Vous avez répondu correctement à "+score+" questions sur "+quizzList.size()+".\n Votre score est de "+percentageScore+".");
                controller.setCustomTitle("Vous n'êtes pas approuvé.");
                controller.setImageView("/images/rejete.png");
                controller.setClosePopupStyle("button-failed");
            }

            Node node = MainPane;
            Window ownerWindow = node.getScene().getWindow();
            popup.show(ownerWindow);
            controller.setPopup(popup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void Retour(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Accueil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
