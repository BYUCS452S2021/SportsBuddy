package DAO;

import java.sql.Connection;

public class LocationDataAccess {
    private Connection connection;

    public LocationDataAccess(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
