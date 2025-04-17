import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    public static void main(String[] args) {
        try {
            // 1. Send POST request to /login
            URL loginUrl = new URL("http://localhost:8000/login");

            HttpURLConnection loginConn = (HttpURLConnection) loginUrl.openConnection();
            loginConn.setRequestMethod("POST");
            loginConn.setDoOutput(true);

            int loginCode = loginConn.getResponseCode();
            System.out.println("Login response code: " + loginCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(loginConn.getInputStream()));
            String inputLine;
            StringBuilder loginResponse = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                loginResponse.append(inputLine);
            }
            in.close();

            System.out.println("Login response: " + loginResponse);

            // 2. Extract token from JSON
            String token = loginResponse.toString().split(":")[1].replace("\"", "").replace("}", "").trim();
            System.out.println("✅ Token: " + token);

            // 3. Use token to call /available-flights
            URL flightsUrl = new URL("http://localhost:8000/available-flights");
            HttpURLConnection flightConn = (HttpURLConnection) flightsUrl.openConnection();
            flightConn.setRequestMethod("GET");
            flightConn.setRequestProperty("Authorization", token);




            int flightsCode = flightConn.getResponseCode();
            System.out.println("Flights response code: " + flightsCode);

            BufferedReader fin = new BufferedReader(new InputStreamReader(flightConn.getInputStream()));
            StringBuilder flightsResponse = new StringBuilder();
            while ((inputLine = fin.readLine()) != null) {
                flightsResponse.append(inputLine);
            }
            fin.close();

        System.out.println("Flights data:\n" + flightsResponse);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
