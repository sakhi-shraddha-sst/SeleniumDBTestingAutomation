package com.framework.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    private static final String USERNAME = "AUTOMATION";
    private static final String PASSWORD = "Auto@123";

    private DBConnectionManager() {}


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

    }
}
