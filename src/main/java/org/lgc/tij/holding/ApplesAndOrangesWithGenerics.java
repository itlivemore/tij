/**  
 * Project Name:tij  
 * File Name:ApplesAndOrangesWithGenerics.java  
 * Package Name:org.lgc.tij.holding  
 * Date:2016年12月7日下午10:28:57  
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.  
 *  
*/

package org.lgc.tij.holding;

import java.util.ArrayList;

/**
 * ClassName:ApplesAndOrangesWithGenerics <br/>
 * Date: 2016年12月7日 下午10:28:57 <br/>
 * 
 * @Description: 使用泛型，可以在编译期防止将错误类型的对象添加到容器中 <br/>
 * 使用泛型时，从容器中取出对象也不需要转型
 * @author lgc
 * @version
 * @since JDK 1.6
 * @see
 */
public class ApplesAndOrangesWithGenerics {
	public static void main(String[] args) {
		ArrayList<Apple> apples = new ArrayList<Apple>();
		for (int i = 0; i < 3; i++) {
			apples.add(new Apple());
		}

		// apples.add(new Orange());//编译时错误
		for (int i = 0; i < apples.size(); i++) {
			System.out.println(apples.get(i).id());
		}
		for (Apple apple : apples) {
			System.out.println(apple.id());
		}
	}
}
