package com.mmc.javamodel.proxy;

import java.lang.reflect.Proxy;

public class TestDynamicProxy {
	public static void main(String[] args) {
		Duck duck = (Duck) Proxy.newProxyInstance(Duck.class.getClassLoader(),
				new Class[] { Duck.class }, new DuckDynamicProxy(
						new BeijingDuck()));
		duck.fly(99);
		duck.taste("∏¬‡‘¥‡£¨º¶»‚Œ∂");
	}
}
