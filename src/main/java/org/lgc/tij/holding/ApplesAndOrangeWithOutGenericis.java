package org.lgc.tij.holding;

import java.util.ArrayList;

/**
 * @Title ApplesAndOrangeWithOutGenericis.java
 * @Package: org.lgc.tij
 * @Description: 不使用泛型，java编译器会报警告信息。使用注解@SuppressWarnings及其参数表示只有有关“不受检查的异常”的警告信息应该被抑制
 *
 * @Author: lgc
 * @Date: 2016年12月7日 上午8:13:43
 *
 *        Copyright @ 2016 Corpration Name
 * 
 */
class Apple {
	private static long counter;
	private final long id = counter++;

	public long id() {
		return id;
	}
}

class Orange {

}

public class ApplesAndOrangeWithOutGenericis {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList apples = new ArrayList();
		apples.add(new Apple());
		for (int i = 0; i < 3; i++) {
			apples.add(new Apple());
		}
		apples.add(new Orange()); // 可以存放Orange类型对象，编译和运行都不会有问题
		for (int i = 0; i < apples.size(); i++) {
			((Apple) apples.get(i)).id(); // 需要转型，因为存放了Orange对象，不是Applec对象,运行时会报错
		}
	}
}
