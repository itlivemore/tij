package org.lgc.tij.exceptions;

/**
 * Created by laigc on 2016/12/25.
 */

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

public class Human {
    public static void main(String[] args) {
        // 抛出异常的时候，异常处理系统会按照代码的书写顺序找出“最近”的处理程序。
        // 找到匹配的处理程序之后，它就认为异常将得到处理，然后就不再继续查找
        try {
            throw new Sneeze();
        } catch (Sneeze sneeze) {
            System.out.println("Caught Sneeze");
        } catch (Annoyance annoyance) {
            System.out.println("Caught Annoyance");
        }

        // 查找的时候并不要求抛出的异常同处理程序所声明的异常完全匹配。
        // 派生类的对象也可以匹配其基类的处理程序
        try {
            throw new Sneeze();
        } catch (Annoyance annoyance) {
            System.out.println("Caught Annoyance");
        }

    }
}
