/**  
 * Project Name:tij  
 * File Name:GenericsAndUpCasting.java  
 * Package Name:org.lgc.tij.holding  
 * Date:2016年12月8日下午9:52:35  
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.  
 *  
*/

package org.lgc.tij.holding;

import java.util.ArrayList;

class GrannySmith extends Apple {
}

class Gala extends Apple {
}

class Fuji extends Apple {
}

class Braeburn extends Apple {
}

/**
 * ClassName:GenericsAndUpCasting <br/>
 * Reason: 当指定了某个类型作为泛型参数时，并不仅限于只能将该确切类型的对象放置到容器中，向上转型也可以像作用于其他类型一样作用于泛型 <br/>
 * Date: 2016年12月8日 下午9:52:35 <br/>
 * 
 * @author lgc
 * @version
 * @since JDK 1.6
 * @see
 */
public class GenericsAndUpCasting {
	public static void main(String[] args) {
		ArrayList<Apple> apples = new ArrayList<Apple>();
		// 可以把Apple的子类型添加到指定为保存Apple对象的容器中
		apples.add(new GrannySmith());
		apples.add(new Gala());
		apples.add(new Fuji());
		apples.add(new Braeburn());
		for (Apple apple : apples) {
			System.out.println(apple); //这里打印apple调用的是Object默认的toString()方法，打印类名，后面跟随该对象的散列码的无符号的十六进制表示，这个散列码是通过hashCode()方法产生的
		}
	}
}
