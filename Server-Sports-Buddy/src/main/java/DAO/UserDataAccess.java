package DAO;

import Models.Location;
import Models.User;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataAccess {
    private Connection connection;

    public UserDataAccess(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void insertUser(User user) throws DataAccessException {
        String sql = "INSERT INTO users (userId, firstName, lastName, gender, userName, "
            + "userPassword, userEmail) VALUES(?,?,?,?,?,?,?)";
        setParameters(sql, user, "Error encountered while inserting into the database");
    }

    public void setParameters(String sql, User user, String message) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getGender());
            stmt.setString(5, user.getUserName());
            stmt.setString(6, user.getUserPassword());
            stmt.setString(7, user.getUserEmail());
//            stmt.setObject(8, user.getUserSports());
//            stmt.setObject(9, user.getLocation());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(message);
        }
    }

    public boolean checkUsernameAndPassword(String userName, String password) throws DataAccessException {
        ResultSet rs = null;
        String sql = "Select * FROM users WHERE userName = ? AND userPassword = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error while accessing database data");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void resetTable() throws DataAccessException {
        try (Statement stmt = connection.createStatement()) {
            String sql = "DELETE FROM users";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }

    public User getUserByUserName(String userName) throws DataAccessException {
        User user;
        System.out.println(userName);
        ResultSet rs = null;
        String sql = "Select * FROM users WHERE userName = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                List<String> sports = new ArrayList<>();
                Location location = new Location();
                user = new User(rs.getString("userId"), rs.getString("firstName"),
                        rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("userName"), rs.getString("userPassword"),
                        rs.getString("userEmail"), sports, location);
                return user;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new DataAccessException("Error finding user");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }
}
