package com.mmc.test;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串浓缩匹配写法
 */
public class Test01 {
	public static void main(String[] args) {
		System.out.println(domain("万历"));
		System.out.println(domain("道光"));
		System.out.println(domain("乾隆"));
		System.out.println(domain("北京"));
		System.out.println(domain("唐太宗"));
		System.out.println(domain("雍正"));

		String Str = new String("www.runoob.com");

		System.out.print("返回值 :");
		System.out.println(Str.matches("(.*)runoob(.*)"));

		System.out.print("返回值 :");
		System.out.println(Str.matches("(.*)google(.*)"));

		System.out.print("返回值 :);
		System.out.println(Str.matches("www(.*)"));
		
		System.out.print("返回值 :);
		System.out.println(Str.matches("(.*)cn"));
	}
	
	private static boolean domain(String type){
		if (type.matches("努尔哈赤|皇太极|顺治|康熙|雍正|乾隆|嘉庆|道光|咸丰|同治|光绪|宣统")) {
			return true;
		}
		return false;
	}
	
	public void ListContains() {
		Set<String> set = new HashSet<String>();
		
//		List<String> list = new ArrayList<String>();
		set.add("陆萧然");
		set.add("陆飞");
		set.add("汤加丽");
		set.add("汤加饭");
		set.add("汤加菜");
		set.add("汤加粒");
		set.add("梁非凡");
		set.add("梁飞凡");
		set.add("刘醒");
		set.add("采购部");
		set.add("外购部");
		for(String str : set){
			if(str.contains("部")){
				System.out.println("存在:" + str);
			}
		}
	}
}