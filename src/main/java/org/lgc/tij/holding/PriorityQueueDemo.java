package org.lgc.tij.holding;

import java.util.*;

/**优先级队列示例
 * Created by lgc on 16-12-21.
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            priorityQueue.add(random.nextInt(i + 10));
        }
        QueueDemo.printQueue(priorityQueue);//这里与QueueDemo中的队列值是一样的，但是顺序不一样

        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 2, 3, 14, 18, 20, 22, 25);
        priorityQueue = new PriorityQueue<Integer>(ints);
        QueueDemo.printQueue(priorityQueue); // 最小的值拥有最高的优先级

        // 通过传入Comparator改变排序
        priorityQueue = new PriorityQueue<Integer>(ints.size(), Collections.<Integer>reverseOrder());
        priorityQueue.addAll(ints);
        QueueDemo.printQueue(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW QBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ = new PriorityQueue<String>(strings);
        QueueDemo.printQueue(stringPQ); // 按字母的顺序排列，而空格也算值，且优先级比字母的高
        // 添加Comparator改变顺序
        stringPQ = new PriorityQueue<String>(strings.size(), Collections.<String>reverseOrder());
        stringPQ.addAll(strings);
        QueueDemo.printQueue(stringPQ);

        // 利用Set去除重复的字符
        Set<Character> charSet = new HashSet<Character>();
        for (char c : fact.toCharArray()) {
            charSet.add(c);
        }
        PriorityQueue<Character> characterPQ = new PriorityQueue<Character>();
        characterPQ.addAll(charSet);
        QueueDemo.printQueue(characterPQ);
    }
}
