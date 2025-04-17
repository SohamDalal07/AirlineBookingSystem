import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class FlightStorage {
    private static final String FILE_NAME = "flights.json";

    public static void saveFlights(List<Flight> flights) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print enabled ✅
            gson.toJson(flights, writer);
        } catch (IOException e) {
            System.out.println("Error saving flights: " + e.getMessage());
        }
    }

    public static List<Flight> loadFlights() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Flight>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void clearFlights() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            writer.write("[]");
        } catch (IOException e) {
            System.out.println("Error clearing flights: " + e.getMessage());
        }
    }
}
