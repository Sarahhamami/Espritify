package Controllers;

import Entities.Question;
import Entities.Quizz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Services.QuizzServices;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
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
    private Button btnretour;

    @FXML
    private Button btnsuivant;

    private List<Quizz> quizzList;
    private int currentIndex;

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
            btnretour.setVisible(false);
            btnsuivant.setVisible(false);
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
        btnretour.setVisible(currentIndex > 0);
        btnsuivant.setVisible(currentIndex < quizzList.size() - 1);
    }

    @FXML
    void verifyResponses(ActionEvent event) throws IOException {
        Quizz currentQuizz = quizzList.get(currentIndex);
        Question currentQuestion = currentQuizz.getId_question();

        String selectedResponse = ((Button) event.getSource()).getText();
        String correctResponse = currentQuestion.getBonneReponse();

        boolean isCorrect = selectedResponse.equals(correctResponse);

        Node node = (Node) event.getSource();
        Window ownerWindow = node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Alert.fxml"));
        AnchorPane popupContent = loader.load();
        Popup popup = new Popup();
        popup.getContent().add(popupContent);
        Controllers.Alert controller = loader.getController();
        if (isCorrect) {
            controller.setCustomMsg("Félicitations, les réponses sont correctes.");
            controller.setCustomTitle("Réponses correctes!");
            controller.setImageView("/images/approuve.png");
            controller.setClosePopupStyle("button-success");
        } else {
            controller.setCustomMsg("Désolé, les réponses ne sont pas correctes. Veuillez réessayer");
            controller.setCustomTitle("Réponses incorrectes!");
            controller.setImageView("/images/rejete.png");
            controller.setClosePopupStyle("button-failed");
        }
        controller.setPopup(popup);
        popup.show(ownerWindow);
    }

    public void onResponseClicked(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        clickedButton.getText();
        verifyResponses(event);
    }

    @FXML
    void retour() {
        if (currentIndex > 0) {
            currentIndex--;
            updateUI();
        }
    }
    @FXML
    void suivant() {
        if (currentIndex < quizzList.size() - 1) {
            currentIndex++;
            updateUI();
        }
    }
}
