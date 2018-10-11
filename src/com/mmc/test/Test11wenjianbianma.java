package com.mmc.test;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * ������ͨ��cpdetector�� �ṩ�ļ������������ж��ı��ļ��ַ�����
 */
public class Test11wenjianbianma {
	public static void main(String[] args) {
//		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//		detector.add(JChardetFacade.getInstance());
//		Charset charset = null;
//		File f = new File("C:\\Users\\Administrator\\Desktop\\��װ�Ǹ���ͬ.xlsx");
//		try {
//			charset = detector.detectCodepage(f.toURL());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (charset != null) {
//			System.out.println(f.getName() + "�����ǣ�" + charset.name());
//		} else
//			System.out.println(f.getName() + "δ֪");
		try {
			System.out.println(Test11wenjianbianma.getFileCharset("D:\\11\\hello\\cc.zip"));
			System.out.println(Test11wenjianbianma.getFileCharset("D:\\11\\test\\�Ĺ���.zip"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFileCharset(String filePath) throws Exception {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/*ParsingDetector�����ڼ��HTML��XML���ļ����ַ����ı���,
		 * ���췽���еĲ�������ָʾ�Ƿ���ʾ̽����̵���ϸ��Ϣ��Ϊfalse����ʾ��
	    */
//		detector.add(new ParsingDetector(false));
		/*JChardetFacade��װ����Mozilla��֯�ṩ��JChardet����������ɴ�����ļ��ı���ⶨ��
		 * ���ԣ�һ���������̽�����Ϳ�����������Ŀ��Ҫ������㻹�����ģ������ٶ�Ӽ���̽������
		 * ���������ASCIIDetector��UnicodeDetector�ȡ�
        */
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		Charset charset = null;
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(filePath));
			charset = detector.detectCodepage(is, 8);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
 
		String charsetName = "GBK";
		if (charset != null) {
			if (charset.name().equals("US-ASCII")) {
				charsetName = "ISO_8859_1";
			} else if (charset.name().startsWith("UTF")) {
				charsetName = charset.name();// ����:UTF-8,UTF-16BE.
			}
		}
		return charsetName;
	}
}
