//: typeinfo/pets/ForNameCreator.java
package org.lgc.tij.typeinfo.pets;
import java.util.*;

public class ForNameCreator extends PetCreator {
  private static List<Class<? extends Pet>> types =
    new ArrayList<Class<? extends Pet>>();
  // Types that you want to be randomly created:
  private static String[] typeNames = {
    "org.lgc.tij.typeinfo.pets.Mutt",
    "org.lgc.tij.typeinfo.pets.Pug",
    "org.lgc.tij.typeinfo.pets.EgyptianMau",
    "org.lgc.tij.typeinfo.pets.Manx",
    "org.lgc.tij.typeinfo.pets.Cymric",
    "org.lgc.tij.typeinfo.pets.Rat",
    "org.lgc.tij.typeinfo.pets.Mouse",
    "org.lgc.tij.typeinfo.pets.Hamster"
  };	
  @SuppressWarnings("unchecked")
  private static void loader() {
    try {
      for(String name : typeNames)
        types.add(
          (Class<? extends Pet>)Class.forName(name));
    } catch(ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  static { loader(); }
  public List<Class<? extends Pet>> types() {return types;}
} ///:~
