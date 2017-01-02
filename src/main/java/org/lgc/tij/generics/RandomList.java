package org.lgc.tij.generics;

import java.util.ArrayList;
import java.util.Random;

/**
 * select()方法随机选择容器中的一个元素
 * Created by laigc on 2017/1/2.
 */
public class RandomList<T> {
    private ArrayList<T> storage = new ArrayList<>();
    private Random random = new Random(47);

    public void add(T item) {
        storage.add(item);
    }

    public T select() {
        return storage.get(random.nextInt(storage.size()));
    }

    public static void main(String[] args) {
        RandomList<String> randomList = new RandomList<>();
        for (String s : ("The quick brown fox jumped over the lazy brown dog").split(" ")) {
            randomList.add(s);
        }
        for (int i = 0; i < 11; i++) {
            System.out.print(randomList.select() + " ");
        }
    }
}
