package org.lgc.tij.typeinfo;

/**
 * 使用泛型语法的Class对象，newInstance()方法将返回该对象的确切类型，而不是Object
 * Created by laigc on 2017/1/1.
 */
public class GenericToyTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<FancyToy> ftClass = FancyToy.class;
        // 返回的是FancyToy，而不是Object
        FancyToy fancyToy = ftClass.newInstance();
        Class<? super FancyToy> up = ftClass.getSuperclass();
//        Class<Toy> up2 = ftClass.getSuperclass(); // 虽然FancyToy的父类是Toy，但是编译会出错
        Object object = up.newInstance(); // 这里返回的是Object，因为up是不确定的类型
    }
}
