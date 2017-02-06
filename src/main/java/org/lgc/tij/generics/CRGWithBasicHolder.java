package org.lgc.tij.generics;

/**
 * 自限定的类型
 * Created by lgc on 17-2-6.
 */
class Subtype extends BasicHolder<Subtype> {
}

public class CRGWithBasicHolder {
    public static void main(String[] args) {
        Subtype subtype1 = new Subtype();
        Subtype subtype2 = new Subtype();

        subtype1.setElement(subtype2);
        Subtype subtype3 = subtype1.getElement();
        subtype1.f();
    }
}
