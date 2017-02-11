package org.lgc.tij.generics;

import net.mindview.util.Tuple;
import net.mindview.util.TwoTuple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 与动态代理混合
 * Created by laigc on 2017/2/11.
 */
class MixinProxy implements InvocationHandler {
    Map<String, Object> delegatesByMethod;

    public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {
        delegatesByMethod = new HashMap<>();
        for (TwoTuple<Object, Class<?>> pair : pairs) {
            for (Method method : pair.second.getMethods()) {
                String methodName = method.getName();
                if (!delegatesByMethod.containsKey(methodName)) {
                    delegatesByMethod.put(methodName, pair.first);
                }
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Object delegate = delegatesByMethod.get(methodName);
        return method.invoke(delegate, args);
    }

    public static Object newInstance(TwoTuple... pairs) {
        Class[] interfaces = new Class[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            TwoTuple pair = pairs[i];
            interfaces[i] = (Class) pair.second;
        }
        ClassLoader classLoader = pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(classLoader, interfaces, new MixinProxy(pairs));
    }
}

public class DynamicProxyMixin {
    public static void main(String[] args) {
        Object mixin = MixinProxy.newInstance(Tuple.tuple(new BasicImpl(), Basic.class),
                Tuple.tuple(new TimeStampedImp(), TimeStamped.class),
                Tuple.tuple(new SerialNumberedImpl(), SerialNumbered.class));

        Basic b = (Basic) mixin;
        TimeStamped t = (TimeStamped) mixin;
        SerialNumbered s = (SerialNumbered) mixin;

        b.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getTimeStamp());
        System.out.println(s.getSerialNumber());

    }
}
