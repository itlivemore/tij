package org.lgc.tij.generics;

import org.lgc.tij.holding.MultiIterableClass;

/**
 * 实现参数化接口
 * Created by laigc on 2017/1/8.
 */
interface Payable<T> {
}

class Employee2 implements Payable<Employee> {
}
// 擦除会将Payable<T>简化为相同的类Payable，这样下面一行代码就意味着在重复两次实现相同的接口
//public class MultipleInterfaceVariants extends Employee2 implements Payable<MultiIterableClass> {
public class MultipleInterfaceVariants implements Payable<MultiIterableClass> {
}
