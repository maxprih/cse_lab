package com.maxpri.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String USER = "s335073";
    private static final String PASSWORD = "zqt033";
    private static final String URL = "jdbc:postgresql://pg:5432/studs";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
