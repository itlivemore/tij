package org.lgc.tij.enumerated;

import java.text.DateFormat;
import java.util.Date;

/**
 * 为enum实例编写方法
 * 需要为enum定义一个或多个abstract方法，然后为每个enum实例实现该抽象方法
 * Created by laigc on 2017/3/5.
 */
public enum ConstantSpecificMethod {
    DATA_TIME {
        @Override
        String getInfo() {
            return DateFormat.getInstance().format(new Date());
        }
    },
    CLASSPATH {
        @Override
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        @Override
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod cs : values()) {
            System.out.println(cs.getInfo());
        }
    }
}
