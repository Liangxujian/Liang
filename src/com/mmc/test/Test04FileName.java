package com.mmc.test;

import java.io.File;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串截取案例
 */
public class Test04FileName {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\Administrator\\Desktop\\88290004-355_00电机型式试验报告.pdf");
		String fileName = file.getName();
		// 获取文件名
		String realName = fileName.substring(0, fileName.lastIndexOf("."));
		System.out.println("fileName:"+ realName);
		// 截取从字符串的0号位开始到第7位
		String fileName2 = fileName.substring(0, 7);
		System.out.println("fileName2:" + fileName2);
		// 截取后缀
		String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		System.out.println("suffix:" + suffix);
		
		String[] strs = {"123", "456", "789"};
		System.out.println(StringUtils.join(strs, "-"));
	}
}
