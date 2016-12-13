package org.lgc.tij.holding;

import org.lgc.tij.typeinfo.pets.Hamster;
import org.lgc.tij.typeinfo.pets.Pet;
import org.lgc.tij.typeinfo.pets.Pets;

import java.util.ArrayList;
import java.util.Random;
import static net.mindview.util.Print.*;
/**
 * Created by lgc on 16-12-13.
 */
public class ListFeatures {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Pet> pets = Pets.arrayList(7);
        print("1:" + pets);
        Hamster h = new Hamster();
        pets.add(h);
        print("2:" + pets);
    }
}
