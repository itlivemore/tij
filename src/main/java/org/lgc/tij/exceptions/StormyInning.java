package org.lgc.tij.exceptions;

/**
 * Created by laigc on 2016/12/25.
 */
class BaseballException extends Exception {
}

class Foul extends BaseballException {
}

class Strike extends BaseballException {
}

abstract class Inning {
    public Inning() throws BaseballException {
    }

    public void event() throws BaseballException {

    }

    public abstract void atBat() throws Strike, Foul;

    public void walk() {

    }

    public void run() {
    }
}

class StormException extends Exception {
}

class RainedOut extends StormException {
}

class PopFoul extends Foul {
}

interface Storm {
    public void event() throws RainedOut;

    public void rainHard() throws RainedOut;

    public void run() throws RainedOut;
}

public class StormyInning extends Inning implements Storm {
    // 因为Inning的构造方法中抛出了BaseballException,所以这里也必须要抛出
//    public StormyInning() throws BaseballException {
//    }
    // 也可以在原来构造方法上添加新的异常
    public StormyInning() throws RainedOut, BaseballException {
    }

    public StormyInning(String s) throws Foul, BaseballException {
    }

    // 错误，Inning中walk()方法没有抛出异常，这里也不能抛出异常
//   public void walk() throws PopFoul {}
    @Override
    public void walk() {
    }

    //基类中run()方法没有抛出异常，接口中run()方法抛出了异常，这里不能抛出异常。
    // 即基类和接口有冲突时，以基类为准
//    @Override
//    public void run() throws RainedOut;{}

    // 基类中没有的方法，接口中有，接口中的rainHard()方法抛出异常，这里可以抛出
    @Override
    public void rainHard() throws RainedOut {
    }

    // 下面两句均有问题，原因是基类和接口中的event()抛出了两个不同的异常，这里可以不抛出
//    public void event() throws RainedOut{}
//    public void event() throws BaseballException{}
    public void event() {
    }

    // 可以在抛出原来异常的父类
    @Override
    public void atBat() throws PopFoul {
    }

    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
        } catch (PopFoul e) {
            System.out.println("PopFoul");
        } catch (RainedOut rainedOut) {
            System.out.println("RainedOut");
        } catch (BaseballException e) {
            System.out.println("BaseballException");
        }

        try {
            Inning i = new StormyInning();
            i.atBat();
        } catch (Strike e) {
            System.out.println("Strike");
        } catch (Foul e) {
            System.out.println("Foul");
        } catch (RainedOut rainedOut) {
            System.out.println("RainedOut");
        } catch (BaseballException e) {
            System.out.println("BaseballException");
        }
    }

}
