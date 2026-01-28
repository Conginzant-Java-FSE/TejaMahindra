package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {
    private static final Logger logger = LogManager.getLogger(DBConnection.class);
    private static final String URL = "jdbc:mysql://localhost:3306/revhire1";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error("Failed to connect to the database.", e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        } else {
            try {
                if (instance.connection.isClosed()) {
                    instance = new DBConnection();
                }
            } catch (SQLException e) {
                logger.error("Error checking connection status", e);
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
