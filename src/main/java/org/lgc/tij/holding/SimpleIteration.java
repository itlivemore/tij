package org.lgc.tij.holding;

import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;

import java.util.ArrayList;
import java.util.Iterator;

/**迭代器用于不同类型的容器的遍历，而不需要关心容器的类型
 * 迭代器通常被称为轻量级对象，创建它的代价小
 * Created by lgc on 16-12-15.
 */
public class SimpleIteration {
    public static void main(String[] args) {
        ArrayList<Pet> pets = Pets.arrayList(12);
        // iterator()返回一个Iterator，Iterato将准备好返回序列的第一个元素
        Iterator<Pet> iterator = pets.iterator();
        while (iterator.hasNext()){ // hasNext()检查序列中是否还有元素
            Pet p = iterator.next(); // next()获得下一个元素
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
        for (Pet p: pets) {
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
        iterator = pets.iterator();
        for (int i = 0; i < 6; i++) {
            iterator.next();
            // 使用remove()将移除next()产生的最后一个元素，这意味着在调用remove()之前必须先调用next()
            iterator.remove();
        }
        System.out.println(pets);
    }
}
