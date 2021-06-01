package Models;

import Requests.RegisterRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Location location;
    private List<String> userSports;

    public User(String userId, String firstName, String lastName, String gender, String userName,
                String userPassword, String userEmail, List<String> userSports, Location location) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userSports = userSports;
        this.location = location;
    }

    public User(RegisterRequest request) {
        this.userId = UUID.randomUUID().toString();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.gender = request.getGender();
        this.userName = request.getUserName();
        this.userPassword = request.getUserPassword();
        this.userEmail = request.getUserEmail();
        this.location = request.getLocation();
        this.userSports = request.getUserSports();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userSports, user.userSports) &&
                Objects.equals(location, user.location);
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

    public List<String> getUserSports() {
        return userSports;
    }

    public void setUserSports(List<String> userSports) {
        this.userSports = userSports;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
