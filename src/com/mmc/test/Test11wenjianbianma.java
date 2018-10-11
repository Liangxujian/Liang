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
 * 本方法通过cpdetector库 提供的监听方法，来判断文本文件字符编码
 */
public class Test11wenjianbianma {
	public static void main(String[] args) {
//		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//		detector.add(JChardetFacade.getInstance());
//		Charset charset = null;
//		File f = new File("C:\\Users\\Administrator\\Desktop\\假装是个合同.xlsx");
//		try {
//			charset = detector.detectCodepage(f.toURL());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (charset != null) {
//			System.out.println(f.getName() + "编码是：" + charset.name());
//		} else
//			System.out.println(f.getName() + "未知");
		try {
			System.out.println(Test11wenjianbianma.getFileCharset("D:\\11\\hello\\cc.zip"));
			System.out.println(Test11wenjianbianma.getFileCharset("D:\\11\\test\\的故事.zip"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFileCharset(String filePath) throws Exception {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/*ParsingDetector可用于检查HTML、XML等文件或字符流的编码,
		 * 构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。
	    */
//		detector.add(new ParsingDetector(false));
		/*JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码测定。
		 * 所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器，
		 * 比如下面的ASCIIDetector、UnicodeDetector等。
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
				charsetName = charset.name();// 例如:UTF-8,UTF-16BE.
			}
		}
		return charsetName;
	}
}
