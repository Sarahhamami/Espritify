package controllers;

import entities.Reponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ReponseServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherReponseController implements Initializable {
    @FXML
    private Button btnOrders;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlQuestion;

    @FXML
    private Pane pnlQuizz;

    @FXML
    private Pane pnlReponse;

    @FXML
    private TableColumn<Reponse, Integer> id_rep;

    @FXML
    private TableColumn<Reponse, String> contenu;

    @FXML
    private TableView<Reponse> tableRep;

    @FXML
    private TextField filterField;

    private final ReponseServices rs= new ReponseServices();
    private ObservableList<Reponse> dataList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        Delete();
        searchQuizz();
    }

    private void initializeTable() {
        id_rep.setCellValueFactory(new PropertyValueFactory<>("id_rep"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));

        dataList = FXCollections.observableArrayList(rs.readAll());
        tableRep.setItems(dataList);
        searchQuizz();
    }

    @FXML
    void clickAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/addReponse.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            AjouterReponseController controller= loader.getController();
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

    void Delete(){
        TableColumn<Reponse, String> deleteItem = new TableColumn<>("");
        Callback<TableColumn<Reponse, String>, TableCell<Reponse, String>> deleteCellFactory = (param) -> {
            final TableCell<Reponse, String> cell = new TableCell<Reponse, String>() {
                final Button deleteButton = new Button("Delete");

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        deleteButton.setOnAction(event -> {
                            Reponse r = getTableView().getItems().get(getIndex());
                            System.out.println(r.getId_rep() + r.getContenu());
                            rs.delete(r);
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
        tableRep.getColumns().add(deleteItem);
    }

    void searchQuizz() {
        FilteredList<Reponse> filteredData = new FilteredList<>(dataList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reponse -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(reponse.getId_rep()).toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(reponse.getContenu()).toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Reponse> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableRep.comparatorProperty());
        tableRep.setItems(sortedData);
    }

    public void afficherQuizz(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/afficherQuizz.fxml"));
        Parent root =loader.load();
        AfficherQuizzController ars=loader.getController();
        filterField.getScene().setRoot(root);
        btnOrders.getScene().setRoot(root);
    }

    public void AfficherQuestion(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/afficherQuestion.fxml"));
        Parent root =loader.load();
        AfficherQuestionController ars=loader.getController();
        filterField.getScene().setRoot(root);
        btnOrders.getScene().setRoot(root);
    }

    public void AfficherReponse(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/afficherReponse.fxml"));
        Parent root =loader.load();
        AfficherReponseController ars=loader.getController();
        filterField.getScene().setRoot(root);
        btnOrders.getScene().setRoot(root);
    }
}
