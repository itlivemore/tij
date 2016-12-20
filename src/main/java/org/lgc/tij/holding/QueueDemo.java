package org.lgc.tij.holding;

import net.mindview.util.CountingGenerator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by lgc on 16-12-20.
 */
public class QueueDemo {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Random random = new Random(47);
        for (int i = 0; i < 10; i++) {
            queue.offer(random.nextInt(i + 10));
        }
        printQueue(queue);
        Queue<Character> qc = new LinkedList<Character>();
        for (char c : "Brotosaurus".toCharArray()) {
            qc.offer(c);
        }
        printQueue(qc);
    }

    private static void printQueue(Queue queue) {
        while (queue.peek() != null) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }
}
