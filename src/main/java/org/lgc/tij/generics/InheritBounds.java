package org.lgc.tij.generics;

import java.awt.*;

/**
 * 在继承的每个层次上添加边界限制
 * 方法被继承，因而不必在每个类中重复
 * Created by laigc on 2017/1/7.
 */
class HoldItem<T> {
    T item;

    public HoldItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}

class Colored2<T extends HasColor> extends HoldItem<T> {

    public Colored2(T item) {
        super(item);
    }

    Color color() {
        return item.getColor();
    }
}

class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T> {
    // 这个类继承了Colored，所以有color()方法，所以这里不用重复写
    public ColoredDimension2(T item) {
        super(item);
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }
}

class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T> {

    public Solid2(T item) {
        super(item);
    }

    int weight() {
        return item.weight();
    }
}

public class InheritBounds {
    public static void main(String[] args) {
        Solid2<Bounded> solid2 = new Solid2<>(new Bounded());
        solid2.color();
        solid2.weight();
        solid2.getY();
    }
}
