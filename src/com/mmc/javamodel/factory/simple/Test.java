package com.mmc.javamodel.factory.simple;

/**
 * 简单工厂模式测试
 */
public class Test {
	public static void main(String[] args) {
		 INoodles noodles = SimpleNoodlesFactory.createNoodles(SimpleNoodlesFactory.TYPE_GK);
		 noodles.desc();
	}
}
