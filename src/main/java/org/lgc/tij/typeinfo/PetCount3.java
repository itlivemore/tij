package org.lgc.tij.typeinfo;

import net.mindview.util.MapData;
import org.lgc.tij.typeinfo.pets.LiteralPetCreator;
import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Pet计数
 * Created by laigc on 2017/1/1.
 */
public class PetCount3 {
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
        public PetCounter() {
            // 把所有的类型的计数初始化为0
            super(MapData.map(LiteralPetCreator.allTypes, 0));
        }

        public void count(Pet pet) {
            for (Map.Entry<Class<? extends Pet>, Integer> entry : entrySet()) {
                Class<? extends Pet> key = entry.getKey();
                if (key.isInstance(pet)) { // 判断是否是某个类型
                    put(key, entry.getValue() + 1);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (Map.Entry<Class<? extends Pet>, Integer> entry : entrySet()) {
                sb.append(entry.getKey().getSimpleName());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // 删除逗号及后面的空格
            sb.append("}");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        PetCounter petCount = new PetCounter();
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
            petCount.count(pet);
        }
        System.out.println();
        System.out.println(petCount);
    }
}
