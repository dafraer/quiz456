package quiz5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class DummyCommunicationManager {
    //Dummy server to test our application
    public static void main(String[] args) throws IOException, IOException {
        // Create an HTTP server listening on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Create a context for the root URI and map it to the handler
        server.createContext("/", new MyHandler());

        // Start the server
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8000");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Define the response JSON
            String response = "Good day";
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(response);

            // Send the response
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();
        }
    }
}
