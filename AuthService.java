import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.HashSet;

public class AuthService implements HttpHandler {

    public static HashSet<String> validTokens = new HashSet<>(); // token storage

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            String response = "Only POST allowed";
            exchange.sendResponseHeaders(405, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
            return;
        }

        // In a real app: check username/password
        // Here we just auto-approve for simplicity
        String token = UUID.randomUUID().toString(); // generate token
        validTokens.add(token); // store token

        String response = "{\"token\": \"" + token + "\"}";
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
