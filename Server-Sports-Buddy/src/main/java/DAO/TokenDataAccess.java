package DAO;

import Models.Token;

import java.sql.*;

public class TokenDataAccess {
    private Connection connection;

    public TokenDataAccess(Connection connection) {
        this.connection = connection;
    }

    public void insertToken(Token token) throws DataAccessException {
        String sql = "INSERT INTO tokens (token, userId, userName) VALUES (?, ?, ?)";
        setParameters(sql, token, "Error encountered while inserting into the database");
    }

    public void setParameters(String sql, Token token, String message) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, token.getToken());
            stmt.setString(2, token.getUserId());
            stmt.setString(3, token.getUserName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(message);
        }
    }

    public boolean checkIfTokenExists(String token) throws DataAccessException {
        ResultSet rs = null;
        String sql = "Select * FROM tokens WHERE token = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, token);
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

    public void deleteToken(Token token) throws DataAccessException {
        String sql = "Delete FROM authToken WHERE userName = ? AND token = ?";
        setParameters(sql, token, "Error encountered while deleting token from database");
    }

    public void resetTable() throws DataAccessException {
        try (Statement stmt = connection.createStatement()) {
            String sql = "DELETE FROM tokens";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }

    public Token getTokenObjectByToken(String token) throws DataAccessException {
        Token authToken;
        ResultSet rs = null;
        String sql = "Select  * FROM tokens WHERE token = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, token);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authToken = new Token(rs.getString("token"),
                        rs.getString("userId"), rs.getString("userName"));
                return authToken;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
