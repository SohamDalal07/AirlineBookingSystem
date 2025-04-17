import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        //Login endpoint
        server.createContext("/login", new AuthService());

        // Available flights endpoint
        server.createContext("/available-flights", new FlightService());

        // Add this test endpoint
        server.createContext("/test", exchange -> {
            String response = "Server is running!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.setExecutor(null); // creates a default executor
        server.start();

        System.out.println("Server is running on http://localhost:8000");
    }
}
