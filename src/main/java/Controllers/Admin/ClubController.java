package Controllers.Admin;

import entities.Club;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.ClubService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ClubController {


    private final ClubService cs = new ClubService();

    @FXML
    private TextField intituleClubTF;


    @FXML
    private TextField emailClubTF;


    @FXML
    private TextField logoClubTF;

    @FXML
    private TextField pageFbTF;

    @FXML
    private TextField pageInstaTF;


    @FXML
    private TableColumn<Club, String> colemail;

    @FXML
    private TableColumn<Club, String> colfb;

    @FXML
    private TableColumn<Club, Integer> colid;

    @FXML
    private TableColumn<Club, String> colinsta;

    @FXML
    private TableColumn<Club, String> colintitule;

    @FXML
    private TableColumn<Club, String> collogo;


    @FXML
    private TableView<Club> table;

    @FXML
    private Button btnAjout;

    @FXML
    private Button btnAffiche;

    @FXML
    private Button updateClub;

    @FXML
    private Button deleteClub;

    @FXML
    private Button btnShowEv;
    @FXML
    private Button btnOrders;

    @FXML
    private TextField searchField;

    int id=0;
    int i = -1;



    @FXML
    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }
    @FXML
    private void closePopup() {
        popup.hide();
    }



    /*@FXML
    public void AjouterClub(ActionEvent actionEvent) {
        cs.add(new Club(intituleClubTF.getText(), emailClubTF.getText(), logoClubTF.getText(), pageFbTF.getText(), pageInstaTF.getText()));

    }*/


    //probleme update service club
    @FXML
    void AjouterClub(ActionEvent event){
        cs.add(new Club(intituleClubTF.getText(), emailClubTF.getText(), logoClubTF.getText(), pageFbTF.getText(), pageInstaTF.getText()));



    }
    @FXML
    void refresh(MouseEvent event) {

        i = table.getSelectionModel().getSelectedIndex();
        if (i <= -1){
            return;
        }

        intituleClubTF.setText(colintitule.getCellData(i).toString());

    }

    public int getId() {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            return table.getItems().get(index).getId();
        } else {
            return -1;
        }
    }

  /*  @FXML
    void afficherListe(ActionEvent event){
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherClub.fxml"));
        ObservableList list = (ObservableList)cs.readAll();

        // System.out.println(list);
        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Club, Integer>("id"));
        colintitule.setCellValueFactory(new PropertyValueFactory<Club, String>("intitule"));
        colemail.setCellValueFactory(new PropertyValueFactory<Club, String>("emailClub"));
        collogo.setCellValueFactory(new PropertyValueFactory<Club, String>("logo"));
        colfb.setCellValueFactory(new PropertyValueFactory<Club, String>("pageFb"));
        colinsta.setCellValueFactory(new PropertyValueFactory<Club, String>("pageInsta"));

    }*/

    private void selectionChanged(ObservableValue<? extends Club >observable, Club oldVal, Club newVal) {
        ObservableList<Club> selectedItems = table.getSelectionModel().getSelectedItems();
        String getSelectedItem = selectedItems.isEmpty() ? "No Selected Item" : newVal.toString();

    }


    @FXML
    void update(ActionEvent event) {


        String value1 = intituleClubTF.getText();
        String value2 = emailClubTF.getText();
        String value3 = logoClubTF.getText();
        String value4 = pageFbTF.getText();
        String value5 = pageInstaTF.getText();

        int selectedId = getId();
        if (selectedId != -1) { // Proceed only if a valid selection exists
            cs.update(new Club(selectedId, value1, value2, value3, value4,value5)); // Assuming OffreStage constructor accepts ID, title, and description
            getData();

        } else {
            // Handle case where no selection is made
            // For example, show a message to the user indicating no selection
            JOptionPane.showMessageDialog(null, "Please select an item.");
        }
        searchClubs();



    }

    @FXML
    void delete(ActionEvent event) {
        Club club = table.getSelectionModel().getSelectedItem();
        cs.delete(club);
        ObservableList list = (ObservableList)cs.readAll();
        table.setItems(list);

    }




    @FXML
    void showEvenements(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/evenement.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnShowEv.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Gestion des événements");
            stage.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    private void searchClubs() {
        List<Club> listC = cs.readAll();
        ObservableList<Club> observableList = FXCollections.observableList(listC);
        FilteredList<Club> filteredData = new FilteredList<>(observableList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(club -> {
                if (newValue == null|| newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(club.getId()).toLowerCase().contains(lowerCaseFilter)
                        ||club.getIntitule().toLowerCase().contains(lowerCaseFilter) || club.getEmailClub().toLowerCase().contains(lowerCaseFilter) || club.getLogo().toLowerCase().contains(lowerCaseFilter)
                        || club.getPageFb().toLowerCase().contains(lowerCaseFilter)|| club.getPageInsta().toLowerCase().contains(lowerCaseFilter)  ;
            });
        });

        SortedList<Club> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }

    public void handleClicks(ActionEvent actionEvent) {
    }
    public void addClub(ActionEvent event){

        try {
        // Load the FXML file for the popup content
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ajouterClub.fxml"));
        BorderPane popupContent = loader.load();

        // Create the popup and set its content
        Popup popup = new Popup();
        popup.getContent().add(popupContent);
        AjouterClubController controller = loader.getController();
        controller.setPopup(popup);

        // Obtain the primary stage
        Stage primaryStage = (Stage) btnOrders.getScene().getWindow();

        // Show the popup
        popup.show(primaryStage);
    } catch (IOException e) {
        e.printStackTrace();
    }

}
//select data
    public void getData(){
        colintitule.setCellValueFactory(new PropertyValueFactory<Club, String>("intitule"));
        colemail.setCellValueFactory(new PropertyValueFactory<Club, String>("emailClub"));
        collogo.setCellValueFactory(new PropertyValueFactory<Club, String>("logo"));
        colfb.setCellValueFactory(new PropertyValueFactory<Club, String>("pageFb"));
        colinsta.setCellValueFactory(new PropertyValueFactory<Club, String>("pageInsta"));

        List<Club> listC = cs.readAll();
        ObservableList<Club> observableList = FXCollections.observableList(listC);
        table.setItems(observableList);

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        table.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);

    }


}
