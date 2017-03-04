package org.lgc.tij.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 恢复CADState
 * Created by laigc on 2017/3/4.
 */
public class RecoverCADState {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Recovering");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
        List<Class<? extends Shape>> shapeTypes2 = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes2 = (List<Shape>) in.readObject();
        System.out.println(shapes2);
    }
}
