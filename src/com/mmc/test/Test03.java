package com.mmc.test;

/**
 * ¿É²Ù×÷²âÊÔÀà
 */
public class Test03 {
	public static void main(String[] args) {
		String str = "1.1";
		if(isNumeric(str)){
			int i = Integer.parseInt(str);
			System.out.println(i);
		}
	}
	
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
