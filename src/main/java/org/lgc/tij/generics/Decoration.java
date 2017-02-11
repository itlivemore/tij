package org.lgc.tij.generics;

import java.util.Date;

/**
 * 使用装饰器模式修改Mixins类
 * Created by laigc on 2017/2/11.
 */
class Basic2 {
    private String value;

    public void set(String val) {
        value = val;
    }

    public String get() {
        return value;
    }
}

class Decorator extends Basic2 {
    protected Basic2 basic2;

    public Decorator(Basic2 basic2) {
        this.basic2 = basic2;
    }

    @Override
    public void set(String val) {
        basic2.set(val);
    }

    @Override
    public String get() {
        return basic2.get();
    }
}

class TimeStamped2 extends Decorator {
    private final long timeStamp;

    public TimeStamped2(Basic2 basic2) {
        super(basic2);
        timeStamp = new Date().getTime();
    }

    public long getStamp() {
        return timeStamp;
    }
}

class SerialNumbered2 extends Decorator {
    private static long counter = 1;
    private final long serialNumber = counter++;

    public SerialNumbered2(Basic2 basic2) {
        super(basic2);
    }

    public long getSerialNumber() {
        return serialNumber;
    }
}

public class Decoration {
    public static void main(String[] args) {
        TimeStamped2 t = new TimeStamped2(new Basic2());
        TimeStamped2 t2 = new TimeStamped2(new SerialNumbered2(new Basic2()));

        SerialNumbered2 s = new SerialNumbered2(new Basic2());
        SerialNumbered2 s2 = new SerialNumbered2(new SerialNumbered2(new Basic2()));
    }
}
