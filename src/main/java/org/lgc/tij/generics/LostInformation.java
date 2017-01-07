package org.lgc.tij.generics;

import java.util.*;

/**
 * 在泛型代码内部，无法获得任何有关泛型参数类型的信息
 * Created by laigc on 2017/1/6.
 */
class Frob {
}

class Fnorkle {
}

class Quark<Q> {
}

class Particle<POSITION, MOMENTUM> {
}

public class LostInformation {
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<>();
        Map<Frob, Fnorkle> map = new HashMap<>();
        Quark<Fnorkle> quark = new Quark<>();
        Particle<Long, Double> particle = new Particle<>();
        // Class.getTypeParameters()返回一个TypeVariable对象数组，表示有泛型声明所声明的类型参数
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(particle.getClass().getTypeParameters()));
    }
}
