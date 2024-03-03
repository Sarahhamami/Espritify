package Controllers.Admin;

import entities.Message;
import entities.Reponse_rec;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import services.MessageService;

import java.io.IOException;

public class ItemMsgContoller {


    private static MessageService ms=new MessageService();
    private GestionMessagerie gs=new GestionMessagerie();

    @FXML
    private Label date;

    @FXML
    private Label desc;

    @FXML
    private HBox itemC;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;
    @FXML
    private Label id_rep;



    private int id_msg;

    public int getId_msg() {
        return id_msg;
    }

    public  void setId_msg(int id_msg) {
        this.id_msg = id_msg;
    }

    @FXML
    void supprimerRep(ActionEvent event) throws IOException {

        Message rr=new Message(Integer.parseInt(id_rep.getText()));
        ms.delete(rr);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
        Parent root =loader.load();
        GestionMessagerie rrs=loader.getController();
        date.getScene().setRoot(root);
    }

}
