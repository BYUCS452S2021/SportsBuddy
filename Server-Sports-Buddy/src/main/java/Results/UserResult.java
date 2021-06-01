package Results;

import Models.User;

public class UserResult extends Result{
    private User user;

    public UserResult() {
        super(false, new String());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
