package Controllers;

import entities.categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CategorieService;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficherCategorieController implements Initializable {

    @FXML
    private TableColumn<categorie, Integer> idcat;

    @FXML
    private TableView<categorie> tableView;

    @FXML
    private TableColumn<categorie, String> typecat;

    @FXML
    private TextField search;

    @FXML
    private TextField type;

    @FXML
    private TextField id;


    ObservableList<categorie> dataList; // Declaring dataList

    private final CategorieService cats = new CategorieService();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        searchCat();
    }

    public void initializeTable(){

        idcat.setCellValueFactory(new PropertyValueFactory<>("id"));
        typecat.setCellValueFactory(new PropertyValueFactory<>("type"));
        ArrayList<categorie> listcat = (ArrayList<categorie>) cats.readAll();
        dataList = FXCollections.observableArrayList(listcat); // Initializing dataList
        tableView.setItems(dataList); // Setting items to tableView
        searchCat();

    }

    @FXML
    void searchCat() {
        FilteredList<categorie> filteredData = new FilteredList<>(dataList, p -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cat -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(cat.getId()).toLowerCase().contains(lowerCaseFilter)
                        || cat.getType().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<categorie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    public int getId() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            return tableView.getItems().get(index).getId();
        } else {
            return -1;
        }
    }
    public void Delete() {
        int id = getId();
        categorie c=new categorie(id,"");
        if (id != -1) {
            try {
                cats.delete(c);
                searchCat();
                initializeTable();
                JOptionPane.showMessageDialog(null, "Delete Success!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No item selected for deletion.");
        }
    }

    public void Update(ActionEvent actionEvent) {
        try {
            String value1 = id.getText();
            int intValue = Integer.parseInt(value1);
            String value2 = type.getText();
            categorie c=new categorie(intValue,value2);
            cats.update(c);
            JOptionPane.showMessageDialog(null, "Update Success!");
            searchCat();
            initializeTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void ajouter(ActionEvent event) {
        cats.add(new categorie(type.getText()));
        searchCat();
        initializeTable();

    }
}
