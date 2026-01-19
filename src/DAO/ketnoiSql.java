package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ketnoiSql {
    public static void main(String[] args) {
        getConnection();
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doancoso1", "onlineAdmin", "12345");
            System.out.println("connect successfully!");
        } catch (SQLException ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    public static void close_connect(Connection c) {
    	try {
			if(c != null) {
				c.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
    }
}
