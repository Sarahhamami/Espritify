/*package Controllers;

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
}*/


package Controllers;

import entities.Categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.CategorieService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AfficherCategorieController implements Initializable {

    @FXML
    private Label TotalStage;

    @FXML
    private TableColumn actions;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private TextField filterField;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableView<Categorie> tablecat;

    @FXML
    private TableColumn<Categorie, String> type;

    ObservableList<Categorie> dataList; // Declaring dataList

    private final CategorieService cats = new CategorieService();


    @FXML
    void ajoutercat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ajoutercat.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup=new Popup();
            popup.getContent().add(popupContent);
            Ajoutercat controller= loader.getController();
            controller.setPopup(popup);
            Stage primaryStage = (Stage) btnOrders.getScene().getWindow();
            popup.show(primaryStage);
            popup.setOnHidden(event -> {
                initializeTable();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void initializeTable(){

        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        dataList = FXCollections.observableArrayList(cats.readAll()); // Initializing dataList
        tablecat.setItems(dataList); // Setting items to tableView
        searchCat();


    }


    public void deleteBtn()
    {
        TableColumn<Categorie, String> deleteItem = new TableColumn<>("");

        Callback<TableColumn<Categorie, String>, TableCell<Categorie, String>> deleteCellFactory = (param) -> {
            final TableCell<Categorie, String> cell = new TableCell<Categorie, String>() {
                final Button deleteButton = new Button("delete");

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        deleteButton.setOnAction(event -> {
                            Categorie c = getTableView().getItems().get(getIndex());
                            cats.delete(c);
                            initializeTable();
                        });
                        setGraphic(deleteButton);
                        setText(null);
                    }
                }
            };
            return cell;

        };
        deleteItem.setCellFactory(deleteCellFactory);
        tablecat.getColumns().add(deleteItem);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        deleteBtn();
        searchCat();


    }

    @FXML
    void searchCat() {
        FilteredList<Categorie> filteredData = new FilteredList<>(dataList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cat -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return cat.getType().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Categorie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablecat.comparatorProperty());
        tablecat.setItems(sortedData);
    }



}
