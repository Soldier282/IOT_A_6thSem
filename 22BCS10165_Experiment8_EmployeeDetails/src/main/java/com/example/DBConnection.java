package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=javaProject;encrypt=false",
                "_ROHITC_",
                ""
            );
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return con;
    }
}
