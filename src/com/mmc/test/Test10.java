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
 * ��ȡEXCEL���ݣ���д�뵽TXT�ļ��У�������ʾ
 */
public class Test10 {
	public StringBuffer getDataStr(List<Map<String, String>> data){
		StringBuffer sb = new StringBuffer();
		// ��������ȡÿһ�е���󳤶ȣ��ŵ�list������
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
				// ����ÿ����Ԫ��ֵ�ĳ���
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
		// ������������󳤶ȣ�ѭ��������Ҫ���Ŀո�ͬʱд��
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com = fsv.getHomeDirectory(); // ����Ƕ�ȡ����·���ķ�����
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
					int remain = columnMaxLen.get(aa) - strLen + 5;// �������貹��ո񳤶�
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
	
	// ����Unicode�����������ж����ĺ��ֺͷ���
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
		File com = fsv.getHomeDirectory(); // ����Ƕ�ȡ����·���ķ�����
		File file = new File(com.getAbsolutePath()+"\\BOM�Ա�20180611203609.xlsx");
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		if(file.exists()){
			data = ExcelUtil.getInstance().getDataFromTemplate(file, 1, 1);
		}
		test.getDataStr(data);
	}
}
