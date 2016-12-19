package org.lgc.tij.holding;

import org.lgc.tij.typeinfo.pets.Hamster;
import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;
import org.lgc.tij.typeinfo.pets.Rat;

import static net.mindview.util.Print.*;
import java.util.LinkedList;

/**
 * Created by lgc on 16-12-19.
 */
public class LinkedListFeature {
    public static void main(String[] args) {
        LinkedList<Pet> pets = new LinkedList<Pet>(Pets.arrayList(5));
        print(pets);
        // getFirst(),element(),peek()都返回列表的第一上元素，而并不移除它
        // 区别在于当List为空时，getFirst(),element()抛出NoSuchElemetException，peek()返回null
        print("pets.getFirst(): " + pets.getFirst());
        print("pets.element(): " + pets.element());
        print("pets.peek(): " + pets.peek());

        // remove(),removeFirst(),poll()移除并返回列表的头，remove(),removeFirst()在列表为空时抛出NoSuchElementException
        // poll()在列表为空时返回null
        print("pets.remove(): " + pets.remove());
        print("pets.removeFirst(): " + pets.removeFirst());
        print("pets.poll() " + pets.poll());

        print(pets);

        pets.addFirst(new Rat()); // 在头部添加
        print("After addFirst(): " + pets);
        pets.offer(Pets.randomPet()); // 在尾部添加
        print("After offer(): " + pets);

        pets.add(Pets.randomPet()); // 在尾部添加
        print("After add(): " + pets);

        pets.addLast(new Hamster()); // 在尾部添加
        print("After addLast(): " + pets);

        print("pets.removeLast(): " + pets.removeLast()); // 移除并返回最后一个元素
    }
}


