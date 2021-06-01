package Services;

import DAO.*;
import Models.Token;
import Models.User;
import Requests.RegisterRequest;
import Results.RegisterResult;

import java.sql.Connection;

public class RegisterService {
    private Database db;

    public RegisterService() {
        db = new Database();
    }

    public RegisterResult register(RegisterRequest request) {
        RegisterResult result = new RegisterResult();
        try {
            Connection connection = db.openConnection();
            UserDataAccess userDA = new UserDataAccess(connection);
            TokenDataAccess tokenDA = new TokenDataAccess(connection);
            ConversationDataAccess conversationDA = new ConversationDataAccess(connection);
            LocationDataAccess locationDA = new LocationDataAccess(connection);
            MessageDataAccess messageDA = new MessageDataAccess(connection);
            User user = new User(request);
            userDA.insertUser(user);
            Token token = new Token(user);
            tokenDA.insertToken(token);
            result.setSuccess(true);
            result.setUserId(user.getUserId());
            result.setFirstName(user.getFirstName());
            result.setLastName(user.getLastName());
            result.setGender(user.getGender());
            result.setUserName(user.getUserName());
            result.setUserPassword(user.getUserPassword());
            result.setUserEmail(user.getUserEmail());
            result.setLocation(user.getLocation());
            result.setUserSports(user.getUserSports());
            result.setToken(token.getToken());
            db.closeConnection(true);
        } catch (DataAccessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException b) {
                result.setMessage(e.getMessage());
            }
        }
        return result;
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }
}
