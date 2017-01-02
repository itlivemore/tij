package org.lgc.tij.generics;

/** 使用泛型存放对象
 * Created by laigc on 2017/1/2.
 */
public class Holder3<T> {
    private T a;

    public Holder3(T a) {
        this.a = a;
    }

    public T get() {
        return a;
    }

    public void set(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Holder3<Automobile> holder3 = new Holder3<>(new Automobile());
        Automobile automobile = holder3.get();// 不用转型
//        holder3.set("Not an Automobile"); //error
//        holder3.set(1); // error
    }
}
