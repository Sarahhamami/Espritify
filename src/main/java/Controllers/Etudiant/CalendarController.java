package Controllers.Etudiant;



import entities.Evenement;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import services.EvenementService;

import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hadjn
 */
public class CalendarController implements Initializable {
    ZonedDateTime dateFocus;
    ZonedDateTime today;
    private int patient;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;
    @FXML
    private Button retour;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        try {
            drawCalendar();
        } catch (SQLException ex) {
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  /*  public void initialize(int id)
    {

    }*/

    @FXML
    void backOneMonth(ActionEvent event) throws SQLException {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) throws SQLException {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }
//dessiner tous les champs de jours de chaque mois et chaque années
    private void drawCalendar() throws SQLException{
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();
        EvenementService es = new EvenementService();
        //List of activities for a given month
        Map<Integer, List<Evenement>> calendarActivityMap = createCalendarMap(es.getCalendarActivitiesMonth(dateFocus));

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<Evenement> calendarActivities = calendarActivityMap.get(currentDate);

                        if(calendarActivities != null){

                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    //placer les evenements dans calendrrier
    private void createCalendarActivity(List<Evenement> Activites, double rectangleHeight, double rectangleWidth, StackPane stackPane){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER); // set the alignment to CENTER

        vBox.setSpacing(rectangleHeight/10);


        for(Evenement activite : Activites){
            Text eventText = new Text(String.valueOf(activite.getTitre()));
            eventText.setWrappingWidth(rectangleWidth);
            vBox.getChildren().add(eventText);
        }

        Text dateText = new Text(String.valueOf(Activites.get(0).getDateDebut().getDayOfMonth()));
        dateText.setFill(Color.BLACK);
        dateText.setStyle("-fx-font-weight: bold;");
        vBox.getChildren().add(0, dateText);
        vBox.setStyle("-fx-background-color: #900000"+"; -fx-border-color: black; -fx-border-width: 1px;");

        stackPane.getChildren().add(vBox);


    }

    private Map<Integer, List<Evenement>> createCalendarMap(List<Evenement> calendarActivities) {
        Map<Integer, List<Evenement>> calendarActivityMap = new HashMap<>();

        for (Evenement activity: calendarActivities) {
            int activityDate = activity.getDateDebut().getDayOfMonth();
            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, Arrays.asList(activity));
            } else {
                List<Evenement> OldListByDate = calendarActivityMap.get(activityDate);

                List<Evenement> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

}
