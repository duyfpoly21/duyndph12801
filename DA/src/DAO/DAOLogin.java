/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Nhanvien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class DAOLogin {

    Dbconnection dbconn = new Dbconnection();
    public DAOLogin() {
    }

    public List<Nhanvien> lstLog() {
        List<Nhanvien> lst = new ArrayList<>();
        try {
            Statement st = dbconn.openConnection().createStatement();
            ResultSet rs = st.executeQuery("Select * from nhanvien");
            while (rs.next()) {
                Nhanvien nhanviennew = new Nhanvien(rs.getString(4), rs.getString(3), rs.getString(1), rs.getString(2));
                lst.add(nhanviennew);
            }
            return lst;
        } catch (Exception e) {
            System.out.println("loi:" + e);
            return null;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DAOLogin aa = new DAOLogin();
        DAO.Dbconnection bb = new Dbconnection();
        for (Nhanvien x : aa.lstLog()) {
            System.out.println("" + x.getName());
        } 

    }
}
