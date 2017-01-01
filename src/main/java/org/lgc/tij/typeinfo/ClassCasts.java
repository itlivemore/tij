package org.lgc.tij.typeinfo;

/**
 * 用于Class引用的转型语法，cast()方法
 * Created by laigc on 2017/1/1.
 */
class Building {
}

class House extends Building {
}

public class ClassCasts {
    public static void main(String[] args) {
        Building b = new House();
        Class<House> houseClass = House.class;
        House house = houseClass.cast(b);
        house = (House) b;
    }
}
