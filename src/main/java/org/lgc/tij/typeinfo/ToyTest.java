package org.lgc.tij.typeinfo;

/**
 * Class的一些方法
 * Created by laigc on 2017/1/1.
 */
interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

class Toy {
    Toy() {
    }

    Toy(int i) {
    }
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
}

public class ToyTest {
    static void printInfo(Class cc) {
        System.out.println("Class name:" + cc.getName() + " is interface ? [" + cc.isInterface() + "]");
        // 不包含包名
        System.out.println("Simple name:" + cc.getSimpleName());
        // 全限定名
        System.out.println("Canonical name:" + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class cc = null;
        try {
            cc = Class.forName("org.lgc.tij.typeinfo.FancyToy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        printInfo(cc);
        // 获得所有的接口
        for (Class face : cc.getInterfaces()) {
            printInfo(face);
        }
        // 查询基类
        Class up = cc.getSuperclass();
        Object obj = null;
        try {
            // 需要默认的构造方法
            obj = up.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        printInfo(obj.getClass());
    }
}
