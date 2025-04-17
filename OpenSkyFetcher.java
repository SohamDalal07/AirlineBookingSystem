import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import com.google.gson.*;

public class OpenSkyFetcher {

    public static List<Flight> fetchFilteredFlights(String origin, String destination, String date, boolean isReturn) throws IOException {
        List<Flight> result = new ArrayList<>();

        try {
            // Fetch real-time data from OpenSky API
            String endpoint = "https://opensky-network.org/api/states/all";
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            JsonObject root = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();
            JsonArray states = root.getAsJsonArray("states");

            for (JsonElement e : states) {
                JsonArray arr = e.getAsJsonArray();
                String callsign = arr.get(1).isJsonNull() ? "N/A" : arr.get(1).getAsString().trim();
                String country = arr.get(2).isJsonNull() ? "Unknown" : arr.get(2).getAsString();

                if (country.equalsIgnoreCase(origin)) {
                    String flightNumber = UUID.randomUUID().toString();
                    String departureTime = "10:00 AM";
                    String arrivalTime = "1:00 PM";
                    int seats = new Random().nextInt(50) + 20;
                    String returnDate = isReturn ? date : "";

                    Flight f = new Flight(
                        flightNumber,
                        callsign,
                        origin,
                        destination,
                        date,
                        returnDate,
                        departureTime,
                        arrivalTime,
                        seats,
                        0.0  // Placeholder for price
                    );

                    result.add(f);
                }

                if (result.size() >= 10) break; // Limit for demo
            }

            // Save to JSON file for the session
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("flights.json");
            gson.toJson(result, writer);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            System.out.println("Error fetching data from OpenSky API: " + e.getMessage());
        }

        return result;
    }
}
