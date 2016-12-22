package org.lgc.tij.holding;

import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;

import java.util.AbstractCollection;
import java.util.Iterator;

/**当我们要创建一个Collection的实现时，如果通过实现Collection接口来实现，那么我们必须要实现Collection的所有方法。
 * 这时我们可通过继承AbstractCollection来实现
 * Created by lgc on 16-12-22.
 */
public class CollectionSequence extends AbstractCollection<Pet> {
    private Pet[] pets = Pets.createArray(8);
    @Override
    public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
            private int index = 0;
            public void remove() {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext() {
                return index < pets.length;
            }

            public Pet next() {
                return pets[index++];
            }
        };
    }

    @Override
    public int size() {
        return pets.length;
    }

    public static void main(String[] args) {
        CollectionSequence c = new CollectionSequence();
        InterfaceVsIterator.display(c);
        InterfaceVsIterator.display(c.iterator());

    }
}
