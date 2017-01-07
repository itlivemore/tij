package org.lgc.tij.generics;

/**
 * 泛型的擦除和兼容性意味着，使用泛型并不是强制的
 * Created by laigc on 2017/1/6.
 */
class GenericBase<T> {
    private T element;

    public void set(T arg) {
        arg = element;
    }

    public T get() {
        return element;
    }
}

class Derived1<T> extends GenericBase<T> {
}

// 可以不使用泛型
class Derived2 extends GenericBase {
}


// 有错误，编译器期望得到一个原生基类
//class Derived3 extends GenericBase<?> {}

public class ErasureAndInheritance {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 d2 = new Derived2();
        Object obj = d2.get();
        d2.set(obj); // 会有警告，为了关闭该警告，main方法上增加了@SuppressWarnings("unchecked")
    }
}
