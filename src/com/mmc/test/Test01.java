package com.mmc.test;

import java.util.HashSet;
import java.util.Set;

/**
 * �ַ���Ũ��ƥ��д��
 */
public class Test01 {
	public static void main(String[] args) {
		System.out.println(domain("����"));
		System.out.println(domain("����"));
		System.out.println(domain("Ǭ¡"));
		System.out.println(domain("����"));
		System.out.println(domain("��̫��"));
		System.out.println(domain("Ӻ��"));

		String Str = new String("www.runoob.com");

		System.out.print("����ֵ :");
		System.out.println(Str.matches("(.*)runoob(.*)"));

		System.out.print("����ֵ :");
		System.out.println(Str.matches("(.*)google(.*)"));

		System.out.print("����ֵ :");
		System.out.println(Str.matches("www(.*)"));
		
		System.out.print("����ֵ :");
		System.out.println(Str.matches("(.*)cn"));
	}
	
	private static boolean domain(String type){
		if(type.matches("Ŭ������|��̫��|˳��|����|Ӻ��|Ǭ¡|����|����|�̷�|ͬ��|����|��ͳ")){
			return true;
		}
		return false;
	}
	
	public void ListContains() {
		Set<String> set = new HashSet<String>();
		
//		List<String> list = new ArrayList<String>();
		set.add("½��Ȼ");
		set.add("½��");
		set.add("������");
		set.add("���ӷ�");
		set.add("���Ӳ�");
		set.add("������");
		set.add("���Ƿ�");
		set.add("���ɷ�");
		set.add("����");
		set.add("�⹺��");
		set.add("�ɹ���");
		for(String str : set){
			if(str.contains("��")){
				System.out.println("����:" + str);
			}
		}
	}
}