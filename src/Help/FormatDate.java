/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Help;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NgocPJa
 */
public class FormatDate {

    public static String dinhDangNgayThangNam(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public static Date convertNgayThangNam(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(FormatDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        public static String dinhDangnguoc(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date convertNguoc(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(FormatDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String convertdaysangyear(String date){
        return Help.FormatDate.dinhDangnguoc(Help.FormatDate.convertNgayThangNam(date));
    }
    
    public static String convertyearsangday(String date){
        return Help.FormatDate.dinhDangNgayThangNam(convertNguoc(date));
    }
}