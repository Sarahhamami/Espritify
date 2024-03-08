package Controllers.Admin;

import entities.Club;
import entities.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import services.EvenementService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class EvenementController {

    private final EvenementService es = new EvenementService();

    @FXML
    private Button btnAjoutEven;

    @FXML
    private Button btnEditEven;

    @FXML
    private Button btnSuppEven;

    @FXML
    private TableColumn<Evenement, String> coldate;

    @FXML
    private TableColumn<Evenement, Date> coldatef;

    @FXML
    private TableColumn<Evenement, String> coldesc;

    @FXML
    private TableColumn<Evenement, Integer> colidEv;

    @FXML
    private TableColumn<Evenement, Integer> colidclub;

    @FXML
    private TableColumn<Evenement, String> collieu;

    @FXML
    private TableColumn<Evenement, String> colteven;

    @FXML
    private DatePicker dateDTF;

    @FXML
    private DatePicker dateFTF;

    @FXML
    private TextField descTF;

    @FXML
    private TextField lieuTF;

    @FXML
    private TableView<Evenement> tableView;

    @FXML
    private TextField titreTF;
    @FXML
    private TextField textClubid;

    @FXML
    private TextField eventSearchField;
    int id=0;


    @FXML
    void ajouterEvenement(ActionEvent event) {

        LocalDate localDateDebut = dateDTF.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDateDebut.format(formatter);
        Date date = java.sql.Date.valueOf(formattedDate);

        LocalDate localDateFin = dateFTF.getValue();
        String formattedDate1 = localDateFin.format(formatter);
        Date date1 = java.sql.Date.valueOf(formattedDate1);


     /*   Evenement evenement = new Evenement(value1, titreTF.getText(), descriptionTF.getText(), lieuTF.getText(), formattedDateDebut, DateFinTF.getValue());
        evenement.setId_club(Integer.parseInt(textClubid.getText()));
        evenement.setTitre(titreTF.getText());
        evenement.setDescription(descTF.getText());
        evenement.setLieu(lieuTF.getText());
        evenement.setDateDebut(date);
        evenement.setDateFin(date1);
        es.add(evenement);
        ObservableList list = (ObservableList) es.readAll();*/


    }

    @FXML
    void afficherEvenement(ActionEvent event) {
        ObservableList listE = (ObservableList) es.readAll();
        tableView.setItems(listE);
        colidEv.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
        colidclub.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id_club"));
        colteven.setCellValueFactory(new PropertyValueFactory<Evenement, String>("titre"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        collieu.setCellValueFactory(new PropertyValueFactory<Evenement,String>("lieu"));
         coldate.setCellValueFactory(new PropertyValueFactory<Evenement,String>("dateDebut"));
         coldatef.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("dateFin"));


    }


    @FXML
    void deleteEvenement(ActionEvent event) {
        Evenement evenement = (Evenement) tableView.getSelectionModel().getSelectedItem();
        es.delete(evenement);
        ObservableList list = (ObservableList)es.readAll();
        tableView.setItems(list);

    }

    @FXML
    void updateEvenement(ActionEvent event) {
        Evenement evenement = (Evenement) tableView.getSelectionModel().getSelectedItem();

        LocalDate localDateDebut = dateDTF.getValue();
        LocalDate localDateFin = dateFTF.getValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDate = localDateDebut.format(formatter);
        Date date = java.sql.Date.valueOf(formattedDate);

        String formattedDateFin = localDateFin.format(formatter);
        Date dateF = java.sql.Date.valueOf(formattedDateFin);


        evenement.setId_club(Integer.parseInt(textClubid.getText()));
        evenement.setTitre(titreTF.getText());
        evenement.setDescription(descTF.getText());
        evenement.setLieu(lieuTF.getText());
      //   evenement.setDateDebut(date);
        //evenement.setDateFin(dateF);
        es.update(evenement);
        ObservableList list = (ObservableList)es.readAll();
        tableView.setItems(list);


    }


    @FXML
    void getData(MouseEvent event) {
        Evenement evenement =  tableView.getSelectionModel().getSelectedItem();
        id = evenement.getId();
        textClubid.setText(String.valueOf(evenement.getId_club()));
        titreTF.setText(evenement.getTitre());
        descTF.setText(evenement.getDescription());
        lieuTF.setText(evenement.getLieu());
        //dateDTF.setValue(LocalDate.ofEpochDay((evenement.getDateDebut().getTime())));
        es.readAll();
    }
    @FXML
    private void searchevents(KeyEvent event) {
        String keyword = eventSearchField.getText();
        ObservableList<Evenement> filteredEvents = es.search(keyword);
        tableView.setItems(filteredEvents);
    }

    // Méthode pour afficher les événements associés à un club
    public void afficherEvenements(Club club) {
        // Récupérer les événements associés au club
        List<Evenement> evenements = club.getEvenements();

        // Afficher les événements dans le TableView
        tableView.setItems(FXCollections.observableArrayList(evenements));
    }

}
