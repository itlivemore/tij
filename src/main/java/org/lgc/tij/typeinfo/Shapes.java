package org.lgc.tij.typeinfo;

import java.util.Arrays;
import java.util.List;

/**
 * 向上转型
 * Created by laigc on 2017/1/1.
 */
abstract class Shape {
    void draw() {
        // 打印this会调用toString()方法
        System.out.println(this + ".draw()");
    }

    @Override
    abstract public String toString();
}

class Circle extends Shape {
    @Override
    public String toString() {
        return "Circle";
    }
}

class Square extends Shape {

    @Override
    public String toString() {
        return "Square";
    }
}

class Triangle extends Shape {

    @Override
    public String toString() {
        return "Triangle";
    }
}

public class Shapes {
    public static void main(String[] args) {
        // 创建List存放Shape对象，只能存放Shape对象。在编译时，将由容器和Java的泛型系统来强制确保这一点，而在运行时，由类型转换操作来确保这一点
        List<Shape> shapeList = Arrays.asList(new Circle(), new Square(), new Triangle());
        for (Shape shape : shapeList) {
            shape.draw();
        }
    }
}
