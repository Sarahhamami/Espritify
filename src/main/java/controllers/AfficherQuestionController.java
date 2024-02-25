package controllers;

import entities.Question;
import entities.Quizz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.QuestionServices;
import services.QuizzService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherQuestionController implements Initializable {
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
    private TableColumn<Question, String> bon_Que;

    @FXML
    private TableColumn<Question, String> contenu;

    @FXML
    private TableColumn<Question, Integer> id_Question;

    @FXML
    private TableColumn<Question, Integer> num_Que;

    @FXML
    private TableColumn<Question, String> reponse;

    @FXML
    private TableView<Question> tablequizz;

    @FXML
    private TextField filterField;

    private final QuestionServices qs= new QuestionServices();
    private ObservableList<Question> dataList;

    @FXML
    void clickAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/addQuestion.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            AjouterQuestionController controller= loader.getController();
            controller.setPopup(popup);
            Stage primaryStage = (Stage) btnOrders.getScene().getWindow();
            popup.show(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        searchQuizz();
    }

    private void initializeTable() {
        id_Question.setCellValueFactory(new PropertyValueFactory<>("id_Que"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        bon_Que.setCellValueFactory(new PropertyValueFactory<>("bonne_rep"));
        num_Que.setCellValueFactory(new PropertyValueFactory<>("numQue"));

        dataList = FXCollections.observableArrayList(qs.readAll());
        tablequizz.setItems(dataList);
        searchQuizz();
    }

    void searchQuizz() {
        FilteredList<Question> filteredData = new FilteredList<>(dataList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(quizz -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(quizz.getId_Que()).toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(quizz.getContenu()).toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablequizz.comparatorProperty());
        tablequizz.setItems(sortedData);
    }
}
