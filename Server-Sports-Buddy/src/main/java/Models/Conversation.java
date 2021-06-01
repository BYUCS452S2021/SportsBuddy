package Models;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private List<String> userIds;
    private List<ConversationMessage> messages;
    private String coonversationId;
    private String locationId;

    public Conversation(List<String> userIds, String coonversationId, String locationId) {
        this.userIds = userIds;
        this.messages = new ArrayList<>();
        this.coonversationId = coonversationId;
        this.locationId = locationId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<ConversationMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ConversationMessage> messages) {
        this.messages = messages;
    }

    public String getCoonversationId() {
        return coonversationId;
    }

    public void setCoonversationId(String coonversationId) {
        this.coonversationId = coonversationId;
    }

    public void addMessage(ConversationMessage message) {
        this.messages.add(message);
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
