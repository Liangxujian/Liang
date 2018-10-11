package com.mmc.test;

import java.awt.datatransfer.DataFlavor;

import javax.swing.JComponent;

public class ReportDataFlavor {
	/**
	 * swing�ؼ�
	 */
	public static DataFlavor componentFlavor = createConstant(JComponent.class, JComponent.class.getName());
	
	/**
	 * ���ض�������
	 */
	public static DataFlavor localObjectFlavor = createConstant(DataFlavor.javaJVMLocalObjectMimeType);

	/**
	 * �������͹���
	 * 
	 * @param rc
	 * @param prn
	 * @return
	 */
	static private DataFlavor createConstant(Class rc, String prn) {
		try {
			return new DataFlavor(rc, prn);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	static private DataFlavor createConstant(String mimeType) {
		try {
			return new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}
}