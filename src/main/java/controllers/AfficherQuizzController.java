package Controllers;

import Entities.Quizz;
import Services.QuizzServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherQuizzController implements Initializable {

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnQuestion;

    @FXML
    private Button btnQuizz;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

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
    private TextField filterField;
    @FXML
    private TableColumn<Quizz, String> question;
    @FXML
    private TableColumn<Quizz, String>  sujet;
    @FXML
    private TableColumn<Quizz, String>  description;
    @FXML
    private TableView<Quizz> tablequizz;
    private final QuizzServices qs= new QuizzServices();
    private ObservableList<Quizz> dataList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        searchQuizz();
        Delete();
        Update();
    }

    private void initializeTable() {
        question.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId_question().getContenu()));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dataList = FXCollections.observableArrayList(qs.readAll());
        tablequizz.setItems(dataList);
        searchQuizz();
    }

    void Delete(){
        TableColumn<Quizz, String> deleteItem = new TableColumn<>("");
        Callback<TableColumn<Quizz, String>, TableCell<Quizz, String>> deleteCellFactory = (param) -> {
            final TableCell<Quizz, String> cell = new TableCell<Quizz, String>() {
                final Button deleteButton = new Button("Delete");
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        deleteButton.setOnAction(event -> {
                            Quizz q = getTableView().getItems().get(getIndex());
                            qs.delete(q);
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
        tablequizz.getColumns().add(deleteItem);
    }

    void Update(){
        TableColumn<Quizz, String> updateItem = new TableColumn<>("");
        Callback<TableColumn<Quizz, String>, TableCell<Quizz, String>> updateCellFactory = (param) -> {
            final TableCell<Quizz, String> cell = new TableCell<Quizz, String>() {
                final Button updateButton = new Button("Update");
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        updateButton.setOnAction(event -> {
                            Quizz q = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/updateQuizz.fxml"));
                                BorderPane popupContent = loader.load();
                                UpdateQuizzController controller = loader.getController();
                                controller.setId(q.getId_quizz());
                                controller.setSujet(q.getSujet());
                                controller.setDescription(q.getDescription());
                                Popup popup = new Popup();
                                popup.getContent().add(popupContent);
                                controller.setPopup(popup);
                                Stage primaryStage = (Stage) btnQuizz.getScene().getWindow();
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
        tablequizz.getColumns().add(updateItem);
    }

    @FXML
    void searchQuizz() {
        FilteredList<Quizz> filteredData = new FilteredList<>(dataList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(quizz -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(quizz.getSujet()).toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Quizz> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablequizz.comparatorProperty());
        tablequizz.setItems(sortedData);
    }

    @FXML
    void clickAdd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/addQuizz.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            AjouterQuizzController controller= loader.getController();
            controller.setPopup(popup);
            Stage primaryStage = (Stage) btnQuizz.getScene().getWindow();
            popup.show(primaryStage);
            popup.setOnHidden(event1 -> {
                initializeTable();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AfficherQuizz(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/afficherQuizz.fxml"));
        Parent root =loader.load();
        AfficherQuizzController ars=loader.getController();
        filterField.getScene().setRoot(root);
    }

    public void AfficherQuestion(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/afficherQuestion.fxml"));
        Parent root =loader.load();
        AfficherQuestionController ars=loader.getController();
        filterField.getScene().setRoot(root);
    }
}
