package org.lgc.tij.holding;

import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;

import java.util.*;

/**Collection是一个接口
 * Created by lgc on 16-12-22.
 */
public class InterfaceVsIterator {
    public static void display(Iterator<Pet> iterator) {
        while (iterator.hasNext()) {
            Pet pet = iterator.next();
            System.out.print(pet.id() + ":" + pet + " ");
        }
        System.out.println();
    }

    public static void display(Collection<Pet> pets) {
        for (Pet pet : pets) {
            System.out.print(pet.id() + ":" + pet + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Pet> petList = Pets.arrayList(8);
        Set<Pet> petSet = new HashSet<Pet>(petList);
        Map<String, Pet> petMap = new LinkedHashMap<String, Pet>();
        String[] names = "Ralph, Eric, Robin, Lacey, Britney, Sam, Spot, Fluffy".split(", ");
        for (int i = 0; i < names.length; i++) {
            petMap.put(names[i], petList.get(i));
        }
        display(petList);
        display(petSet);
        display(petList.iterator());
        display(petSet.iterator());
        System.out.println(petMap);
        System.out.println(petMap.keySet());
        display(petMap.values());
        display(petMap.values().iterator());
    }
}
