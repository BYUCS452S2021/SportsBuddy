package Requests;

import Models.Location;

import java.util.List;
import java.util.UUID;

public class RegisterRequest {
    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Location location;
    private List<String> userSports;

    public RegisterRequest(String firstName, String lastName, String gender, String userName, String userPassword,
                           String userEmail, Location location, List<String> userSports) {
        this.userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.location = location;
        this.userSports = userSports;
    }

    public String getUserId() {
        return userId;
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
