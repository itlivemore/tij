package org.lgc.tij.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理通过传递的其他参数来过滤某些方法的调用
 * Created by laigc on 2017/1/1.
 */
class MethodSelector implements InvocationHandler {
    private Object proxied;

    public MethodSelector(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("interesting")) {
            System.out.println("Proxy detected the interesting method");
        }
        return method.invoke(proxied, args);
    }
}

interface SomeMethods {
    void boring1();

    void boring2();

    void interesting(String args);

    void boring3();
}

class Implementation implements SomeMethods {

    @Override
    public void boring1() {
        System.out.println("Implementation.boring1");
    }

    @Override
    public void boring2() {
        System.out.println("Implementation.boring2");
    }

    @Override
    public void interesting(String args) {
        System.out.println("interesting args = [" + args + "]");
    }

    @Override
    public void boring3() {
        System.out.println("Implementation.boring3");
    }
}

public class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(SomeMethods.class.getClassLoader(), new Class[]{SomeMethods.class}, new MethodSelector(new Implementation()));
        proxy.boring1();
        proxy.boring2();
        proxy.interesting("hello");
        proxy.boring3();

    }
}
