package Controllers.Admin;

import entities.Conversation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import services.ConversationService;

public class  EditerCmntController{
    private ConversationService cs=new ConversationService();
    private int id_conv;

    public int getId_conv() {
        return id_conv;
    }

    public void setId_conv(int id_conv) {
        this.id_conv = id_conv;
    }

    @FXML
private Button cancelBtn;

@FXML
private TextField desc;

@FXML
private Button modifier;

@FXML
private TextField titre;

@FXML
private Label toplabell;

private Popup popup;

    public Popup getPopup() {
        return popup;
    }

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    public TextField getDesc() {
        return desc;
    }

    public void setDesc(TextField desc) {
        this.desc = desc;
    }

    public TextField getTitre() {
        return titre;
    }

    public void setTitre(TextField titre) {
        this.titre = titre;
    }

    @FXML
void Modifier(ActionEvent event) {
        Conversation conversation=new Conversation(id_conv,titre.getText(),desc.getText());
        cs.update(conversation);
}

@FXML
void closePopup(ActionEvent event) {
        popup.hide();
}


}
