package org.lgc.tij.containers;

import java.util.*;

/**
 * List的功能方法
 * basicTest()中包含每个List都可以执行的操作
 * iterMotion()使用Iterator遍历元素
 * iterManipulation()使用Iterator修改元素
 * testVisual()用以查看List的操作结果
 * Created by laigc on 2017/2/18.
 */
public class Lists {
    private static boolean b;
    private static String s;
    private static int i;
    private static Iterator<String> iterator;
    private static ListIterator<String> listIterator;

    public static void basicTest(List<String> list) {
        list.add(1, "x"); // 在位置1上添加
        list.add("x"); // 在末尾添加
        list.addAll(Countries.names());
        list.addAll(3, Countries.names());
        b = list.contains("1");
        b = list.containsAll(Countries.names());
        s = list.get(1);
        i = list.indexOf("1");
        b = list.isEmpty();
        iterator = list.iterator();
        listIterator = list.listIterator();
        i = list.lastIndexOf("1");
        list.remove(1);
        list.remove("3");
        list.set(1, "y");
        list.retainAll(Countries.names(25));
        list.removeAll(Countries.names(25));
        i = list.size();
        list.clear();
    }

    public static void iterMotion(List<String> list) {
        ListIterator<String> it = list.listIterator();
        b = it.hasNext();
        b = it.hasPrevious();
        s = it.next();
        i = it.nextIndex();
        s = it.previous();
        i = it.previousIndex();
    }

    public static void iterManipulation(List<String> list) {
        ListIterator<String> it = list.listIterator();
        it.add("47");
        // add()之后必须移动到另一个元素
        it.next();
        it.remove();
        // remove()之后必须移动到另一个元素
        it.next();
        it.set("47");
    }

    public static void testVisual(List<String> list) {
        System.out.println(list);
        List<String> list2 = Countries.names(25);
        System.out.println("list2:" + list2);
        list.addAll(list2);
        list.addAll(list2);
        System.out.println(list);
        ListIterator<String> it = list.listIterator(list.size() / 2);
        it.add("one");
        System.out.println(list);
        System.out.println(it.next());
        it.set("47");
        System.out.println(list);
        it = list.listIterator(list.size());
        while (it.hasPrevious()) {
            System.out.print(it.previous() + " ");
        }
        System.out.println();
        System.out.println("testVisual finished");
    }

    public static void testLinkedList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addAll(Countries.names(25));
        System.out.println(linkedList);
        linkedList.addFirst("one");
        linkedList.addFirst("two");
        System.out.println(linkedList);
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.removeLast());
        System.out.println(linkedList);
    }

    public static void main(String[] args) {
        basicTest(new LinkedList<>(Countries.names(25)));
        basicTest(new ArrayList<>(Countries.names(25)));

        iterMotion(new LinkedList<>(Countries.names(25)));
        iterMotion(new LinkedList<>(Countries.names(25)));

        iterManipulation(new ArrayList<>(Countries.names(25)));
        iterManipulation(new ArrayList<>(Countries.names(25)));

        testVisual(new ArrayList<>(Countries.names(25)));
        testLinkedList();
    }
}
