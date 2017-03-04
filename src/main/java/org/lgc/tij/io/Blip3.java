package org.lgc.tij.io;

import java.io.*;

/**
 * 使用Externalizable控制序列化
 * Created by laigc on 2017/3/4.
 */
public class Blip3 implements Externalizable {
    private int i;
    private String s;

    public Blip3() {
        System.out.println("Blip3.Blip3()");
    }

    public Blip3(int i, String s) {
        System.out.println("Blip3.Blip3(int i, String s)");
        this.i = i;
        this.s = s;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        // 必须写下面两行
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        // 必须写下面两行
        // 恢复的时时会调用默认构造器，而默认构造器不会初始化s和i，所以在这里初始化
        s = (String) in.readObject();
        i = in.readInt();
    }

    @Override
    public String toString() {
        return s + i;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Constructing objects:");
        Blip3 blip3 = new Blip3(33, "A String");
        System.out.println(blip3);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Blip3.out"));
        System.out.println("saving object:");
        out.writeObject(blip3);
        out.close();

        // 恢复
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blip3.out"));
        System.out.println("Recovering :");
        blip3 = (Blip3) in.readObject();
        System.out.println(blip3);
    }
}
