import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class FileManager {
    private static final String FLIGHT_FILE = "flights.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Clears the flight file at startup
    public static void clearFlights() {
        try (PrintWriter writer = new PrintWriter(FLIGHT_FILE)) {
            writer.print("[]");  // Empty JSON array
        } catch (IOException e) {
            System.out.println("Error clearing flight file: " + e.getMessage());
        }
    }

    // Saves flight list to JSON
    public static void saveFlights(List<Flight> flights) {
        try (Writer writer = new FileWriter(FLIGHT_FILE)) {
            gson.toJson(flights, writer);
        } catch (IOException e) {
            System.out.println("Error saving flights: " + e.getMessage());
        }
    }

    // Loads flight list from JSON
    public static List<Flight> loadFlights() {
        try (Reader reader = new FileReader(FLIGHT_FILE)) {
            return gson.fromJson(reader, new TypeToken<List<Flight>>() {}.getType());
        } catch (FileNotFoundException e) {
            // If file doesn't exist yet, return empty list
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error loading flights: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
