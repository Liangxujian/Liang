package com.mmc.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TxTUtil {
	private static class Temp {
		static TxTUtil instance = new TxTUtil();
	}
	
	public static TxTUtil getInstance() {
		return Temp.instance;
	}
	
    /**
     * 读取txt文件的内容 
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		if (file != null && file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
				String s = null;
				while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
					result.append(System.lineSeparator() + s);
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}
	
	public static List<String> txt2List(File file) {
		List<String> result = new ArrayList<>();
		if (file != null && file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
				String s = null;
				while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
					result.add(s);
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		File file = new File("D:/course.txt");
		System.out.println(txt2String(file));
	}
	
	private TxTUtil() {
		// TODO Auto-generated constructor stub
	}
}
