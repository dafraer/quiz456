package quiz6;

import quiz5.CommunicationManager;

public class SpecialCommunicationManager {
    String helper = "There is no help in this cruel world";
    public String commonService(String message) {
        CommunicationManager communicationManager = new CommunicationManager();
        communicationManager.addMessage(message);
        communicationManager.addMessage(helper);
        return helper;
    }
}
