/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.KhoaHoc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author dell
 */
public class DAOKhoahoc {

    Dbconnection dbconn = new Dbconnection();
    Statement st = null;

    public DAOKhoahoc() {
    }

    public List<KhoaHoc> getlistKh() {
        List<KhoaHoc> lst = new ArrayList<>();
        try {
            st = Dbconnection.getConnection().createStatement();
            String sql = "SELECT * FROM KHOAHOC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                KhoaHoc x = new KhoaHoc();
                x.setIdKh(rs.getInt(1));
                x.setTenkh(rs.getString(2));
                x.setThoiluong(rs.getInt(3));
                x.setHocphi(rs.getDouble(4));
                x.setNgaytao(Help.FormatDate.convertyearsangday(rs.getString(5)));
                x.setGhichu(rs.getString(6));
                x.setNgayKG(Help.FormatDate.convertyearsangday(rs.getString(7)));
                x.setMaCD(rs.getString(8));
                x.setManv(rs.getString(9));
                lst.add(x);
            }
            return lst;
        } catch (SQLException e) {
            System.out.println("loi cc " + e);
        }
        return null;
    }

    public boolean insertKH(KhoaHoc x) {
        try {
            x.setNgayKG(Help.FormatDate.convertdaysangyear(x.getNgayKG()));
            x.setNgaytao(Help.FormatDate.convertdaysangyear(x.getNgaytao()));
            st = dbconn.getConnection().createStatement();
            String sql = "INSERT KHOAHOC VALUES(N'" + x.getTenkh() + "'," + x.getThoiluong() + "," + x.getHocphi() + ",'" + x.getNgaytao() + "',"
                    + "'" + x.getGhichu() + "','" + x.getNgayKG() + "','" + x.getMaCD() + "','" + x.getManv() + "')";
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    //
    public boolean updateKH(KhoaHoc x) {
        try {
            x.setNgayKG(Help.FormatDate.convertdaysangyear(x.getNgayKG()));
            x.setNgaytao(Help.FormatDate.convertdaysangyear(x.getNgaytao()));
            st = dbconn.getConnection().createStatement();
            String sql = "EXEC UPDATEKH " + x.getIdKh() + " ,N'" + x.getTenkh() + "'," + x.getThoiluong() + "," + x.getHocphi() + ",'" + x.getNgaytao() + "',"
                    + "'" + x.getGhichu() + "','" + x.getNgayKG() + "','" + x.getMaCD() + "','" + x.getManv() + "')";
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean deleteKH(int x) {
        try {
            st = dbconn.getConnection().createStatement();
            String sql = "DELETE FROM KHOAHOC WHERE MAKH = "+x+"";
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
