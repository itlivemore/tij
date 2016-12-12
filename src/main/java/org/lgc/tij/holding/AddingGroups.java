package org.lgc.tij.holding;

import java.util.*;

/**添加一组元素
 * Created by lgc on 16-12-12.
 */
public class AddingGroups {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        Integer[] moreInts = new Integer[]{6,7,8,9,10};
        collection.addAll(Arrays.asList(moreInts));
        Collections.addAll(collection, 11,12,13,14,15);
        Collections.addAll(collection, moreInts);
        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20); // 使用Arrays.asList输出list，底层是数组，不能调整大小
        list.set(1,99);
//        list.add(21);//不能调整大小
    }
}
