package Controllers.Etudiant;

import entities.Entretien;
import entities.OffreStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.EntretienService;
import services.OffreStageService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EntretienController implements Initializable {
    @FXML
    private VBox pnItems = null;

    @FXML
    private Button btnDossierStage;

    @FXML
    private Button btnEntretien;

    @FXML
    private Button btnotherQuizz;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnQuizz;
    @FXML
    private Button btnCours;

    @FXML
    private Button btndiscussion;
    @FXML
    private Button btnClub;
    @FXML
    private Button btnStage;
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) {

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
        if (actionEvent.getSource() == btnClub) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/club.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnCours) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Front_Etud.fxml"));
                Parent root = loader.load();
                FrontEtud controller=loader.getController();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (actionEvent.getSource() == btnStage) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Stage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnEntretien) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Entretien.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btndiscussion) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnQuizz) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/Quizzes.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnotherQuizz) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/otherQuizzes.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
private EntretienService entretienService= new EntretienService();
    private OffreStageService offreStageService=new OffreStageService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // hedhi bech ttbadel wa9t ta7dher session
        List<Entretien> lst= entretienService.getById(75);

        for (Entretien entretien : lst) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/ItemEntretien.fxml"));
                Node node = loader.load();

                ItemEntretienController itemController = loader.getController();
                OffreStage offreStage=offreStageService.readById(entretien.getId_stage());
                // Set data for the UI components
                ((Label) node.lookup("#lieu")).setText(entretien.getLieu());
                ((Label) node.lookup("#nom_soc")).setText(offreStage.getNomSociete());
                ((Label) node.lookup("#date")).setText(entretien.getDateEntre().toString());
                if (entretien.isEtat())
                itemController.setEtat("Accepté(e)");
                else
                    itemController.setEtat("En attente");
                itemController.setDate(entretien.getDateEntre().toString());
                itemController.setDesc(entretien.getDescription());
                itemController.setLieu(entretien.getLieu());
                itemController.setType(entretien.getType());

                // Add node to the UI
                pnItems.getChildren().add(node);

                // Add mouse event handlers
                node.setOnMouseEntered(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });
                node.setOnMouseExited(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
