package org.lgc.tij.generics;

import java.lang.reflect.Method;

/**
 * 利用反射实现潜在类型机制
 * 本例中perform()方法就是使用了潜在类型机制
 * 潜在类型机制在这里体现为：“我不关心你是什么类型，只要你可以speak()和sit()即可”
 * Created by laigc on 2017/2/11.
 */
class Mime {
    public void walkAgainstTheWind() {
    }

    public void sit() {
        System.out.println("Pretending to sit");
    }

    public void pushInvisibleWalls() {
    }

    @Override
    public String toString() {
        return "Mime";
    }
}

class SmartDog {
    public void speak() {
        System.out.println("Woof!");
    }

    public void sit() {
        System.out.println("Sitting");
    }

    public void reproduce() {
    }
}

class CommunicateReflectively {
    // perform可以动态地确定所需要的方法是否可用并调用它们
    public static void perform(Object speaker) {
        Class<?> spkr = speaker.getClass();
        try {
            try {
                Method speak = spkr.getMethod("speak");
                speak.invoke(speaker);
            } catch (NoSuchMethodException e) {
                System.out.println(speaker + " can not speak");
            }

            try {
                Method sit = spkr.getMethod("sit");
                sit.invoke(speaker);
            } catch (NoSuchMethodException e) {
                System.out.println(speaker + " can not sit");
            }
        } catch (Exception e) {
            throw new RuntimeException(speaker.toString(), e);
        }
    }
}

public class LatentReflection {
    public static void main(String[] args) {
        CommunicateReflectively.perform(new SmartDog());
        CommunicateReflectively.perform(new Mime());
    }
}
