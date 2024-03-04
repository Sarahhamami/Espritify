package Controllers;

import Controllers.Admin.GestionMessagerie;
import Controllers.Admin.ReclamationController;
import Controllers.Admin.ReponseReclamationController;
import entities.Question;
import javafx.scene.Node;
import javafx.scene.Scene;
import services.QuestionServices;
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

public class AfficherQuestionController implements Initializable {

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnQuestion;

    @FXML
    private Button btnQuizz;


    @FXML
    private Button btnOverview;

    @FXML
    private Button btnStage;
    @FXML
    private Button btnDossierStage;
    @FXML
    private Button btnEntretien;

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
    private TableColumn<Question, String> contenu;
    @FXML
    private TableColumn<Question, String> bonReponse;

    @FXML
    private TableColumn<Question, String> reponse1;

    @FXML
    private TableColumn<Question, String> reponse2;

    @FXML
    private TableColumn<Question, String> reponse3;

    @FXML
    private TableView<Question> tablequestion;

    private final QuestionServices qs= new QuestionServices();
    private ObservableList<Question> dataList;

    @FXML
    private Button btnRec;
    @FXML
    private Button btnRepRec;
    @FXML
    private Button btnMessg;
    @FXML
    private Button btnCours;
    @FXML
    private Button btnCategorie;

    @FXML
    public void handleClicks(ActionEvent actionEvent) {

        // this will be transformed based on the work of each button
        if (actionEvent.getSource() == btnDossierStage) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/DossierStage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnRec) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnRepRec) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnMessg) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnQuizz) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AfficherQuizz.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnQuestion) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AfficherQuestion.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnEntretien) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Entretien.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == btnOverview) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/OverView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource()==btnStage)
        {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Stage.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
        if(actionEvent.getSource()==btnCours)
        {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AfficherCours.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
        if(actionEvent.getSource()==btnCategorie)
        {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AfficherCategorie.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        searchQuestion();
        Delete();
        Update();
    }

    private void initializeTable() {
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        reponse1.setCellValueFactory(new PropertyValueFactory<>("reponse1"));
        reponse2.setCellValueFactory(new PropertyValueFactory<>("reponse2"));
        reponse3.setCellValueFactory(new PropertyValueFactory<>("reponse3"));
        bonReponse.setCellValueFactory(new PropertyValueFactory<>("bonneReponse"));
        dataList = FXCollections.observableArrayList(qs.readAll());
        tablequestion.setItems(dataList);
        searchQuestion();
    }

    void searchQuestion() {
        FilteredList<Question> filteredData = new FilteredList<>(dataList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(quizz -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return String.valueOf(quizz.getContenu()).toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablequestion.comparatorProperty());
        tablequestion.setItems(sortedData);
    }

    void Delete(){
        TableColumn<Question, String> deleteItem = new TableColumn<>("");
        Callback<TableColumn<Question, String>, TableCell<Question, String>> deleteCellFactory = (param) -> {
            final TableCell<Question, String> cell = new TableCell<Question, String>() {
                final Button deleteButton = new Button("Delete");
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        deleteButton.setOnAction(event -> {
                            Question q = getTableView().getItems().get(getIndex());
                            System.out.println(q.getId_question() + q.getContenu());
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
        tablequestion.getColumns().add(deleteItem);
    }

    void Update(){
        TableColumn<Question, String> updateItem = new TableColumn<>("");
        Callback<TableColumn<Question, String>, TableCell<Question, String>> updateCellFactory = (param) -> {
            final TableCell<Question, String> cell = new TableCell<Question, String>() {
                final Button updateButton = new Button("Update");

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        updateButton.setOnAction(event -> {
                            Question q = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/updateQuestion.fxml"));
                                BorderPane popupContent = loader.load();
                                UpdateQuestionController controller = loader.getController();
                                controller.setId(q.getId_question());
                                controller.setContenu(q.getContenu());
                                controller.setRep1(q.getReponse1());
                                controller.setRep2(q.getReponse2());
                                controller.setRep3(q.getReponse3());
                                controller.setBonRep(q.getBonneReponse());
                                Popup popup = new Popup();
                                popup.getContent().add(popupContent);
                                controller.setPopup(popup);
                                Stage primaryStage = (Stage) btnQuestion.getScene().getWindow();
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
        tablequestion.getColumns().add(updateItem);
    }

    @FXML
    void clickAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/addQuestion.fxml"));
            BorderPane popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            AjouterQuestionController controller= loader.getController();
            controller.setPopup(popup);
            Stage primaryStage = (Stage) btnQuestion.getScene().getWindow();
            popup.show(primaryStage);
            popup.setOnHidden(event -> {
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

    public void gogestmsg(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
        Parent root =loader.load();
        GestionMessagerie ars=loader.getController();
        btnDossierStage.getScene().setRoot(root);

    }

    public void Goreprec(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
        Parent root =loader.load();
        ReponseReclamationController ars=loader.getController();
        btnDossierStage.getScene().setRoot(root);
    }

    public void goRec(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
        Parent root =loader.load();
        ReclamationController ars=loader.getController();
        btnDossierStage.getScene().setRoot(root);
    }

    public void gotoQuizz(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/AfficherQuizz.fxml"));
        Parent root =loader.load();
        AfficherQuizzController ars=loader.getController();
        btnDossierStage.getScene().setRoot(root);
    }

    public void gotoQuestion(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/AfficherQuestion.fxml"));
        Parent root =loader.load();
        AfficherQuestionController ars=loader.getController();
        btnDossierStage.getScene().setRoot(root);
    }
}
