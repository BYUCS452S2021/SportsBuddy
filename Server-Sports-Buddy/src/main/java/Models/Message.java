package Models;

import java.util.Date;

public class Message {
    private String senderId;
    private String message;
    private String receiverId;
    private Date date;

    public Message(String senderId, String message, String receiverId, Date date) {
        this.senderId = senderId;
        this.message = message;
        this.receiverId = receiverId;
        this.date = date;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
