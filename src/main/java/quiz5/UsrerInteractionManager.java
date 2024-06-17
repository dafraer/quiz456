package quiz5;
import quiz6.SpecialCommunicationManager;

import java.io.IOException;
import java.util.Scanner;
public class UsrerInteractionManager {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, this is a chat bot!");
        while (true) {
            System.out.print("User: ");
            String userMessage = scanner.nextLine();
            if (userMessage.contains("help")) {
                SpecialCommunicationManager specialCommunicationManager = new SpecialCommunicationManager();
                String botMessage = specialCommunicationManager.commonService(userMessage);
                System.out.printf("Chat bot: %s\n", botMessage);
            } else {
                CommunicationManager communicatonManager = new CommunicationManager();
                String botMessage = communicatonManager.sendRequestMessage(userMessage);
                System.out.printf("Chat bot: %s\n", botMessage);
            }
        }
    }
}
