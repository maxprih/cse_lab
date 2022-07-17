package com.maxpri.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String USER = "postgres";
    private static final String PASSWORD = "050803";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
