/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Help;

import Model.Nhanvien;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author dell
 */
public class writeandread {

    public static Object read(String abc){
        try {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(abc));
        Object aa = ois.readObject();
        ois.close();
        return aa;
        } catch (Exception e) {
            System.out.println(""+e);
            return null;
        }
    }

    public static void write(String abc, Object a) {
        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(abc));
            oss.writeObject(a);
            oss.close();
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }


}
