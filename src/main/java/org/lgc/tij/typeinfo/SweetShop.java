package org.lgc.tij.typeinfo;

/**
 * 一旦某个类的Class对象被载入内存，它就被用来创建这个类的所有对象。
 * Class对象仅在需要的时候才被加载，static初始化是在类加载时进行的
 * Created by laigc on 2017/1/1.
 */
class Candy {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie {
    static {
        System.out.println("Loading Cookie");
    }
}

public class SweetShop {
    public static void main(String[] args) {
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");
        try {
            Class.forName("org.lgc.tij.typeinfo.Gum");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("After Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");
    }
}
