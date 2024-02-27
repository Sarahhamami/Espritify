package Controllers;

import entities.Categorie;
import entities.Cours;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.CoursService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class AfficherCoursController implements Initializable {

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
    private TableColumn<Cours, String> categorie;

    @FXML
    private TableColumn<Cours, String> contenu;

    @FXML
    private TableColumn<Cours, String> etat;

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
    private TableView<Cours> tablecours;




    @FXML
    private TableColumn<Cours, String> titre;
    ObservableList<Cours> dataList;
    CoursService cs=new CoursService();

    @FXML
    void ajoutercours() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ajoutercours.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup=new Popup();
            popup.getContent().add(popupContent);
            Ajoutercours controller= loader.getController();
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

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        categorie.setCellValueFactory(cellData -> {
            Cours cours = cellData.getValue();
            StringProperty categorieNameProperty = new SimpleStringProperty(cours.getcat().getType());
            return categorieNameProperty;
        });
        dataList = FXCollections.observableArrayList(cs.readAll());
        tablecours.setItems(dataList); // Setting items to tableView
        searchCat();


    }


    public void deleteBtn()
    {
        TableColumn<Cours, String> deleteItem = new TableColumn<>("");

        Callback<TableColumn<Cours, String>, TableCell<Cours, String>> deleteCellFactory = (param) -> {
            final TableCell<Cours, String> cell = new TableCell<Cours, String>() {
                final Button deleteButton = new Button("delete");

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        deleteButton.setOnAction(event -> {
                            Cours c = getTableView().getItems().get(getIndex());
                            cs.delete(c);
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
        tablecours.getColumns().add(deleteItem);

    }

    void Update(){
        TableColumn<Cours, String> updateItem = new TableColumn<>("");
        Callback<TableColumn<Cours, String>, TableCell<Cours, String>> updateCellFactory = (param) -> {
            final TableCell<Cours, String> cell = new TableCell<Cours, String>() {
                final Button updateButton = new Button("Update");
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        updateButton.setOnAction(event -> {
                            Cours c = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/upadateCours.fxml"));
                                BorderPane popupContent = loader.load();
                                UpdateCours controller = loader.getController();
                                controller.setId(c.getId());
                                controller.setTitre(c.getTitre());
                                controller.setContenu(c.getContenu());

                                Popup popup = new Popup();
                                popup.getContent().add(popupContent);
                                controller.setPopup(popup);
                                Stage primaryStage = (Stage) btnOrders.getScene().getWindow();
                                popup.show(primaryStage);
                                popup.setOnHidden(event1 -> {
                                    initializeTable();
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        setGraphic(updateButton);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        updateItem.setCellFactory(updateCellFactory);
        tablecours.getColumns().add(updateItem);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        deleteBtn();
        Update();
        searchCat();


    }

    @FXML
    void searchCat() {
        FilteredList<Cours> filteredData = new FilteredList<>(dataList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(c -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return c.getTitre().toLowerCase().contains(lowerCaseFilter) ;
            });
        });

        SortedList<Cours> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablecours.comparatorProperty());
        tablecours.setItems(sortedData);
    }



}
