package com.dbBook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnectionFactory {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres/dbbook";
    private static String USER_NAME = "postgres";
    private static String PASSWORD = "1234";


    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
