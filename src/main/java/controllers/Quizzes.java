package controllers;

import entities.Quizz;
import entities.Reponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.QuizzService;

import java.util.List;

public class Quizzes  {

    @FXML
    private AnchorPane MainPane;
    @FXML
    private AnchorPane q1;
    @FXML
    private AnchorPane q2;
    @FXML
    private AnchorPane q3;

    @FXML
    private Text question;
    @FXML
    private Text reponse1;

    @FXML
    private Text reponse2;

    @FXML
    private Text reponse3;

    @FXML
    private Button btnretour;

    @FXML
    private Button btnsuivant;

    int i= 0;



    QuizzService qs = new QuizzService();

    public void initialize() {

        actualise(qs.readAll());
    }

    void actualise(List<Quizz> quizzList){
        if(quizzList.size()-1-i*3>0){
            btnsuivant.setVisible(true);
        }
        if(quizzList.size()-1-i*3 <= 0){
            btnsuivant.setVisible(false);
        }
        if(i > 0){
            btnretour.setVisible(true);
        }
        if(i == 0){
            btnretour.setVisible(false);
        }
        /*
        if (!quizzList.isEmpty()) {
            Quizz currentQuizz = quizzList.get(i);
            question.setText(currentQuizz.getQuestions().get(0).getContenu());

            List<Reponse> reponses = currentQuizz.getQuestions().get(0).getReponses();
            if (!reponses.isEmpty()) {
                reponse1.setText(reponses.get(0).getContenu());
            }
            if (reponses.size() >= 2) {
                reponse2.setText(reponses.get(1).getContenu());
            }
            if (reponses.size() >= 3) {
                reponse3.setText(reponses.get(2).getContenu());
            }
        }

         */
    }

    @FXML
    void retour(ActionEvent event) {
        i -=1;
        actualise(qs.readAll());
    }

    @FXML
    void suivant(ActionEvent event) {
        i +=1;
        actualise(qs.readAll());

    }
}