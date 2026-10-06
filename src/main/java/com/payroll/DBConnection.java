package com.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/payroll_management";

    private static final String USER = "root";

    private static final String PASSWORD = "Payroll@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        try {
            Connection connection = getConnection();

            System.out.println("=================================");
            System.out.println("DATABASE CONNECTION SUCCESSFUL");
            System.out.println("Payroll Management System");
            System.out.println("=================================");

            connection.close();

        } catch (SQLException e) {

            System.out.println("DATABASE CONNECTION FAILED");
            e.printStackTrace();
        }
    }
}