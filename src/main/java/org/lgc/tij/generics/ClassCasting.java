package org.lgc.tij.generics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 通过泛型来转型
 * Created by lgc on 17-1-10.
 */
public class ClassCasting {
    public void f(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]));
        List<Widget> lw1 = (List<Widget>) ois.readObject(); // 一般的转型
//        List<Widget> lw2 = List<Widget>.class.cast(ois.readObject());
        List<Widget> lw3 = List.class.cast(ois.readObject());// 泛型转型

    }
}
