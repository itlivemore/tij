package org.lgc.tij.io;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * transient(瞬时)关键字
 * 操作一个Serizable对象，那么所有序列化操作都会自动进行。
 * 为了能够予以控制，可以用transient(瞬时)关键字逐个字段地关闭序列化，
 * 它的意思是“不用麻烦你保存或恢复数据——我自己会处理的”
 * Created by laigc on 2017/3/4.
 */
public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password; //这里加了transient，不会被序列化

    public Logon(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Logon{" +
                "date=" + date +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Logon logon = new Logon("Hulk", "myLittlePony");
        System.out.println(logon);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Logon.out"));
            out.writeObject(logon);
            out.close();

            TimeUnit.SECONDS.sleep(1);
            // 恢复
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
            System.out.println("Recovering");
            logon = (Logon) in.readObject();
            System.out.println(logon);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
