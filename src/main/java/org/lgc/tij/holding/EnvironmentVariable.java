package org.lgc.tij.holding;

import java.util.Map;
import java.util.Set;

/**显示所有的操作系统环境变量
 * Created by laigc on 2016/12/24.
 */
public class EnvironmentVariable {
    public static void main(String[] args) {
        Set<Map.Entry<String, String>> entries = System.getenv().entrySet();
        for (Map.Entry<String,String> entry:
             entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
