package org.lgc.tij.generics;

import net.mindview.util.Generator;

/**
 * 测试BasicGenerator
 * Created by laigc on 2017/1/2.
 */
public class BasicGeneratorDemo {
    public static void main(String[] args) {
        Generator<CountedObject> generator = BasicGenerator.create(CountedObject.class);
        for (int i = 0; i < 6; i++) {
            System.out.println(generator.next());
        }
    }
}
