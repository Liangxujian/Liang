package com.mmc.javamodel.factory.simple;

/**
 * �򵥹���ģʽ����
 */
public class Test {
	public static void main(String[] args) {
		 INoodles noodles = SimpleNoodlesFactory.createNoodles(SimpleNoodlesFactory.TYPE_GK);
		 noodles.desc();
	}
}
