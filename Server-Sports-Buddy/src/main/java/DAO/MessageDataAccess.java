package DAO;

import Models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDataAccess {
    private Connection connection;

    public MessageDataAccess(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void insertMessage(Message message) throws DataAccessException {
        String sql = "INSERT INTO messages (senderId, message, receiverId, date) " +
                "VALUES(?,?,?,?)";
        setParameters(sql, message, "Error encountered while inserting message into the database");
    }

    public void setParameters(String sql, Message message, String errorMessage) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, message.getSenderId());
            stmt.setString(2, message.getMessage());
            stmt.setString(3, message.getReceiverId());
            stmt.setObject(4, message.getDate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(errorMessage);
        }
    }

    public List<Message> getUsersConversation(String senderId, String receiverId) throws DataAccessException {
        List<Message> messages = new ArrayList<Message>();
        Message message;
        ResultSet rs = null;
        String sql = "SELECT * FROM messages WHERE senderId = ? AND receiverId = ? ORDER BY date";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, senderId);
            stmt.setString(2, receiverId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                message = new Message(rs.getString("senderId"), rs.getString("message"),
                        rs.getString("receiverId"), rs.getDate("date"));
                messages.add(message);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error getting messages between users");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return messages;
    }
}
