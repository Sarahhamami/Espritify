package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenTDBApiExample {
    public static void main(String[] args) throws IOException {
        // Exemple de requête pour obtenir 5 questions de la catégorie "Science : Ordinateurs"
        String apiUrl = "https://opentdb.com/api.php?amount=5&category=18&difficulty=easy&type=multiple";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Traiter la réponse JSON pour extraire les questions et les réponses
            System.out.println(response.toString());
        } else {
            System.out.println("Erreur lors de la requête HTTP : " + responseCode);
        }
    }
}
