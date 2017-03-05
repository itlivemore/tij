package org.lgc.tij.enumerated;

import net.mindview.util.Generator;

import java.util.Random;

/**
 * 所有的enum都继承自java.lang.Enum类，所以enum类不能再继承其它的类
 * 但是可以实现一个或多个接口
 * Created by laigc on 2017/3/5.
 */

// 不能继承
//enum  CartoonCharacter extends Pet {}

enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

    private Random random = new Random(47);

    @Override
    public CartoonCharacter next() {
        return CartoonCharacter.values()[random.nextInt(values().length)];
    }
}

public class EnumImplementation {
    public static <T> void printNext(Generator<T> generator) {
        System.out.print(generator.next() + " , ");
    }

    public static void main(String[] args) {
        CartoonCharacter bob = CartoonCharacter.BOB;

        for (int i = 0; i < 10; i++) {
            printNext(bob);
        }
    }
}
