package org.lgc.tij.generics;

/** 持有对象的类
 * Created by lgc on 17-2-6.
 */
public class BasicHolder<T> {
    T element;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}
