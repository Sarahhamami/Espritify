package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class otherQuizzes {

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

    private List<String> questionTexts;
    private List<List<String>> answers;
    private List<Integer> correctAnswers;

    private int currentIndex;
    private int score = 0;

    public void initialize() {
        currentIndex = 0;
        loadQuestions();
        updateUI();
    }

    public void loadQuestions() {
        questionTexts = new ArrayList<>();
        answers = new ArrayList<>();
        correctAnswers = new ArrayList<>();

        try {
            URL url = new URL("https://opentdb.com/api.php?amount=20&category=15&difficulty=medium&type=multiple");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray results = jsonResponse.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject result = results.getJSONObject(i);
                String questionText = result.getString("question");
                JSONArray incorrectAnswers = result.getJSONArray("incorrect_answers");
                List<String> answerList = new ArrayList<>();

                // Add incorrect answers
                for (int j = 0; j < incorrectAnswers.length(); j++) {
                    answerList.add(incorrectAnswers.getString(j));
                }

                // Add correct answer
                String correctAnswer = result.getString("correct_answer");
                answerList.add(correctAnswer);

                // Shuffle the answer list
                Collections.shuffle(answerList);

                // Randomly select a button index to set as the correct answer
                Random random = new Random();
                int correctButtonIndex = random.nextInt(answerList.size());

                // Store correct answer index in correctAnswers list
                correctAnswers.add(correctButtonIndex);

                // Add answer list to answers list
                answers.add(answerList);

                // Add question text to questionTexts list
                questionTexts.add(questionText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUI() {
        question.setText(questionTexts.get(currentIndex));
        List<String> answerList = answers.get(currentIndex);
        reponse1.setText(answerList.get(0));
        reponse2.setText(answerList.get(1));
        reponse3.setText(answerList.get(2));

        int correctButtonIndex = correctAnswers.get(currentIndex);
        if (correctButtonIndex == 0) {
            reponse1.setText(answerList.get(3));
        } else if (correctButtonIndex == 1) {
            reponse2.setText(answerList.get(3));
        } else {
            reponse3.setText(answerList.get(3));
        }
    }

    @FXML
    void verifyResponses(ActionEvent event) {
        Button selectedButton = (Button) event.getSource();
        String selectedResponse = selectedButton.getText();

        int correctButtonIndex = correctAnswers.get(currentIndex);
        String correctResponse = answers.get(currentIndex).get(correctButtonIndex);

        boolean isCorrect = selectedResponse.equals(correctResponse);

        if (isCorrect) {
            score++;
        }

        currentIndex++;

        if (currentIndex < questionTexts.size()) {
            updateUI();
        } else {
            showFinalResult();
        }
    }

    private void showFinalResult() {
        double percentageScore = ((double) score / questionTexts.size()) * 100;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Alert.fxml"));
            AnchorPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            Alert controller = loader.getController();

            if (percentageScore > 50) {
                controller.setCustomMsg("Félicitations! Vous avez répondu correctement à "+score+" questions sur "+questionTexts.size()+".\n Votre score est de "+percentageScore+". ");
                controller.setCustomTitle("Vous êtes approuvé!");
                controller.setImageView("/images/approuve.png");
                controller.setClosePopupStyle("button-success");
            } else {
                controller.setCustomMsg("Désolé! Vous avez répondu correctement à "+score+" questions sur "+questionTexts.size()+".\n Votre score est de "+percentageScore+".");
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
