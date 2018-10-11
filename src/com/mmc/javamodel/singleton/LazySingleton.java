package com.mmc.javamodel.singleton;

public class LazySingleton {
	private static LazySingleton singleton = null;
	private static Object obj = new Object();
	
	public static LazySingleton getInstance() {
		if (singleton == null) {
			synchronized (obj) {
				if (singleton == null) {
					singleton = new LazySingleton();
				}
			}
		}
		return singleton;
	}
	
	private LazySingleton() {
		// TODO Auto-generated constructor stub
	}
}
