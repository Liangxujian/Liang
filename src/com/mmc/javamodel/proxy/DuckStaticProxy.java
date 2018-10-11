package com.mmc.javamodel.proxy;

/**
 * 静态代理类
 * 静态代理方便好理解，拓展了功能，而且不入侵源代码
 * 但是当接口存在多个实现类时，需要一一重写
 */
public class DuckStaticProxy implements Duck {
	Duck duck = null;
	
	public DuckStaticProxy(Duck duck) {
		// TODO Auto-generated constructor stub
		this.duck = duck;
	}
	
	@Override
	public void fly(int heigh) {
		// TODO Auto-generated method stub
		System.out.println("脱毛洗净");
		duck.fly(heigh);
		System.out.println("煮熟吃掉");
	}

	@Override
	public void taste(String taste) {
		// TODO Auto-generated method stub
		System.out.println("煮熟");
		duck.taste(taste);
		System.out.println("吃掉");
	}
}
