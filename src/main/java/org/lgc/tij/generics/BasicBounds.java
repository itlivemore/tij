package org.lgc.tij.generics;

import java.awt.*;

/**
 * 泛型的边界
 * Created by laigc on 2017/1/7.
 */
interface HasColor {
    Color getColor();
}

class Colored<T extends HasColor> {
    T item;

    public Colored(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    Color color() {
        return item.getColor(); // 泛型边界允许调用方法
    }
}

class Dimension {
    public int x, y, z;
}

// 类写在前面，接口写在后面
//class ColoredDimension<T extends HasColor & Dimension> {
class ColoredDimension<T extends Dimension & HasColor> {
    T item;

    public ColoredDimension(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    Color color() {
        return item.getColor();
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

interface Weight {
    int weight();
}

class Solid<T extends Dimension & HasColor & Weight> {
    T item;

    public Solid(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    Color color() {
        return item.getColor();
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

    int weight() {
        return item.weight();
    }
}

class Bounded extends Dimension implements HasColor, Weight {

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }
}

public class BasicBounds {
    public static void main(String[] args) {
        Solid<Bounded> boundedSolid = new Solid<>(new Bounded());

        boundedSolid.color();
        boundedSolid.getY();
        boundedSolid.weight();
    }
}
