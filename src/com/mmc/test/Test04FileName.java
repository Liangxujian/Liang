package com.mmc.test;

import java.io.File;

import org.apache.commons.lang.StringUtils;

/**
 * �ַ�����ȡ����
 */
public class Test04FileName {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\Administrator\\Desktop\\88290004-355_00�����ʽ���鱨��.pdf");
		String fileName = file.getName();
		// ��ȡ�ļ���
		String realName = fileName.substring(0, fileName.lastIndexOf("."));
		System.out.println("fileName:"+ realName);
		// ��ȡ���ַ�����0��λ��ʼ����7λ
		String fileName2 = fileName.substring(0, 7);
		System.out.println("fileName2:" + fileName2);
		// ��ȡ��׺
		String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		System.out.println("suffix:" + suffix);
		
		String[] strs = {"123", "456", "789"};
		System.out.println(StringUtils.join(strs, "-"));
	}
}
