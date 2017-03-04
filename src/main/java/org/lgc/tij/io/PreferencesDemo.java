package org.lgc.tij.io;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Preferences是一个键-值集合，存储在一个节点层次结构中。
 * 它只能用于小的，受限的数据集合——我们只能存储基本类型和字符串.
 * 用于存储和读取用户的偏好以及程序配置项的设置
 * Created by laigc on 2017/3/4.
 */
public class PreferencesDemo {
    public static void main(String[] args) throws BackingStoreException {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putInt("Companions", 4);
        prefs.putBoolean("Are there witches?", true);

        int usageCount = prefs.getInt("UsageCount", 0); // 第二个参数是默认值
        usageCount++;
        prefs.putInt("UsageCount", usageCount);

        for (String key : prefs.keys()) {
            System.out.println(key + ": " + prefs.get(key, null));
        }
        System.out.println("How many companions does Dorothy have? " +
                prefs.getInt("Companions", 0));
    }
}
