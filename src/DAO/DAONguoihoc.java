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

    Dbconnection dbconn = new Dbconnection();

    public DAONguoihoc() {
    }

    public static void main(String[] args) {
        DAONguoihoc aa = new DAONguoihoc();
        for (Nguoihoc x : aa.lstNguoihocs()) {
            System.out.println("" + x.getName());
        }
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
                x.setMaNV(rs.getString(8));
                x.setNgaysinh(Help.FormatDate.convertyearsangday(rs.getString(9)));
                x.setCheck(false);
                lst.add(x);
            }
            st.close();
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean InsertNguoihoc(List<Nguoihoc> lst) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            for (Nguoihoc x : lst) {
                x.setNgayDK(Help.FormatDate.convertdaysangyear(x.getNgayDK()));
                x.setNgaysinh(Help.FormatDate.convertdaysangyear(x.getNgaysinh()));
                String sql = "INSERT NGUOIHOC VALUES('" + x.getId() + "',N'" + x.getName() + "'," + x.getGioitinh() + ",'" + x.getSdt()
                        + "','" + x.getEmail() + "',N'" + x.getGhichu() + "','" + x.getNgayDK() + "','" + x.getMaNV() + "','" + x.getNgaysinh() + "')";
                if (x.getCheck() == true) {
                    st.executeUpdate(sql);
                    x.setCheck(false);
                }
            }
            System.out.println("ok");
            return true;
        } catch (Exception e) {
            System.out.println("sịt" + e);

        }
        return false;
    }

    public boolean UpdateNguoihoc(List<Nguoihoc> lst) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            for (Nguoihoc x : lst) {
                x.setNgayDK(Help.FormatDate.convertdaysangyear(x.getNgayDK()));
                x.setNgaysinh(Help.FormatDate.convertdaysangyear(x.getNgaysinh()));
                String sql = "EXEC UPDATENH'" + x.getId() + "',N'" + x.getName() + "'," + x.getGioitinh() + ",'" + x.getSdt()
                        + "','" + x.getEmail() + "',N'" + x.getGhichu() + "','" + x.getNgaysinh() + "'";
                if (x.getCheck() == true) {
                    st.executeUpdate(sql);
                    x.setCheck(false);
                }
            }
            System.out.println("ok");
            return true;
        } catch (Exception e) {
            System.out.println("sịt" + e);

        }
        return false;
    }

    public boolean deleteNguoihoc(List<Nguoihoc> lst,String id) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            for (Nguoihoc x : lst) {
                String sql = "DELETE FROM NGUOIHOC WHERE MANH = '" +id+ "'";
                st.executeUpdate(sql);
            }
            System.out.println("ok");
            return true;
        } catch (Exception e) {
            System.out.println("sịt" + e);
        }
        return false;
    }

}
