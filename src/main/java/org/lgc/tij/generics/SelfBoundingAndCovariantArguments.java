package org.lgc.tij.generics;

/**
 * 使用自限定类型时，导出类中只有一个方法
 * Created by lgc on 17-2-7.
 */
interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {
}

public class SelfBoundingAndCovariantArguments {
    void testA(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
//        s1.set(sbs); // error，这里不能将基类型传递给set()，因为没有任何方法具有这样的签名。原先的方法被覆盖

    }
}
