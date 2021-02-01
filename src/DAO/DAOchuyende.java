/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ChuyenDe;
import Model.Nhanvien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class DAOchuyende {

    Dbconnection dbconn = new Dbconnection();
    
    public static void main(String[] args) {
        DAOchuyende a = new DAOchuyende();
        ChuyenDe x = new ChuyenDe("Cdd", "dgddg", 20, 100, "abc","");
        a.insertCD(x);
    }

    public List<ChuyenDe> lstchuyende() {
        List<ChuyenDe> lst = new ArrayList<>();
        try {
            Statement st = dbconn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CHUYENDE");
            while (rs.next()) {
                ChuyenDe x = new ChuyenDe();
                x.setIdCD(rs.getString(1));
                x.setNameCD(rs.getString(2));
                x.setHocphiCD(rs.getDouble(3));
                x.setThoiluongCD(rs.getInt(4));
                x.setMotaCD(rs.getString(5));
                x.setHinh(rs.getString(6));
                lst.add(x);
            }
            return lst;
        } catch (Exception e) {
            System.out.println("loi:" + e);
            return null;
        }
    }

    public Boolean insertCD(ChuyenDe x) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            String sql = " insert chuyende values('"+x.getIdCD()+"',N'"+x.getNameCD()+"',"+x.getHocphiCD()
                    +","+x.getThoiluongCD()+",'"+x.getMotaCD()+"','"+x.getHinh()+"')";
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Loi cc gi "+ e);
        }
        return false;
    }

    //exec UpdateNV 'M02','Linh','linhtt',0
    public Boolean updateNV(ChuyenDe x) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            String sql = "exec UpdateCD '"+x.getIdCD()+"',N'"+x.getNameCD()+"',"+x.getHocphiCD()+","+x.getThoiluongCD()+",N'"+x.getMotaCD()+"','"+x.getHinh()+"'";
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public Boolean xoaCD(ChuyenDe x) {
        try {
            Statement st = dbconn.getConnection().createStatement();
            String sql = "Delete from Chuyende where MACD = '" + x.getIdCD() + "'";
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
