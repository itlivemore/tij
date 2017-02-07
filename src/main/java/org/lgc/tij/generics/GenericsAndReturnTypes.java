package org.lgc.tij.generics;

/**
 * 自限定泛型返回确切类型
 * Created by lgc on 17-2-7.
 */
interface GenericGetter<T extends GenericGetter<T>> {
    T get();
}

interface Getter extends GenericGetter<Getter> {
}

public class GenericsAndReturnTypes {
    void test(Getter getter) {
        Getter getter1 = getter.get(); // 可以返回确切的类型
        GenericGetter getter2 = getter.get(); // 也可以返回父类型
    }
}
