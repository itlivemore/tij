package org.lgc.tij.holding;

import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;

import java.util.*;

/**
 * Created by lgc on 16-12-15.
 */
public class CrossContainerIteration {
    /**
     * display()方法不包含任何有关它所遍历的序列的类型信息，这也展示了Iterator的真正威力：能够将遍历序列的操作与序列的底层的结构分离。
     * 正由于此，我们有时会说：迭代器统一了对容器的访问方式
     * @param iterator
     */
    public static void display(Iterator<Pet> iterator) {
        while (iterator.hasNext()) {
            Pet p = iterator.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Pet> pets = Pets.arrayList(8);
        LinkedList<Pet> petsLL = new LinkedList<Pet>(pets);
        HashSet<Pet> petHS = new HashSet<Pet>(pets);
        TreeSet<Pet> petsTS = new TreeSet<Pet>(pets);
        display(pets.iterator());
        display(petsLL.iterator());
        display(petHS.iterator());
        display(petsTS.iterator());
    }
}
