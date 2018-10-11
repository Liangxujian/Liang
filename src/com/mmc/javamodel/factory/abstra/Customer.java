package com.mmc.javamodel.factory.abstra;

public class Customer {
	public static void main(String[] args) {
		IFactory factory = new Factory();
		factory.createHWPhones().show();
		factory.createXMPhones().show();
	}
}
