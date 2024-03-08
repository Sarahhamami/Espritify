package Controllers.Admin;

import entities.Club;
import entities.Evenement;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.ClubService;
import services.EvenementService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AjouterEvenementController implements Initializable {

    private final EvenementService es = new EvenementService();
    private final ClubService cs = new ClubService();

    @FXML
    private TextField club_idTF;

    @FXML
    private TextField titreTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField lieuTF;

    @FXML
    private DatePicker DateDebutTF;

    @FXML
    private DatePicker DateFinTF;

    @FXML
    private ComboBox<String> clubComboBox;

    @FXML
    private WebView webView;

    private WebEngine webEngine;

    @FXML
    private Popup popup;

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void closePopup() {
        popup.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeClubComboBox();

        webEngine = webView.getEngine();

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", new JavaConnector());
            }
        });

        webEngine.loadContent(generateHTML());
    }

    private String generateHTML() {
        return "<html><head><title>Map</title></head><body>"
                + "<div id='map' style='width: 400px; height: 300px;'></div>"
                + "<script src='https://cdn.jsdelivr.net/npm/leaflet@1.7.1/dist/leaflet.js'></script>"
                + "<script>"
                + "var mymap = L.map('map').setView([36.8065, 10.1815], 13);"
                + "L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {"
                + "   attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'"
                + "}).addTo(mymap);"
                + "var popup = L.popup();"
                + "function onMapClick(e) {"
                + "   popup"
                + "       .setLatLng(e.latlng)"
                + "       .setContent('Latitude: ' + e.latlng.lat.toFixed(6) + '<br>Longitude: ' + e.latlng.lng.toFixed(6))"
                + "       .openOn(mymap);"
                + "   javaConnector.handleClickOnMap(e.latlng.lat, e.latlng.lng);"
                + "}"
                + "mymap.on('click', onMapClick);"
                + "</script></body></html>";
    }

    public class JavaConnector {
        @SuppressWarnings("unused")
        public void handleClickOnMap(double latitude, double longitude) {
            Platform.runLater(() -> {
                try {
                    String encodedAddress = URLEncoder.encode(lieuTF.getText(), StandardCharsets.UTF_8);
                    URL url = new URL("https://nominatim.openstreetmap.org/reverse?format=json&lat=" +
                            latitude + "&lon=" + longitude + "&accept-language=fr");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                    StringBuilder response = new StringBuilder();
                    String output;
                    while ((output = br.readLine()) != null) {
                        response.append(output);
                    }

                    JSONParser parser = new JSONParser();
                    JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());
                    String address = (String) jsonResponse.get("display_name");

                    lieuTF.setText(address);

                    conn.disconnect();
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void initializeClubComboBox() {
        Map<Integer, String> clubsMap = cs.obtenirClubsMap();
        for (Map.Entry<Integer, String> entry : clubsMap.entrySet()) {
            clubComboBox.getItems().add(entry.getValue());
        }
    }
    private String traduireTexteAvecGoogleTranslate(String texte, String langueDestination) {
        try {
            String texteEncode = URLEncoder.encode(texte, StandardCharsets.UTF_8);
            String urlAPI = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=" + langueDestination + "&dt=t&q=" + texteEncode;
            URL url = new URL(urlAPI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;

            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            JSONParser parser = new JSONParser();
            JSONArray jsonResponse = (JSONArray) parser.parse(response.toString());

            // La traduction est disponible à l'index 0
            JSONArray traductionArray = (JSONArray) jsonResponse.get(0);
            JSONArray traductionElements = (JSONArray) traductionArray.get(0);
            String traduction = (String) traductionElements.get(0);

            return traduction;

        } catch (Exception e) {
            e.printStackTrace();
            return texte; // Retourne le texte original en cas d'erreur
        }
    }



    @FXML
    public void AjouterEvenement(ActionEvent actionEvent) {
        String intituleDuClub = clubComboBox.getValue();
        int idDuClub = cs.obtenirIdClubParIntitule(intituleDuClub);
        String titre = this.titreTF.getText();
        String description = this.descriptionTF.getText();
        String lieu = this.lieuTF.getText();
        LocalDate datedebut = this.DateDebutTF.getValue();
        LocalDate datefin = this.DateFinTF.getValue();
        int comparison = datedebut.compareTo(datefin);
        if (comparison>0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La date de début ne peut pas être supérieure à la date de fin.");
            alert.showAndWait();
        } else if (idDuClub == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un club.");
            alert.showAndWait();
        } else if (titre.isEmpty() || description.isEmpty() || lieu.isEmpty() || datedebut == null || datefin == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
            alert.showAndWait();
        } else {

            try {

                String query = URLEncoder.encode(lieu, StandardCharsets.UTF_8);
                URL url = new URL("https://nominatim.openstreetmap.org/search?q=" + query + "&format=json&addressdetails=1");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), StandardCharsets.UTF_8));

                //BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                StringBuilder response = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    response.append(output);
                }

                JSONParser parser = new JSONParser();
                JSONArray jsonResponse = (JSONArray) parser.parse(response.toString());

                if (jsonResponse.size() > 0) {
                    JSONObject firstResult = (JSONObject) jsonResponse.get(0);
                    String formattedAddress = (String) firstResult.get("display_name");

                    String lieuTraduit = traduireTexteAvecGoogleTranslate(formattedAddress, "fr");
                    Evenement evenement = new Evenement(idDuClub, titre, description, lieuTraduit, datedebut, datefin);
                    es.add(evenement);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText("Evenement ajoutée avec succès ");
                    alert.showAndWait();
                    closePopup();
                }
                conn.disconnect();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
