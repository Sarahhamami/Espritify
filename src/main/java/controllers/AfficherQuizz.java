package controllers;

import entites.Quizz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.QuizzService;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherQuizz implements Initializable {

    @FXML
    private TableView<Quizz> tableView;
    @FXML
    private TableColumn<Quizz, Integer> IdQuizz;
    @FXML
    private TableColumn<Quizz, String> Question;
    @FXML
    private TextField search;
    @FXML
    private TextField id;
    @FXML
    private TextField question;
    private final QuizzService qs = new QuizzService();
    private ObservableList<Quizz> dataList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        searchQuizz();
    }

    private void initializeTable() {
        IdQuizz.setCellValueFactory(new PropertyValueFactory<>("id"));
        Question.setCellValueFactory(new PropertyValueFactory<>("question"));
        dataList = FXCollections.observableArrayList(qs.readAll());
        tableView.setItems(dataList);
        searchQuizz();
    }

    public void ajouter() {
        qs.add(new Quizz(question.getText()));
        JOptionPane.showMessageDialog(null, "Ajout Success!");
        searchQuizz();
        initializeTable();
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
        Quizz q=new Quizz(id,"");
        if (id != -1) {
            try {
                qs.delete(q);
                searchQuizz();
                initializeTable();
                JOptionPane.showMessageDialog(null, "Delete Success!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No item selected for deletion.");
        }
    }

    public void Update() {
        try {
            String value1 = id.getText();
            int intValue = Integer.parseInt(value1);
            String value2 = question.getText();
            Quizz q=new Quizz(intValue,value2);
            qs.update(q);
            JOptionPane.showMessageDialog(null, "Update Success!");
            searchQuizz();
            initializeTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void searchQuizz() {
        FilteredList<Quizz> filteredData = new FilteredList<>(dataList, p -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(quizz -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(quizz.getId()).toLowerCase().contains(lowerCaseFilter)
                        || quizz.getQuestion().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Quizz> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }
}
