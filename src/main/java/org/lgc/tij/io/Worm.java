package org.lgc.tij.io;

import java.io.*;
import java.util.Random;

/**
 * 通过链接的对象生成一个worm(蠕虫)对序列化机制进行了测试
 * Created by laigc on 2017/2/26.
 */
class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}

public class Worm implements Serializable {
    private static Random random = new Random(47);

    private Data[] datas = {new Data(random.nextInt(10)),
            new Data(random.nextInt(10)), new Data(random.nextInt(10))};

    private Worm next;
    private char c;

    public Worm(int i, char x) {
        System.out.println("Worm constructor: " + i);
        c = x;
        if (--i > 0) {
            next = new Worm(i, (char) (x + 1));
        }
    }

    public Worm() {
        System.out.println("Default constructor");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append("(");
        for (Data data : datas) {
            sb.append(data);
        }
        sb.append(")");
        if (next != null) {
            sb.append(next);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Worm worm = new Worm(6, 'a');
        System.out.println("worm = " + worm);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("worm.out"));
        objectOutputStream.writeObject("Worm storage");
        objectOutputStream.writeObject(worm);
        objectOutputStream.close();

        // 读取
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("worm.out"));
        String s = (String) objectInputStream.readObject();
        Worm worm2 = (Worm) objectInputStream.readObject();
        System.out.println(s + ",worm2=" + worm2);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream2.writeObject("Wrom storage\n");
        objectOutputStream2.writeObject(worm);
        objectOutputStream2.flush();

        ObjectInputStream objectInputStream1 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        s = (String) objectInputStream1.readObject();
        Worm worm3 = (Worm) objectInputStream1.readObject();
        System.out.println(s + "worm3=" + worm3);
    }
}
