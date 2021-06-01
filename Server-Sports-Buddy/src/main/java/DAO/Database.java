package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;
    private UserDataAccess userDAO;
    private MessageDataAccess messageDAO;
    private LocationDataAccess locationDAO;
    private ConversationDataAccess conversationDAO;

    public Database() {}

    public Connection openConnection() throws DataAccessException {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:sportsbuddy.db";
            connection = DriverManager.getConnection(CONNECTION_URL);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }
        return connection;
    }

    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error unable to close database connection");
        }
    }

    public Connection getConnection() throws DataAccessException{
        if(connection == null) {
            return openConnection();
        } else {
            return connection;
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public UserDataAccess getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDataAccess userDAO) {
        this.userDAO = userDAO;
    }

    public MessageDataAccess getMessageDAO() {
        return messageDAO;
    }

    public void setMessageDAO(MessageDataAccess messageDAO) {
        this.messageDAO = messageDAO;
    }

    public LocationDataAccess getLocationDAO() {
        return locationDAO;
    }

    public void setLocationDAO(LocationDataAccess locationDAO) {
        this.locationDAO = locationDAO;
    }

    public ConversationDataAccess getConversationDAO() {
        return conversationDAO;
    }

    public void setConversationDAO(ConversationDataAccess conversationDAO) {
        this.conversationDAO = conversationDAO;
    }

    public void resetTables() throws DataAccessException {
        try (Statement stmt = connection.createStatement()) {
            String sql = "DELETE from users";
            stmt.executeUpdate(sql);
            sql = "DELETE from messages";
            stmt.executeUpdate(sql);
            sql = "DELETE from locations";
            stmt.executeUpdate(sql);
            sql = "DELETE from converations";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("Error clearing tables");
        }
    }
}
