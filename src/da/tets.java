/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dell
 */
public class tets {

    public static void main(String[] args) {
//        dateee tet = new dateee();
//        tet.setDay("2000-02-01");
//        tet.setNgay(Help.FormatDate.convertNguoc("2020-06-09"));
        String a = "2021-05-25";
        Date b = new Date(4, 2, 2);
        
        String e = "05-06-2020";
//        Date c = Help.FormatDate.convertNgayThangNam(e);
//        String d = Help.FormatDate.dinhDangnguoc(Help.FormatDate.convertNgayThangNam(e));
        System.out.println("" +Help.FormatDate.convertdaysangyear(e));
        System.out.println(""+Help.FormatDate.convertyearsangday(a));
    }
      public static void main1(String[] argv) {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    System.out.println(f.format(new Date()));
  }
}
