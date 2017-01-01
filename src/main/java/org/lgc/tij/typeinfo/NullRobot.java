package org.lgc.tij.typeinfo;

import net.mindview.util.Null;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

/**
 * 假设存在许多不同类型的Robot，我们想对每一种Robot类型都创建一个空对象，去执行某些特殊操作
 * 在本例中，即提供空对象所代表的Robot确切类型的信息。这些信息是通过动态代理捕获的
 * Created by laigc on 2017/1/1.
 */

class NullRobotProxyHandler implements InvocationHandler {
    private String nullName;
    private Robot proxied = new NRobot();

    NullRobotProxyHandler(Class<? extends Robot> type) {
        nullName = type.getSimpleName() + " NullRobot";
    }


    private class NRobot implements Null, Robot {
        @Override
        public String name() {
            return nullName;
        }

        @Override
        public String model() {
            return nullName;
        }

        @Override
        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }
}

public class NullRobot {
    public static Robot newNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(NullRobot.class.getClassLoader(), new Class[]{Null.class, Robot.class}, new NullRobotProxyHandler(type));
    }

    public static void main(String[] args) {
        Robot[] bots = {new SnowRemovalRobot("SnowBase"), newNullRobot(SnowRemovalRobot.class)};
        for (Robot bot : bots) {
            Robot.Test.test(bot);
        }
    }
}
