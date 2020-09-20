package org.sang.dao;

import java.sql.*;

public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/meeting?serverTimezone=GMT%2B8&useSSL=false", "root", "superxue");
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myforum?serverTimezone=GMT%2B8&useSSL=false", "root", "888888");
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
