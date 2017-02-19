package org.lgc.tij.containers;

import java.util.WeakHashMap;

/**
 * WeakHashMap
 * Created by laigc on 2017/2/19.
 */
class Element {
    private String ident;

    public Element(String ident) {
        this.ident = ident;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    public int hashCode() {
        return ident.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Element && ident.equals(((Element) obj).ident);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
    }
}

class Key extends Element {
    public Key(String ident) {
        super(ident);
    }
}

class Value extends Element {
    public Value(String ident) {
        super(ident);
    }
}

public class CanonicalMapping {
    public static void main(String[] args) {
        int size = 1000;
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<>();
        for (int i = 0; i < size; i++) {
            Key key = new Key(Integer.toString(i));
            Value value = new Value(Integer.toString(i));
            if (i % 3 == 0) {
                keys[i] = key;
            }
            map.put(key, value);
        }
        System.gc();
        // 从结果中可以看到，垃圾回收器每隔三个键就会跳过一个，因为指向那个键的普通引用被存入了数组
        // 所以那些对象不能被垃圾回收器回收
    }
}
