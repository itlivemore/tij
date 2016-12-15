package org.lgc.tij.holding;

import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**ListIterator是一个更加强大的Iterator的子类型，它只能用于各种List的访问
 * Created by lgc on 16-12-15.
 */
public class ListIteration {
    public static void main(String[] args) {
        ArrayList<Pet> pets = Pets.arrayList(8);
        ListIterator<Pet> iterator = pets.listIterator();
        while (iterator.hasNext()) {
            // 获取当前元素前一个元素和后一个元素的索引
            System.out.print(iterator.next() + " , " + iterator.nextIndex() + " , " + iterator.previousIndex() + "; ");
        }
        System.out.println();
        while (iterator.hasPrevious()) { // Iterator只能向前移动，ListIterator能够双向移动
            System.out.print(iterator.previous().id() + " ");
        }
        System.out.println();
        System.out.println(pets);
        // listIterator(n)方法创建一个一开始就指向列表索引为n的元素处的ListIterator
        iterator = pets.listIterator(3);
        while (iterator.hasNext()) {
            iterator.next();
            // set()方法替换ListIterator访问过的最后一个元素
            iterator.set(Pets.randomPet());
        }
        System.out.println(pets);
    }
}
