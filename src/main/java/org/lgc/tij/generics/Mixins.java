package org.lgc.tij.generics;

import java.util.Date;

/**
 * 混型，让一个类拥有多个类的特性
 * 本例中，在对象中混入拥有一个时间戳这样的属性，而另一个可以混入一个序列号。
 * 基本上是在使用代理
 * Created by lgc on 17-2-9.
 */
interface TimeStamped {
    long getTimeStamp();
}

class TimeStampedImp implements TimeStamped {
    private final long timeStamp;

    public TimeStampedImp() {
        timeStamp = new Date().getTime();
    }

    @Override
    public long getTimeStamp() {
        return timeStamp;
    }
}

interface SerialNumbered {
    long getSerialNumber();
}

class SerialNumberedImpl implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;

    @Override
    public long getSerialNumber() {
        return serialNumber;
    }
}

interface Basic {
    public void set(String val);

    public String get();
}

class BasicImpl implements Basic {
    private String value;

    @Override
    public void set(String val) {
        value = val;
    }

    @Override
    public String get() {
        return value;
    }
}

class Mixin extends BasicImpl implements TimeStamped, SerialNumbered {
    private TimeStamped timeStamped = new TimeStampedImp();
    private SerialNumbered serialNumbered = new SerialNumberedImpl();

    @Override
    public long getSerialNumber() {
        return serialNumbered.getSerialNumber();
    }

    @Override
    public long getTimeStamp() {
        return timeStamped.getTimeStamp();
    }
}

public class Mixins {
    public static void main(String[] args) {
        Mixin mixin1 = new Mixin();
        Mixin mixin2 = new Mixin();
        mixin1.set("test string1");
        mixin2.set("test string2");
        System.out.println(mixin1.get() + " " + mixin1.getTimeStamp() + " " + mixin1.getSerialNumber());
        System.out.println(mixin2.get() + " " + mixin2.getTimeStamp() + " " + mixin2.getSerialNumber());

    }
}

























