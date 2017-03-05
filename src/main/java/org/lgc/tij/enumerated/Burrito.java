package org.lgc.tij.enumerated;

import static org.lgc.tij.enumerated.Spicines.HOT;
import static org.lgc.tij.enumerated.Spicines.MEDIUM;
import static org.lgc.tij.enumerated.Spicines.NOT;

/**
 * 静态引入Enum
 * Created by laigc on 2017/3/4.
 */
public class Burrito {
    Spicines degree;

    public Burrito(Spicines degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Burrito{" +
                "degree=" + degree +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new Burrito(NOT));
        System.out.println(new Burrito(MEDIUM));
        System.out.println(new Burrito(HOT));
    }
}
