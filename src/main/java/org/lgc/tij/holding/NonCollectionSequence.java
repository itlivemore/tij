package org.lgc.tij.holding;

import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;

import java.util.Iterator;

/**
 * 如果一个类已经继承了其他的类，就不能继承AbstractCollection了。在这种情况下实现Collection接口就要实现接口中所有方法。
 * 使用迭代器来实现就比较容易了
 * Created by laigc on 2016/12/24.
 */
class PetSequence {
    protected Pet[] pets = Pets.createArray(8);
}

public class NonCollectionSequence extends PetSequence{
   public Iterator<Pet> iterator() {
       return new Iterator<Pet>() {
           private int index = 0;
           public boolean hasNext() {
               return index < pets.length;
           }

           public Pet next() {
               return pets[index++];
           }

           public void remove() {
               throw new UnsupportedOperationException();
           }
       };
   }

    public static void main(String[] args) {
        NonCollectionSequence nc = new NonCollectionSequence();

    }
}
