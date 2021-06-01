package Services;

import DAO.DataAccessException;
import DAO.Database;
import DAO.TokenDataAccess;
import DAO.UserDataAccess;
import Models.Token;
import Models.User;
import Requests.LoginRequest;
import Results.LoginResult;

import java.sql.Connection;

public class LoginService {
    private Database db;

    public LoginService() {
        db = new Database();
    }

    public LoginResult login(LoginRequest request) {
        LoginResult result = new LoginResult();
        try {
            Connection connection = db.openConnection();
            UserDataAccess userDA = new UserDataAccess((connection));
            TokenDataAccess tokenDA = new TokenDataAccess(connection);
            User user = userDA.getUserByUserName(request.getUserName());
            if(userDA.checkUsernameAndPassword(request.getUserName(), request.getPassword())) {
                Token token = new Token(user);
                tokenDA.insertToken(token);
                result.setUserName(token.getUserName());
                result.setSuccess(true);
                result.setToken(token.getToken());
                result.setUserId(token.getUserId());
            } else {
                result.setMessage("Incorrect user or password");
                result.setUserId(null);
                result.setSuccess(false);
            }

            db.closeConnection(true);
        } catch (DataAccessException e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            try {
                result.setMessage(e.getMessage());
                db.closeConnection(false);
            } catch (DataAccessException b) {
                result.setMessage(b.getMessage());
            }
        }
        return result;
    }
}
