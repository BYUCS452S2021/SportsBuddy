package Models;

import java.util.UUID;

public class Token {
    private String token;
    private String userId;
    private String userName;

    public Token(String userName) {
        this.userName = userName;
        token = UUID.randomUUID().toString();
    }

    public Token(User user) {
        userName = user.getUserName();
        userId = user.getUserId();
        token = UUID.randomUUID().toString();
    }

    public Token(String token, String userId, String userName) {
        this.token = token;
        this.userId = userId;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
