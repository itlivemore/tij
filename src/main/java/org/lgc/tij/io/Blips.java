package org.lgc.tij.io;

import java.io.*;

/**
 * 序列化的控制
 * Created by laigc on 2017/2/26.
 */
class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {
    // 注意这里的构造方法不是public
    Blip2() {
        System.out.println("Blip2 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}

public class Blips {
    public static void main(String[] args) throws Exception {
        System.out.println("Constructing objects");
        Blip1 blip1 = new Blip1();
        Blip2 blip2 = new Blip2();

        // 保存
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        System.out.println("Saving objects");
        objectOutputStream.writeObject(blip1);
        objectOutputStream.writeObject(blip2);
        objectOutputStream.close();

        // 恢复
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Blips.out"));
        System.out.println("Recovering blip1: ");
        blip1 = (Blip1) objectInputStream.readObject();
        System.out.println("Recovering blip2: ");
//        blip2 = (Blip2) objectInputStream.readObject();// 有异常，因为Blip2的构造方法不是public

    }
}
