package org.lgc.tij.generics;

import net.mindview.util.Generator;
import org.lgc.tij.generics.coffee.Coffee;
import org.lgc.tij.generics.coffee.Latte;
import org.lgc.tij.generics.coffee.Mocha;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 适配器解决往容器中添加任意类型对象
 * Created by laigc on 2017/2/11.
 */
interface Addable<T> {
    void add(T t);
}

public class Fill2 {
    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++) {
            try {
                addable.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        for (int i = 0; i < size; i++) {
            addable.add(generator.next());
        }
    }
}

class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> collection;

    public AddableCollectionAdapter(Collection<T> collection) {
        this.collection = collection;
    }

    @Override
    public void add(T t) {
        collection.add(t);
    }
}

class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> collection) {
        return new AddableCollectionAdapter<T>(collection);
    }
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
    @Override
    public void add(T t) {
        super.add(t);
    }
}

class Fill2Test {
    public static void main(String[] args) {
        List<Coffee> carrier = new ArrayList<>();
        Fill2.fill(new AddableCollectionAdapter<Coffee>(carrier), Coffee.class, 3);
        Fill2.fill(Adapter.collectionAdapter(carrier), Latte.class, 2);
        for (Coffee coffee : carrier) {
            System.out.println(coffee);
        }
        System.out.println("-------------");
        AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<>();
        Fill2.fill(coffeeQueue, Mocha.class, 4);
        Fill2.fill(coffeeQueue, Latte.class, 1);
        for (Coffee coffee : coffeeQueue) {
            System.out.println(coffee);
        }
    }
}
