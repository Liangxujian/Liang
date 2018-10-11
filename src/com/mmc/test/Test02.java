package com.mmc.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 字符串自定义规则排序
 */
public class Test02 {
	public static void main(String[] args) {
		String str1 = "32.1.1.1.2.1";
		String str2 = "32.1.1.1.2.3";
		String str3 = "32.1.1.1.2";
		String str4 = "32.1.1.1.1.3";
		String str5 = "32.1.1.1.2.2";
		String str6 = "32.1.24.4.1";
		String str7 = "32.1.10.1";
		String str8 = "32.11";
		
		List<String> list = new ArrayList<String>();
		list.add(str1);
		list.add(str2);
		list.add(str3);
		list.add(str4);
		list.add(str5);
		list.add(str6);
		list.add(str7);
		list.add(str8);
		
		System.out.println(list);
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String str_1, String str_2) {
//				 TODO Auto-generated method stub
				int result = str_1.compareTo(str_2);
				System.out.println(str_1 + " compare to:"+ str_2 + ",result is:" + result);
				return result;
			}
		});
		System.out.println(list);
	}
}
