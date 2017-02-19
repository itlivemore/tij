package org.lgc.tij.containers;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 预测天气
 * Created by laigc on 2017/2/18.
 */
public class SpringDetector {
    public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception {
        Constructor<T> constructor = type.getConstructor(int.class);

        Map<Groundhog, Prediction> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(constructor.newInstance(i), new Prediction());
        }
        System.out.println(map);

        Groundhog groundhog = constructor.newInstance(3);
        System.out.println("Looking up prediction for " + groundhog);
        if (map.containsKey(groundhog)) {
            System.out.println(map.get(groundhog));
        } else {
            System.out.println("key not found: " + groundhog);
        }
    }

    public static void main(String[] args) {
        try {
            detectSpring(Groundhog.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
