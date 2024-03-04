package Controllers.Admin;

import Controllers.AfficherQuestionController;
import Controllers.AfficherQuizzController;
import entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.ReclamationService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReclamationController implements Initializable {
    public static ReclamationService rs=new ReclamationService();

    @FXML
    private Label TotalNonTraité;

    @FXML
    private Label TotalRec;

    @FXML
    private Label TotalTraité;
    @FXML
    private PieChart chart;


    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnStage;

    @FXML
    private Button btnQuestion;
    @FXML
    private Button btnQuizz;
    @FXML
    private Button btnDossierStage;
    @FXML
    private Button btnEntretien;

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
    private Button btnReponseRec;
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
        refresh();
    }
    public void refresh(){
        List<Reclamation> R= rs.readAll();

        for (int i = 0; i < R.size(); i++) {
            try {
                final int j = i;
                Reclamation Reclamation = R.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/itemRec.fxml"));
                Node node = loader.load();

                ((Label) node.lookup("#nom")).setText(Reclamation.getNom_user());
                ((Label) node.lookup("#prenom")).setText(Reclamation.getPrenom_user());
                ((Label) node.lookup("#titre")).setText(Reclamation.getTitre());
                ((Label) node.lookup("#description")).setText(Reclamation.getDescription());
                ((Label) node.lookup("#date")).setText(Reclamation.getDate());
                ((Label) node.lookup("#etat")).setText(Reclamation.getEtat());
                ItemRecController irc=loader.getController();
                irc.setid_Sup(Reclamation.getId());
                irc.setId_user_reponse(Reclamation.getId());
                // Give the items some effect
                node.setOnMouseEntered(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });
                node.setOnMouseExited(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });

                pnItems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            search(); // Call search method whenever text changes in the filterField
        });

        int totrec=rs.countRec();
        int totnontraite=rs.countNonTraite();
        int tottraite=rs.countTraite();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Traité", tottraite),
                new PieChart.Data("Non Traité", totnontraite)
        );

        chart.setData(pieChartData);


        TotalRec.setText(String.valueOf(Integer.parseInt(String.valueOf(rs.countRec()))));
    TotalTraité.setText(String.valueOf(Integer.parseInt(String.valueOf(rs.countTraite()))));
    TotalNonTraité.setText(String.valueOf(Integer.parseInt(String.valueOf(rs.countNonTraite()))));

    }
    @FXML
    void search() {
        List<Reclamation> listRec = rs.readAll();
        ObservableList<Reclamation> observableList = FXCollections.observableList(listRec);
        FilteredList<Reclamation> filteredData = new FilteredList<>(observableList, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return String.valueOf(reclamation.getId()).toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getTitre().toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getNom_user().toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getPrenom_user().toLowerCase().contains(lowerCaseFilter)
                        || reclamation.getEtat().toLowerCase().contains(lowerCaseFilter)
                        ;
            });

            // Clear previous items in the UI
            pnItems.getChildren().clear();

            // Update UI with the filtered data
            for (Reclamation Rec : filteredData) {
                // Initialize node for each item
                Node node = null; // Update this line based on your actual FXML structure
                try {
                    node = FXMLLoader.load(getClass().getResource("/Admin/ItemRec.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (node != null) {
                    ((Label) node.lookup("#nom")).setText(Rec.getNom_user());
                    ((Label) node.lookup("#prenom")).setText(Rec.getPrenom_user());
                    ((Label) node.lookup("#titre")).setText(Rec.getTitre());
                    ((Label) node.lookup("#description")).setText(Rec.getDescription());
                    ((Label) node.lookup("#date")).setText(Rec.getDate());
                    ((Label) node.lookup("#etat")).setText(Rec.getEtat());
                    // Update other fields as needed
                    pnItems.getChildren().add(node); // Add the node to the UI
                }
            }
        });
    }

    @FXML
    void ReponseRec(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
        Parent root =loader.load();
        ReponseReclamationController ars=loader.getController();
        filterField.getScene().setRoot(root);

    }

    @FXML
    void Reclamation(ActionEvent event) throws IOException {


        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
        Parent root =loader.load();
        ReclamationController ars=loader.getController();
        filterField.getScene().setRoot(root);

    }


    @FXML
    void GoDiscussion(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/DiscussionInterne.fxml"));
        Parent root =loader.load();
        DiscussionInternController ars=loader.getController();
        filterField.getScene().setRoot(root);

    }

    @FXML
    void GoGestMessagerie(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
        Parent root =loader.load();
        GestionMessagerie ars=loader.getController();
        filterField.getScene().setRoot(root);

    }

    @FXML
    void PDF(ActionEvent event) {

        List<Reclamation> data = rs.readAll();

        try {
            // Create a new document PDF
            PDDocument document = new PDDocument();

            // Create a page in the document
            PDPage page = new PDPage();
            document.addPage(page);

            // Get the content of the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set font and font size
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Title
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 750);
            contentStream.showText("Reclamation Report");
            contentStream.endText();

            // Line separator
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(100, 740);
            contentStream.lineTo(500, 740);
            contentStream.stroke();

            // Set font for details
            contentStream.setFont(PDType1Font.HELVETICA, 10);

            // Content table
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);

            int rowNum = 1; // Row number counter

            // Header row
            contentStream.showText("Num");
            contentStream.newLineAtOffset(20, 0);
            contentStream.showText("Nom");
            contentStream.newLineAtOffset(50, 0);
            contentStream.showText("Prenom");
            contentStream.newLineAtOffset(60, 0);
            contentStream.showText("Titre");
            contentStream.newLineAtOffset(60, 0);
            contentStream.showText("Description");
            contentStream.newLineAtOffset(80, 0);
            contentStream.showText("Etat");
            contentStream.newLineAtOffset(40, 0);
            contentStream.showText("Date");
            contentStream.newLineAtOffset(-310, -15);

            for (Reclamation reclamation : data) {
                contentStream.showText(Integer.toString(rowNum));
                contentStream.newLineAtOffset(20, 0);

                contentStream.showText(reclamation.getNom_user());
                contentStream.newLineAtOffset(50, 0);

                contentStream.showText(reclamation.getPrenom_user());
                contentStream.newLineAtOffset(60, 0);

                contentStream.showText(reclamation.getTitre());
                contentStream.newLineAtOffset(60, 0);

                contentStream.showText(reclamation.getDescription());
                contentStream.newLineAtOffset(80, 0);

                contentStream.showText(reclamation.getEtat());
                contentStream.newLineAtOffset(40, 0);

                contentStream.showText(reclamation.getDate());
                contentStream.newLineAtOffset(-310, -15);

                rowNum++;
            }

            contentStream.endText();

            // Close the content of the page
            contentStream.close();

            // Save and open the document
            String outputPath = "C:\\Users\\a\\Desktop\\java\\Espritify\\rec.pdf";
            File file = new File(outputPath);
            document.save(file);

            // Close the document
            document.close();

            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gogestmsg(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Messagerie.fxml"));
        Parent root =loader.load();
        GestionMessagerie ars=loader.getController();
        btnCustomers.getScene().setRoot(root);

    }

    public void Goreprec(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/ReponseReclamation.fxml"));
        Parent root =loader.load();
        ReponseReclamationController ars=loader.getController();
        btnCustomers.getScene().setRoot(root);
    }

    public void goRec(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/Reclamation.fxml"));
        Parent root =loader.load();
        ReclamationController ars=loader.getController();
        btnCustomers.getScene().setRoot(root);
    }

    public void gotoQuizz(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/AfficherQuizz.fxml"));
        Parent root =loader.load();
        AfficherQuizzController ars=loader.getController();
        btnCustomers.getScene().setRoot(root);
    }

    public void gotoQuestion(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Admin/AfficherQuestion.fxml"));
        Parent root =loader.load();
        AfficherQuestionController ars=loader.getController();
        btnCustomers.getScene().setRoot(root);
    }

}



