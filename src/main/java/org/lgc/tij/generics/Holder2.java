package org.lgc.tij.generics;

/** 存放一个Object，就可以存储不同类型的对象
 * Created by laigc on 2017/1/2.
 */
public class Holder2 {
    private Object a;

    public Holder2(Object a) {
        this.a = a;
    }

    public Object get() {
        return a;
    }

    public void set(Object a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Holder2 holder2 = new Holder2(new Automobile());
        Automobile a = (Automobile) holder2.get();
        holder2.set("Not an Automobile");
        String s = (String) holder2.get();
        holder2.set(1);
        Integer x = (Integer) holder2.get();
    }
}
