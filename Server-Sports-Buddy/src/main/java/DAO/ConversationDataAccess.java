package DAO;

import Models.Conversation;
import Models.ConversationMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConversationDataAccess {
    private Connection connection;

    public ConversationDataAccess(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void insertMessage(ConversationMessage conversationMessage) throws DataAccessException {
        String sql = "INSERT INTO conversations (conversationId, userId, message, date, locationId) " +
                "VALUES(?,?,?,?,?)";
        setParameters(sql, conversationMessage, "Error inserting conversation message");
    }

    private void setParameters(String sql, ConversationMessage conversationMessage, String errorMessage) throws DataAccessException{
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, conversationMessage.getConversationId());
            stmt.setString(2, conversationMessage.getUserId());
            stmt.setString(3, conversationMessage.getMessage());
            stmt.setObject(4, conversationMessage.getDate());
            stmt.setString(5, conversationMessage.getLocationId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(errorMessage);
        }
    }

    public List<ConversationMessage> getConversionByConversationId(String conversationId) throws DataAccessException{
        List<ConversationMessage> conversation = new ArrayList<ConversationMessage>();
        ConversationMessage message;
        ResultSet rs = null;
        String sql = "SELECT * FROM conversations WHERE conversationId = ? ORDER BY date";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, conversationId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                message = new ConversationMessage(rs.getString("userId"), rs.getString("message"),
                        rs.getDate("date"), rs.getString("locationId"), rs.getString("conversationId"));
                conversation.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error getting the conversation");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return conversation;
    }

    public List<Conversation> getConversationsByLocationId(String locationId) {
        return null;
    }
}
