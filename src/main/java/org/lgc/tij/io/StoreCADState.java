package org.lgc.tij.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * static字段序列化
 * 注意各个类处理color的初始化
 * Created by laigc on 2017/3/4.
 */
abstract class Shape implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int newColor);

    public abstract int getColor();

    public Shape(int xVal, int yVal, int dim) {
        xPos = xVal;
        yPos = yVal;
        dimension = dim;
    }

    public String toString() {
        return getClass() +
                "color[" + getColor() + "] xPos[" + xPos +
                "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }

    public static Shape randomFactory() {
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch (counter++ % 3) {
            default:
            case 0:
                return new Circle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);
        }
    }
}

class Circle extends Shape {
    private static int color = RED;

    public Circle(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }
}

class Square extends Shape {
    private static int color;

    public Square(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
        color = RED;
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }
}

class Line extends Shape {
    private static int color = RED;

    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException {
        os.writeInt(color);
    }

    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException {
        color = os.readInt();
    }

    public Line(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }
}

public class StoreCADState {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Class<? extends Shape>> shapeTypes = new ArrayList<>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);


        List<Shape> shapes = new ArrayList<>();
        // 添加shape
        for (int i = 0; i < 10; i++) {
            shapes.add(Shape.randomFactory());
        }

        // 将所有color设置为GREEN
        for (int i = 0; i < 10; i++) {
            ((Shape) shapes.get(i)).setColor(Shape.GREEN);
        }

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CADState.out"));
        out.writeObject(shapeTypes);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
        System.out.println(shapes);

        // 下面的代码和类RecoverCADState中的代码一样，但是执行结果却不相同
        // 因为static字段默认不会被序列化，static字段是类的属性，类一加载，static字段就加载了。
        // 在这里直接恢复，不用重新加载类，保存时已经修改了类的color，所以color结果是一致。
        // 但是RecoverCADState是重新加载了类，static重新初始化了
        System.out.println("Recovering");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
        List<Class<? extends Shape>> shapeTypes2 = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes2 = (List<Shape>) in.readObject();
        System.out.println(shapes2);
    }
}
