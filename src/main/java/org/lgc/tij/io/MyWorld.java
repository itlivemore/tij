package org.lgc.tij.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 序列化存储对象，对象中引用其它对象
 * 注意查看打印结果返回的house引用地址。
 * 如果是将对象序列化到单一的流中，引用地址会是同一个。
 * Created by laigc on 2017/3/4.
 */
class House implements Serializable {

}

class Animal implements Serializable {
    private String name;
    private House preferedHouse;


    public Animal(String name, House preferedHouse) {
        this.name = name;
        this.preferedHouse = preferedHouse;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", preferedHouse=" + preferedHouse +
                '}';
    }
}

public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        System.out.println("animals:" + animals);

        ByteArrayOutputStream buff1 = new ByteArrayOutputStream();
        ObjectOutputStream out1 = new ObjectOutputStream(buff1);
        out1.writeObject(animals);
        out1.writeObject(animals);

        ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(buff2);
        out2.writeObject(animals);

        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buff1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buff2.toByteArray()));

        List animals1 = (List) in1.readObject();
        List animals2 = (List) in1.readObject();
        List animals3 = (List) in2.readObject();

        System.out.println("animals1: " + animals1);
        System.out.println("animals2: " + animals2);
        System.out.println("animals3: " + animals3);

    }
}
