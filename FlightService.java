import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlightService implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // ✅ Authorization check (optional if you're doing auth)
        String auth = exchange.getRequestHeaders().getFirst("Authorization");
        if (auth == null || auth.isEmpty()) {
            String response = "Unauthorized";
            exchange.sendResponseHeaders(401, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            return;
        }

        // ✅ Connect to OpenSky API
        URL url = new URL("https://opensky-network.org/api/states/all");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Optional: Add basic auth if using OpenSky credentials
        // String userpass = "username:password";
        // String basicAuth = "Basic " + Base64.getEncoder().encodeToString(userpass.getBytes());
        // conn.setRequestProperty("Authorization", basicAuth);

        // ✅ Read response
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder apiResponse = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            apiResponse.append(inputLine);
        }
        in.close();

        // ✅ Send back OpenSky data
        String json = apiResponse.toString();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, json.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }
}
