package org.lgc.tij.generics;

/**
 * 在泛型中显式地传递类型的Class对象
 * 如在泛型中使用instanceof，现可以改用isInstance()
 * Created by laigc on 2017/1/7.
 */
class Buillding {
}

class House extends Buillding {
}

public class ClassTypeCapture<T> {
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg) {
        return kind.isInstance(arg); // 因为不能使用instanceof，所以这里用isInstance
    }

    public static void main(String[] args) {
        ClassTypeCapture<Buillding> buildCapture = new ClassTypeCapture<>(Buillding.class);
        System.out.println(buildCapture.f(new Buillding()));
        System.out.println(buildCapture.f(new House()));

        ClassTypeCapture<House> houseCapture = new ClassTypeCapture<>(House.class);
        System.out.println(houseCapture.f(new Buillding()));
        System.out.println(houseCapture.f(new House()));
    }
}
