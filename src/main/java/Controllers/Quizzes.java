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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.QuizzServices;
import javafx.event.ActionEvent;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void getCert(){

    }

    public void pdf() {
        /*
        Connection cnx = null;
        Statement ste;
        PreparedStatement pst;
        String req="select * from certification";
        try{
            ste=cnx.createStatement();
            ste.execute(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */

        try {
            // Create a new document PDF
            PDDocument document = new PDDocument();

            // Create a page in the document
            PDPage page = new PDPage();
            document.addPage(page);

            // Get the content of the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set font and font size
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Title
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 750);
            contentStream.showText("Certification  d'accomplissement de niveau ");
            contentStream.endText();

            // Line separator
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(100, 740);
            contentStream.lineTo(500, 740);
            contentStream.stroke();

            // Set font for details
            contentStream.setFont(PDType1Font.HELVETICA, 10);

            // Content table
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);

            int rowNum = 1; // Row number counter

            //contentStream.showText(.getNom_user());
            contentStream.newLineAtOffset(10, 0);

            //contentStream.showText(.getPrenom_user());
            contentStream.newLineAtOffset(20, 0);


            contentStream.showText(" Arij mer a recue une certification d'accomplissement de niveau par espritify ");
            contentStream.newLineAtOffset(30, 0);


            contentStream.endText();

            // Close the content of the page
            contentStream.close();

            // Save and open the document
            String outputPath = "";
            File file = new File(outputPath);
            document.save(file);

            // Close the document
            document.close();

            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
