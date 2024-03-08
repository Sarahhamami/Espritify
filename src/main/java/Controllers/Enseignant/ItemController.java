package Controllers.Enseignant;
import entities.Cours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
//import main.Main;
import org.example.MyListener;
import entities.Cours;
import org.controlsfx.control.Rating;

public class ItemController {
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;
    private String titre;
    private String contenu;
    private int rating;
    @FXML
    private Rating rate;
    private Cours cours;
    private MyListener myListener;

    public void setRatingControl(double ratingControl) {
        this.rate.setRating(ratingControl);
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(cours);
    }

    public void setData(Cours cours,MyListener myListener) {
        //System.out.println(cours.getRate());
        this.cours = cours;
        this.myListener = myListener;
        nameLabel.setText(titre);
        setRatingControl(cours.getRate());
        Image image = new Image("file:///C:/xampp/htdocs/ons/images/" + contenu);
        img.setImage(image);
    }
}