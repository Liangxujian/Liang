package com.mmc.javamodel.proxy;

public class TestStaticProxy {
	public static void main(String[] args) {
		DuckStaticProxy proxy = new DuckStaticProxy(new BeijingDuck());
		proxy.fly(9);
		proxy.taste("Ïã´à¿É¿Ú");
	}
}
