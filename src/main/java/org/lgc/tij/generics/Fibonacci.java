package org.lgc.tij.generics;

import net.mindview.util.Generator;

/**
 * 生成Fibonacci数列
 * Created by laigc on 2017/1/2.
 */
public class Fibonacci implements Generator<Integer> {
    private int count = 0;

    @Override
    public Integer next() {
        return fib(count++);
    }

    private Integer fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 18; i++) {
            System.out.print(fibonacci.next() + " ");
        }
    }
}
