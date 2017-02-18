package org.lgc.tij.containers;

import net.mindview.util.Deque;

/**
 * 双向队列测试
 * Created by laigc on 2017/2/18.
 */
public class DequeTest {
    private static void fillTest(Deque<Integer> deque) {
        for (int i = 20; i < 27; i++) {
            deque.addFirst(i);
        }

        for (int i = 50; i < 55; i++) {
            deque.addLast(i);
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        fillTest(deque);
        System.out.println(deque);
        while (deque.size() != 0) {
            System.out.print(deque.removeFirst() + " ");
        }
        System.out.println();
        fillTest(deque);
        while (deque.size() != 0) {
            System.out.print(deque.removeLast() + " ");
        }
    }
}
