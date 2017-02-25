package org.lgc.tij.io;

import java.io.*;

/**
 * 使用DataOutputStream写入数据，并用DataInputStream恢复数据
 * Created by laigc on 2017/2/25.
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) {
        try {
            String path = StoringAndRecoveringData.class.getClassLoader().getResource("io/Data.txt").getPath();
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

            dataOutputStream.writeDouble(3.14159);
            dataOutputStream.writeUTF("That was pi");
            dataOutputStream.writeDouble(1.414);
            dataOutputStream.writeUTF("Square root of 2");
            dataOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream(path);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
            System.out.println(dataInputStream.readDouble());
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readDouble());
            System.out.println(dataInputStream.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
