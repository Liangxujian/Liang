package com.mmc.javamodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 * 在程序运行时，运用反射机制动态创建而成
 */
public class DuckDynamicProxy implements InvocationHandler {
	Duck duck = null;
	
	public DuckDynamicProxy(Duck duck) {
		// TODO Auto-generated constructor stub
		this.duck = duck;
	}
	
	/**
	 * @param proxy 指代我们所代理的那个真实对象（Duck.class.getClassLoader()）
	 * @param method 指代我们所要调用真实对象的某个方法的Method对象（new Class[] { Duck.class }）
	 * @param objs 指代调用真实对象某个方法时接受的参数（new DuckDynamicProxy(new BeijingDuck())）
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] objs)
			throws Throwable {
		// TODO Auto-generated method stub
		Object obj = null;
		if ("fly".equals(method.getName())) {
			System.out.println("养肥");
			obj = method.invoke(duck, objs);
			System.out.println("卖掉");
		} else if ("taste".equals(method.getName())) {
			System.out.println("开玩笑！");
			obj = method.invoke(duck, objs);
			System.out.println("真的假的？");
		}
		return obj;
	}
}
