/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Nguoihoc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class DAONguoihoc {

    static Dbconnection dbconn = new Dbconnection();

    public DAONguoihoc() {
    }

    public static void in() {

    }

    public static void main(String[] args) {
        DAONguoihoc aa = new DAONguoihoc();
        Nguoihoc x = new Nguoihoc();
        aa.InsertNguoihoc(x);
    }

    public List<Nguoihoc> lstNguoihocs() {
        List<Nguoihoc> lst = new ArrayList<>();
        try {
            Statement st = dbconn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM NGUOIHOC");
            while (rs.next()) {
                Nguoihoc x = new Nguoihoc();
                x.setId(rs.getString(1));
                x.setName(rs.getString(2));
                x.setGioitinh(rs.getInt(3));
                x.setSdt(rs.getString(4));
                x.setEmail(rs.getString(5));
                x.setGhichu(rs.getString(6));
                x.setNgayDK(Help.FormatDate.convertyearsangday(rs.getString(7)));
                x.setMaNV(rs.getString(9));
                x.setNgaysinh(Help.FormatDate.convertyearsangday(rs.getString(8)));
                x.setCheck(false);
                lst.add(x);
            }
            st.close();
            return lst;
        } catch (Exception e) {
        }
        return null;
    }
//INSERT NGUOIHOC VALUES('NH01','Lan Anh',0,'0912366995','Lanh@gmail.com',N'rất xinh gái','2021-01-20','2001-09-01','M01')

    public boolean InsertNguoihoc(Nguoihoc x) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            x.setNgayDK(Help.FormatDate.convertdaysangyear(x.getNgayDK()));
            x.setNgaysinh(Help.FormatDate.convertdaysangyear(x.getNgaysinh()));
            String sql = "INSERT NGUOIHOC VALUES('" + x.getId() + "',N'" + x.getName() + "'," + x.getGioitinh() + ",'" + x.getSdt() + "',"
                    + "'" + x.getEmail() + "',N'" + x.getGhichu() + "','" + x.getNgayDK() + "','" + x.getNgaysinh() + "','M01')";
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("loi cc " + e);
        }
        return false;
    }

    public boolean UpdateNguoihoc(Nguoihoc x) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            x.setNgayDK(Help.FormatDate.convertdaysangyear(x.getNgayDK()));
            x.setNgaysinh(Help.FormatDate.convertdaysangyear(x.getNgaysinh()));
            String sql = "EXEC UPDATENH'" + x.getId() + "',N'" + x.getName() + "'," + x.getGioitinh() + ",'" + x.getSdt()
                    + "','" + x.getEmail() + "',N'" + x.getGhichu() + "','" + x.getNgaysinh() + "'";
        } catch (Exception e) {
            System.out.println("sịt" + e);

        }
        return false;
    }

    public boolean deleteNguoihoc(String id) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            String sql = "DELETE FROM NGUOIHOC WHERE MANH = '" + id + "'";
            st.executeUpdate(sql);
            System.out.println("ok");
            return true;
        } catch (Exception e) {
            System.out.println("sịt" + e);
        }
        return false;
    }

}
