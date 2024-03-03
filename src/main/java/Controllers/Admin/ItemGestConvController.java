package Controllers.Admin;

import entities.Conversation;
import entities.Message;
import entities.Reponse_rec;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import services.ConversationService;
import services.MessageService;

import java.io.IOException;
import java.util.List;

public class ItemGestConvController {

    private static ConversationService cs=new ConversationService();
    private static MessageService ms=new MessageService();
    ItemMsgContoller ims=new ItemMsgContoller();
    private GestionMessagerie gs;

    public void setGestionMessagerie(GestionMessagerie gs) {
        this.gs = gs;
    }

    @FXML
    private Label date;

    @FXML
    private Label description;

    @FXML
    private HBox itemC;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label titre;

    private int id_conv;

    public int getId_conv() {
        return id_conv;
    }

    public void setId_conv(int id_conv) {
        this.id_conv = id_conv;
    }

        @FXML
        void Show(ActionEvent event) throws IOException {


            FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
            Parent root =loader.load();
            GestionMessagerie ars=loader.getController();
            setGestionMessagerie(ars);  // Set the controller in ItemConv
            date.getScene().setRoot(root);
            gs.getPnItems().getChildren().clear();
            gs.showundobtn();



            List<Message> c= ms.readAllByID(getId_conv());

            for (int i = 0; i < c.size(); i++) {
                try {

                    Message Message = c.get(i);
                    FXMLLoader Itemmoader = new FXMLLoader(getClass().getResource("/Admin/ItemMessage.fxml"));
                    Node node = Itemmoader.load();
                    ((Label) node.lookup("#nom")).setText(Message.getNom_commentor());
                    ((Label) node.lookup("#prenom")).setText(Message.getPrenom_commentor());
                    ((Label) node.lookup("#date")).setText(Message.getDate());
                    ((Label) node.lookup("#desc")).setText(Message.getDescription());
                    GestionMessagerie irepc=loader.getController();
                    irepc.setId_msg(Message.getId());
                    System.out.println(irepc.getId_msg());
                    // Set id_msg in ItemMsgController


                    gs.getPnItems().getChildren().add(node);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    @FXML
    void supprimerConv(ActionEvent event) throws IOException {

        System.out.println(id_conv);
        Conversation rr=new Conversation(id_conv);
        cs.delete(rr);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
        Parent root =loader.load();
       GestionMessagerie rrs=loader.getController();
        description.getScene().setRoot(root);

    }

}
