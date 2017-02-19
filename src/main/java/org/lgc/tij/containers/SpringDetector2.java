package org.lgc.tij.containers;

/** 测试重载了hashCode()和equals()方法的类
 * Created by laigc on 2017/2/18.
 */
public class SpringDetector2 {
    public static void main(String[] args) {
        try {
            SpringDetector.detectSpring(Groundhog2.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
