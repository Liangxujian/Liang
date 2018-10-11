package com.mmc.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import com.mmc.test.util.ExcelUtil;

/**
 * 读取EXCEL数据，并写入到TXT文件中，对齐显示
 */
public class Test10 {
	public StringBuffer getDataStr(List<Map<String, String>> data){
		StringBuffer sb = new StringBuffer();
		// 遍历，获取每一列的最大长度，放到list集合中
		List<Integer> columnMaxLen = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			if(i == 0){
				for(String key : data.get(i).keySet()){
					int strLen = 0;
					for (int j = 0; j < key.length(); j++) {
						if(isChinese(key.charAt(j))){
		                    strLen = strLen + 2;
		                }else{
		                    strLen = strLen + 1;
		                }
					}
					columnMaxLen.add(strLen);
				}
			}
			int ii = 0;
			for (String value : data.get(i).values()) {
				// 计算每个单元格值的长度
				int strLen = 0;
				for (int j = 0; j < value.length(); j++) {
					if(isChinese(value.charAt(j))){
	                    strLen = strLen + 2;
	                }else{
	                    strLen = strLen + 1;
	                }
				}
				if(strLen > columnMaxLen.get(ii)) {
					columnMaxLen.set(ii, strLen);
				}
				ii++;
			}
		}
		// 遍历，根据最大长度，循环计算需要填充的空格，同时写出
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com = fsv.getHomeDirectory(); // 这便是读取桌面路径的方法了
		String path = com.getAbsolutePath() + "\\output.txt";
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(file));
			for (int a = 0; a < data.size(); a++) {
				if(a == 0){
					int bb = 0;
					for(String key : data.get(a).keySet()){
						int strLen = 0;
						for (int j = 0; j < key.length(); j++) {
							if(isChinese(key.charAt(j))){
			                    strLen = strLen + 2;
			                }else{
			                    strLen = strLen + 1;
			                }
						}
						int remain = columnMaxLen.get(bb) - strLen + 5;
						for (int i = 0; i < remain; i++) {
							key = key + " ";
			            }
						sb.append(key);
//						System.out.printf(key+"\t");
//						bw.write(key);
						bb++;
					}
//					System.out.printf("\r\n");
//					bw.write("\r\n");
					bw.flush();
				}
				int aa = 0;
				for (String value : data.get(a).values()) {
					int strLen = 0;
					for (int j = 0; j < value.length(); j++) {
						if(isChinese(value.charAt(j))){
		                    strLen = strLen + 2;
		                }else{
		                    strLen = strLen + 1;
		                }
					}
					int remain = columnMaxLen.get(aa) - strLen + 5;// 计算所需补充空格长度
					for (int i = 0; i < remain; i++) {
		                value = value + " ";
		            }
					sb.append(value);
//					System.out.printf(value+"\t");
//					bw.write(value);
					aa++;
				}
//				System.out.printf("\r\n");
//				bw.write("\r\n");
				bw.flush();
			}
			System.out.println(sb.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(bw != null){
					bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		Test10 test = new Test10();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com = fsv.getHomeDirectory(); // 这便是读取桌面路径的方法了
		File file = new File(com.getAbsolutePath()+"\\BOM对比20180611203609.xlsx");
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		if(file.exists()){
			data = ExcelUtil.getInstance().getDataFromTemplate(file, 1, 1);
		}
		test.getDataStr(data);
	}
}
