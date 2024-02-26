package controllers;
import entities.Quizz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.QuizzService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherQuizzController implements Initializable {

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
    private VBox pnItems;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlQuestion;

    @FXML
    private Pane pnlReponse;

    @FXML
    private Pane pnlQuizz;

    @FXML
    private TableView<Quizz> tablequizz;
    @FXML
    private TableColumn<Quizz, Integer> id_Quizz;
    @FXML
    private TableColumn<Quizz, String> sujet;
    @FXML
    private TableColumn<Quizz, String> description;
    @FXML
    private TableColumn<Quizz, Integer> id_question;
    @FXML
    private TextField filterField;

    private final QuizzService qs= new QuizzService();
    private ObservableList<Quizz> dataList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        Delete();
        Update();
        searchQuizz();
    }

    private void initializeTable() {
        id_Quizz.setCellValueFactory(new PropertyValueFactory<>("id_quizz"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("descript"));
        id_question.setCellValueFactory(new PropertyValueFactory<>("question"));

        dataList = FXCollections.observableArrayList(qs.readAll());
        tablequizz.setItems(dataList);

        searchQuizz();
    }

    @FXML
    void clickAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/addQuizz.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            AjouterQuizzController controller= loader.getController();
            controller.setPopup(popup);
            Stage primaryStage = (Stage) btnOrders.getScene().getWindow();
            popup.show(primaryStage);
            initializeTable();
            dataList.clear(); // Clear the existing data
            dataList.addAll(qs.readAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                            System.out.println(q.getQuestion() + q.getSujet());
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
                                controller.UpdateQuizz(event,q);
                                Popup popup = new Popup();
                                popup.getContent().add(popupContent);
                                controller.setPopup(popup);
                                Stage primaryStage = (Stage) btnOrders.getScene().getWindow();
                                popup.show(primaryStage);
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

                return String.valueOf(quizz.getId_quizz()).toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(quizz.getSujet()).toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Quizz> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablequizz.comparatorProperty());
        tablequizz.setItems(sortedData);
    }

    public void AfficherReponse(MouseEvent mouseEvent) {
    }

    public void AfficherQuestion(MouseEvent mouseEvent) {
    }

    public void afficherQuizz(MouseEvent mouseEvent) {
    }
}
