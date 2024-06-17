package quiz5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
public class CommunicationManager {
    ArrayList<String> messages = new ArrayList<>();

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public String sendRequestMessage(String message) throws IOException, InterruptedException {
        //Add message
        messages.add(message);
        //Convert to json
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessages = objectMapper.writeValueAsString(messages);
        //Send to the server:
        // Create HTTP client
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        // Create HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                //add chatbot api here
                .uri(URI.create("http://localhost:8000"))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonMessages))
                .build();

        // Send HTTP request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //Add chatbot's answer to the messages
        messages.add(response.body());
        //Return chatbot's answer
        return response.body();
    }
}
