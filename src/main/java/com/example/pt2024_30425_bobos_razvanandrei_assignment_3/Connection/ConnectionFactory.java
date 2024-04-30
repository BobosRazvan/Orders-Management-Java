package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/Orders Management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());


    private static ConnectionFactory singleInstance = new ConnectionFactory();


    private ConnectionFactory() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "PostgreSQL JDBC Driver not found!", e);
        }
    }


    public static Connection getConnection() {
        return singleInstance.createConnection();
    }






    private Connection createConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Connecting to the database failed!", ex);
        }
        return con;
    }
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Closing ResultSet failed!", e);
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Closing Statement failed!", e);
            }
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Closing Connection failed!", e);
            }
        }
    }

    public static String getClients(String name) {
        String query = "SELECT * FROM client WHERE name = ?";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    return null;
                }
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }


}
