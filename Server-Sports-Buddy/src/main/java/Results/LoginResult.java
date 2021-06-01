package Results;

import Models.Token;

public class LoginResult extends Result {
    private String token;
    private String userName;
    private String userId;

    public LoginResult() {
        super(true, null);
        this.token = null;
        this.userName = null;
        this.userId = null;
    }

    public LoginResult(Token token) {
        super(true, null);
        this.token = token.getToken();
        this.userName = token.getUserName();
        this.userId = token.getUserId();
    }

    public LoginResult(boolean success, String message) {
        super(success, message);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
