package Controllers.Admin;

import entities.Reclamation;
import entities.Reponse_rec;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.ReponseRecService;

import java.io.IOException;

public class ItemRepController {

    ReponseRecService rrs=new ReponseRecService();
     private int id_rep;


    public int getid_rep() {
        return id_rep;
    }

    public void setid_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    @FXML
    private Label data;

    @FXML
    private Label descriptionrep;

    @FXML
    private HBox itemC;

    @FXML
    private Label titrerec;

    @FXML
    private Label titrerep;

    @FXML
    void modifier(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ModifierRep.fxml"));
            BorderPane popupContent = loader.load();

            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            ModifierRepController controller = loader.getController();
            controller.setid_rep(id_rep);
            controller.setPopup(popup);
            Stage primaryStage = (Stage) descriptionrep.getScene().getWindow();
            popup.show(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void supprimerRep(ActionEvent event) throws IOException {

        Reponse_rec rr=new Reponse_rec(id_rep);
        rrs.delete(rr);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
        Parent root =loader.load();
        ReponseReclamationController rrs=loader.getController();
        descriptionrep.getScene().setRoot(root);

    }

}
