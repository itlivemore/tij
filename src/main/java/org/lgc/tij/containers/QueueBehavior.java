package org.lgc.tij.containers;

import net.mindview.util.Generator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * queue示例
 * 从结果中可以看到，除了优先级队列，Queue是先进先出
 * Created by laigc on 2017/2/18.
 */
public class QueueBehavior {
    private static int count = 10;

    static <T> void test(Queue<T> queue, Generator<T> generator) {
        for (int i = 0; i < count; i++) {
            queue.offer(generator.next());
        }

        while (queue.peek() != null) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }

    static class Gen implements Generator<String> {
        String[] s = ("one two three four five six seven eight nine ten").split(" ");

        int i;

        @Override
        public String next() {
            return s[i++];
        }
    }

    public static void main(String[] args) {
        test(new LinkedList<String>(), new Gen());
        test(new PriorityQueue<String>(), new Gen());
        test(new ArrayBlockingQueue<String>(count), new Gen());
        test(new ConcurrentLinkedQueue<String>(), new Gen());
        test(new LinkedBlockingQueue<String>(), new Gen());
        test(new PriorityBlockingQueue<String>(), new Gen());
    }

}
