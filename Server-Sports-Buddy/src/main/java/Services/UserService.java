package Services;

import DAO.DataAccessException;
import DAO.Database;
import DAO.TokenDataAccess;
import DAO.UserDataAccess;
import Models.Token;
import Results.UserResult;

import java.sql.Connection;

public class UserService {
    Database db;

    public UserService() {
        this.db = new Database();
    }

    public UserResult getUser(String authToken) {
        UserResult result = new UserResult();

        try {
            Connection connection = db.openConnection();
            UserDataAccess userDA = new UserDataAccess(connection);
            TokenDataAccess tokenDA = new TokenDataAccess(connection);

            if(tokenDA.checkIfTokenExists(authToken)) {

                Token token = tokenDA.getTokenObjectByToken(authToken);
                result.setUser(userDA.getUserByUserName(token.getUserName()));
                result.setSuccess(true);
            } else {
                throw new DataAccessException("Error bad auth token");
            }
            db.closeConnection(true);
        } catch (DataAccessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            try {
                db.closeConnection(false);
            }
            catch(DataAccessException b) {
                result.setMessage(b.getMessage());
            }
        }
        return result;
    }
}
