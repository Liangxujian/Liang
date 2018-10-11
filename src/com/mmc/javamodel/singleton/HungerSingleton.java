package com.mmc.javamodel.singleton;

public class HungerSingleton {
	private static HungerSingleton singleton = new HungerSingleton();
	
	public static HungerSingleton getInstance(){
		return singleton;
	}
	
	private HungerSingleton() {
		// TODO Auto-generated constructor stub
	}
}
