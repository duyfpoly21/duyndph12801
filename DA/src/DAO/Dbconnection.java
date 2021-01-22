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
    final String user = "duy";
    final String pw = "duyduc";
    final String dbname = "DAM";
    final String hostname = "127.0.0.1";
    final String connectionURL = "jdbc:sqlserver://" + hostname
            + ":1433;databaseName=" + dbname;
    Connection conn;

    public Dbconnection() {
    }
    
    public Connection conn(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionURL, user, pw);
            return conn;
        } catch (Exception e) {
        }
        return null;
    }
        public  Connection openConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionURL, user, pw);
            return conn;
    }
}
