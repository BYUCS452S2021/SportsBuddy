package Results;

import Models.Location;
import Models.Token;

import java.util.ArrayList;
import java.util.List;

public class RegisterResult extends Result {
    private String token;
    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Location location;
    private List<String> userSports;

    public RegisterResult() {
        super();
        token = "";
        userId = "";
        firstName = "";
        lastName = "";
        gender = "";
        userName = "";
        userPassword = "";
        userEmail = "";
        location = new Location();
        userSports = new ArrayList<>();
    }

    public RegisterResult(Token token) {
        this.token = token.getToken();
        this.userName = token.getUserName();
        this.userId = token.getUserId();

    }

    public RegisterResult(boolean success, String message) {
        super(success, message);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getUserSports() {
        return userSports;
    }

    public void setUserSports(List<String> userSports) {
        this.userSports = userSports;
    }


}
