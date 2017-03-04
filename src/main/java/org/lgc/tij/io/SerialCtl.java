package org.lgc.tij.io;

import java.io.*;

/**
 * 使用defaultReadObject()来控制序列化
 * 用ObjectOutputStream和ObjectInputStream会自动调用对象的writeObject()和readObject()
 * Created by laigc on 2017/3/4.
 */
public class SerialCtl implements Serializable {
    private String a;
    private transient String b;

    public SerialCtl(String a, String b) {
        this.a = "Not Transient: " + a;
        this.b = "Transient: " + b;
    }

    @Override
    public String toString() {
        return "SerialCtl{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        // 调用defaultWriteObject()来序列化普通字段
        stream.defaultWriteObject();
        // 序列化transient字段
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        // 调用defaultReadObject()来读取普通字段
        stream.defaultReadObject();
        // 读取transient字段
        b = (String) stream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtl serialCtl = new SerialCtl("Test1", "Test2");
        System.out.println("Before: " + serialCtl);

        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(buff);
        objectOutputStream.writeObject(serialCtl);

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buff.toByteArray()));
        SerialCtl serialCtl2 = (SerialCtl) objectInputStream.readObject();
        System.out.println("After: " + serialCtl2);
    }

}
