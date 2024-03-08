package Controllers.Etudiant;

import Controllers.Admin.AdminClubController;
import Controllers.Admin.ClubController;
import Controllers.Admin.ItemClubController;
import Controllers.Admin.ItemEvenementController;
import entities.Club;
import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ClubService;
import services.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class FrontClub implements Initializable {
    @FXML
    private AnchorPane clubItems;

    @FXML
    private VBox vboxClub;

    @FXML
    private VBox vboxEvent;
    ClubService cs = new ClubService();
    EvenementService es = new EvenementService();
    AdminClubController ac = new AdminClubController();

    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Club> o= cs.readAll();
        List<Evenement> e= es.readAll();

        for (int i = 0; i < o.size(); i++) {
            try {
                final int j = i;
                Club club = o.get(i);
                Node node = FXMLLoader.load(getClass().getResource("/Front/FrontClubItem.fxml"));
                //((Label) node.lookup("#id")).setText(String.valueOf(club.getId()));
                ((Label) node.lookup("#intitule")).setText(club.getIntitule());
                ((Label) node.lookup("#email")).setText(club.getEmailClub());
                ((Label) node.lookup("#logo")).setText(club.getLogo());
                ((Label) node.lookup("#pagefb")).setText(club.getPageFb());
                ((Label) node.lookup("#pageInsta")).setText(club.getPageInsta());


                // Give the items some effect
                node.setOnMouseEntered(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });
                node.setOnMouseExited(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });

                vboxClub.getChildren().add(node);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        for (int x = 0; x < e.size(); x++) {
            try {
                final int k = x;
                Evenement evenement = e.get(x);
               // EvenementService es = new EvenementService();

                Node node = FXMLLoader.load(getClass().getResource("/Front/FrontEventsItem.fxml"));
                ((Label) node.lookup("#id")).setText(String.valueOf(evenement.getId()));
                ((Label) node.lookup("#club")).setText(es.getIntituleClub());
                ((Label) node.lookup("#titre")).setText(evenement.getTitre());
                ((Label) node.lookup("#description")).setText(evenement.getDescription());
                ((Label) node.lookup("#lieu")).setText(evenement.getLieu());
                ((Label) node.lookup("#dateDebut")).setText(String.valueOf(evenement.getDateDebut()));
                ((Label) node.lookup("#dateFin")).setText(String.valueOf(evenement.getDateFin()));


                // Give the items some effect
                node.setOnMouseEntered(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });
                node.setOnMouseExited(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });

                vboxEvent.getChildren().add(node);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Club> o= cs.readAll();
        for (Club club : o) {
            // Créez un FXMLLoader pour charger le fichier FXML de l'élément de club
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/FrontClubItem.fxml"));
            HBox item = null;
            Node node = null;



            try {
                item = loader.load();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Obtenez une référence au contrôleur ItemClubController
            FrontItemClubController controller = loader.getController();

            // Définissez l'ID du club pour cet élément
            controller.setClubId(club.getId());
            controller.setClub(club);
          // Ajout d'un gestionnaire d'événements de clic à chaque élément de club
            item.setOnMouseClicked(event -> {
                // Chargement des événements du club sélectionné
                List<Evenement> events = cs.getEventsForClub(club);
                // Affichage des événements dans une autre partie de l'interface utilisateur
                displayEvents(events);
            });


            // Ajoutez l'élément à votre liste de clubs
            vboxClub.getChildren().add(item);
        }

    }
    // Méthode pour afficher les événements dans une autre partie de l'interface utilisateur
    private void displayEvents(List<Evenement> events) {
        vboxEvent.getChildren().clear(); // Nettoyer la zone des événements précédents

        for (Evenement event : events) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/FrontEventsItem.fxml"));
            HBox item = null;
            try {
                item = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Controllers.Etudiant.FrontItemEventsController controller = loader.getController();

            // Définissez l'ID du club pour cet élément
            controller.setEvenementId(event.getId());
            controller.setEventDetails(event);

            // Supposons que votre événement ait un nom
            vboxEvent.getChildren().add(item);
        }
    }


    @FXML
    void VoirCalendrier(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Etudiant/calendar.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();


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
}
