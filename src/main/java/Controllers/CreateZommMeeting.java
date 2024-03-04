package Controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class CreateZommMeeting {

    public static void main(String[] args) {
        String accessToken = "pk7cnm-gRX-vJy94ZBg2EA";
        String userId = "Yk7IyfgfRzyplUyLfwRCqA";
        String createMeetingUrl = "https://api.zoom.us/v2/users/" + userId + "/meetings";

        try {
            URL url = new URL(createMeetingUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            // Set the meeting details
            String meetingDetails = "{\"topic\":\"Test Meeting\",\"type\":2}";
            outputStream.writeBytes(meetingDetails);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}