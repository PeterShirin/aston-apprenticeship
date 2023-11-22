package ru.aston.apprenticeship.simpleservice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/product_database";
        String username = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, username, password);
    }
}
