/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;

/**
 *
 * @author dell
 */
public class Dbconnection {
    final static String user = "duy";
    final static String pw = "duyduc";
    final static String dbname = "DAM";
    final static String hostname = "127.0.0.1";
    final static String connectionURL = "jdbc:sqlserver://" + hostname
            + ":1433;databaseName=" + dbname;
    static Connection  conn;

    public Dbconnection() {
    }
    
//    public Connection conn(){
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection(connectionURL, user, pw);
//            return conn;
//        } catch (Exception e) {
//        }
//        return null;
//    }
    
    public static Connection getConnection(){
        if (conn==null) {
            try {
                openConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
        public static void openConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionURL, user, pw);
    }
}
