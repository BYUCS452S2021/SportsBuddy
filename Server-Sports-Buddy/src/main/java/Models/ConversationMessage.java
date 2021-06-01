package Models;

import java.util.Date;

public class ConversationMessage {
    private String userId;
    private String message;
    private Date date;
    private String locationId;
    private String conversationId;

    public ConversationMessage(String userId, String message, Date date, String locationId, String conversationId) {
        this.userId = userId;
        this.message = message;
        this.date = date;
        this.locationId = locationId;
        this.conversationId = conversationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
