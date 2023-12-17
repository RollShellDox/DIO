package cn.edu.swu.zc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTool {
    public static Connection getDBConnection() throws ClassNotFoundException {
        String dbUrl = "jdbc:mysql://localhost:3306/bookstore";
        String dbUser = "root";
        String dbPass = "1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            return connection;
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
