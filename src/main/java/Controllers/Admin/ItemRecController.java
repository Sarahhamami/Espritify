package Controllers.Admin;

import entities.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.ReclamationService;
import services.ReponseRecService;

import java.io.IOException;

public class ItemRecController {

    private static ReclamationService rs=new ReclamationService();
    private static ReponseRecService rrs=new ReponseRecService();
    @FXML
    private Label date;

    @FXML
    private Label description;
    private int id_user_reponse;

    public int getId_user_reponse() {
        return id_user_reponse;
    }

    public void setId_user_reponse(int id_user_reponse) {
        this.id_user_reponse = id_user_reponse;
    }

    private int id_Sup;

    public int getId_id_Sup() {
        return id_Sup;
    }

    public void setid_Sup(int id_Sup) {
        this.id_Sup = id_Sup;
    }

    @FXML
    private Label titre;
    ReclamationController rc= new ReclamationController();

    public Label getDescription() {
        return description;
    }

    public void setDescription(Label description) {
        this.description = description;
    }

    public Label getTitre() {
        return titre;
    }

    public void setTitre(Label titre) {
        this.titre = titre;
    }



    @FXML
    void Repondre(ActionEvent event) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/RepondrePU.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            ReponseController controller = loader.getController();
            controller.setId_reponse(id_user_reponse);
            controller.setPopup(popup);
            Stage primaryStage = (Stage) description.getScene().getWindow();
            popup.show(primaryStage);
            System.out.println(id_user_reponse);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void supprimerRec(ActionEvent event) throws IOException {

        Reclamation r=new Reclamation(id_Sup);
        rs.delete(r);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
        Parent root =loader.load();
        ReclamationController rc=loader.getController();
        description.getScene().setRoot(root);
    }
}
