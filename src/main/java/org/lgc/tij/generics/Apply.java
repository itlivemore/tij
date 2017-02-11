package org.lgc.tij.generics;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 将一个方法应用于序列
 * apply()方法，它能够将任何方法应用于某个序列中的所有对象
 * Created by laigc on 2017/2/11.
 */
public class Apply {
    public static <T, S extends Iterable<? extends T>> void apply(S seq, Method f, Object... args) {
        try {
            for (T t : seq) {
                f.invoke(t, args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Shape {
    public void roate() {
        System.out.println(this + " roate");
    }

    public void resize(int newSize) {
        System.out.println(this + " resize " + newSize);
    }
}

class Square extends Shape {
}

class FilledList<T> extends ArrayList<T> {
    public FilledList(Class<? extends T> type, int size) {
        try {
            for (int i = 0; i < size; i++) {
                add(type.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ApplyTest {
    public static void main(String[] args) throws Exception {
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shapes.add(new Shape());
        }
        Apply.apply(shapes, Shape.class.getMethod("roate"));
        Apply.apply(shapes, Shape.class.getMethod("resize", int.class), 5);

        List<Square> squares = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            squares.add(new Square());
        }
        Apply.apply(squares, Square.class.getMethod("roate"));
        Apply.apply(squares, Square.class.getMethod("resize", int.class), 5);

        Apply.apply(new FilledList<Shape>(Shape.class, 10), Shape.class.getMethod("roate"));
        Apply.apply(new FilledList<Shape>(Square.class, 10), Shape.class.getMethod("roate"));

        System.out.println("--------queue---------");
        Queue queue = new ArrayBlockingQueue(2);
        queue.add(new Shape());
        queue.add(new Square());
        Apply.apply(queue, Shape.class.getMethod("roate"));

    }
}
