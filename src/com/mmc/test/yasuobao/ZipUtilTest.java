package com.mmc.test.yasuobao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.commons.lang.StringUtils;

public class ZipUtilTest {
	private static class Temp {
		static ZipUtilTest instance = new ZipUtilTest();
	}
	
	public static ZipUtilTest getInstance(){
		return Temp.instance;
	}
	
	private ZipUtilTest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ʹ�ø��������ѹָ����ZIPѹ���ļ���ָ��Ŀ¼
	 * <p>
	 * ���ָ��Ŀ¼������,�����Զ�����,���Ϸ���·���������쳣���׳�
	 * 
	 * @param zip ָ����ZIPѹ���ļ�
	 * @param dest ��ѹĿ¼
	 * @param passwd ZIP�ļ�������
	 * @return ��ѹ���ļ�����
	 * @throws ZipException ѹ���ļ����𻵻��߽�ѹ��ʧ���׳�
	 */
	public static File[] unzip(String zip, String dest, String passwd) throws ZipException {
		File zipFile = new File(zip);
		return unzip(zipFile, dest, passwd);
	}

	/**
	 * ʹ�ø��������ѹָ����ZIPѹ���ļ�����ǰĿ¼
	 * 
	 * @param zip ָ����ZIPѹ���ļ�
	 * @param passwd ZIP�ļ�������
	 * @return ��ѹ���ļ�����
	 * @throws ZipException ѹ���ļ����𻵻��߽�ѹ��ʧ���׳�
	 */
	public static File[] unzip(String zip, String passwd) throws ZipException {
		File zipFile = new File(zip);
		File parentDir = zipFile.getParentFile();
		return unzip(zipFile, parentDir.getAbsolutePath(), passwd);
	}

	/**
	 * ʹ�ø��������ѹָ����ZIPѹ���ļ���ָ��Ŀ¼
	 * <p>
	 * ���ָ��Ŀ¼������,�����Զ�����,���Ϸ���·���������쳣���׳�
	 * 
	 * @param zip ָ����ZIPѹ���ļ�
	 * @param dest ��ѹĿ¼
	 * @param passwd ZIP�ļ�������
	 * @return ��ѹ���ļ�����
	 * @throws ZipException ѹ���ļ����𻵻��߽�ѹ��ʧ���׳�
	 */
	public static File[] unzip(File zipFile, String dest, String passwd) throws ZipException {
		ZipFile zFile = new ZipFile(zipFile);
		zFile.setFileNameCharset("UTF-8");
//		zFile.setFileNameCharset("GBK");
		if (!zFile.isValidZipFile()) {
			throw new ZipException("ѹ���ļ����Ϸ�,���ܱ���.");
		}
		File destDir = new File(dest);
		if (destDir.isDirectory() && !destDir.exists()) {
			destDir.mkdir();
		}
		if (zFile.isEncrypted()) {
			zFile.setPassword(passwd.toCharArray());
		}
		zFile.extractAll(dest);

		List<FileHeader> headerList = zFile.getFileHeaders();
		List<File> extractedFileList = new ArrayList<File>();
		for (FileHeader fileHeader : headerList) {
			if (!fileHeader.isDirectory()) {
				extractedFileList.add(new File(destDir, fileHeader.getFileName()));
			}
		}
		File[] extractedFiles = new File[extractedFileList.size()];
		extractedFileList.toArray(extractedFiles);
		return extractedFiles;
	}

	/**
	 * ѹ��ָ���ļ�����ǰ�ļ���
	 * 
	 * @param src Ҫѹ����ָ���ļ�
	 * @return ���յ�ѹ���ļ���ŵľ���·��,���Ϊnull��˵��ѹ��ʧ��.
	 */
	public static String zip(String src) {
		return zip(src, null);
	}

	/**
	 * ʹ�ø�������ѹ��ָ���ļ����ļ��е���ǰĿ¼
	 * 
	 * @param src Ҫѹ�����ļ�
	 * @param passwd ѹ��ʹ�õ�����
	 * @return ���յ�ѹ���ļ���ŵľ���·��,���Ϊnull��˵��ѹ��ʧ��.
	 */
	public static String zip(String src, String passwd) {
		return zip(src, null, passwd);
	}

	/**
	 * ʹ�ø�������ѹ��ָ���ļ����ļ��е���ǰĿ¼
	 * 
	 * @param src Ҫѹ�����ļ�
	 * @param dest ѹ���ļ����·��
	 * @param passwd ѹ��ʹ�õ�����
	 * @return ���յ�ѹ���ļ���ŵľ���·��,���Ϊnull��˵��ѹ��ʧ��.
	 */
	public static String zip(String src, String dest, String passwd) {
		return zip(src, dest, true, passwd);
	}

	/**
	 * ʹ�ø�������ѹ��ָ���ļ����ļ��е�ָ��λ��.
	 * <p>
	 * dest�ɴ�����ѹ���ļ���ŵľ���·��,Ҳ���Դ����Ŀ¼,Ҳ���Դ�null����"".<br />
	 * �����null����""��ѹ���ļ�����ڵ�ǰĿ¼,����Դ�ļ�ͬĿ¼,ѹ���ļ���ȡԴ�ļ���,��.zipΪ��׺;<br />
	 * �����·���ָ���(File.separator)��β,����ΪĿ¼,ѹ���ļ���ȡԴ�ļ���,��.zipΪ��׺,������Ϊ�ļ���.
	 * 
	 * @param src Ҫѹ�����ļ����ļ���·��
	 * @param dest ѹ���ļ����·��
	 * @param isCreateDir �Ƿ���ѹ���ļ��ﴴ��Ŀ¼,����ѹ���ļ�ΪĿ¼ʱ��Ч.<br />
	 *            			(���Ϊfalse,��ֱ��ѹ��Ŀ¼���ļ���ѹ���ļ�.)
	 * @param passwd ѹ��ʹ�õ�����
	 * @return ���յ�ѹ���ļ���ŵľ���·��,���Ϊnull��˵��ѹ��ʧ��.
	 */
	public static String zip(String src, String dest, boolean isCreateDir, String passwd) {
		File srcFile = new File(src);
		dest = buildDestinationZipFilePath(srcFile, dest);
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // ѹ����ʽ
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // ѹ������
		if (!StringUtils.isEmpty(passwd)) {
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // ���ܷ�ʽ
			parameters.setPassword(passwd.toCharArray());
		}
		try {
			ZipFile zipFile = new ZipFile(dest);
			if (srcFile.isDirectory()) {
				// ���������Ŀ¼�Ļ�,��ֱ�ӰѸ���Ŀ¼�µ��ļ�ѹ����ѹ���ļ�,��û��Ŀ¼�ṹ
				if (!isCreateDir) {
					File[] subFiles = srcFile.listFiles();
					ArrayList<File> temp = new ArrayList<File>();
					Collections.addAll(temp, subFiles);
					zipFile.addFiles(temp, parameters);
					return dest;
				}
				zipFile.addFolder(srcFile, parameters);
			} else {
				zipFile.addFile(srcFile, parameters);
			}
			return dest;
		} catch (ZipException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����ѹ���ļ����·��,��������ڽ��ᴴ�� ����Ŀ������ļ�������Ŀ¼,Ҳ���ܲ���,�˷�������ת������ѹ���ļ��Ĵ��·��
	 * 
	 * @param srcFile Դ�ļ�
	 * @param destParam ѹ��Ŀ��·��
	 * @return ��ȷ��ѹ���ļ����·��
	 */
	private static String buildDestinationZipFilePath(File srcFile, String destParam) {
		if (StringUtils.isEmpty(destParam)) {
			if (srcFile.isDirectory()) {
				destParam = srcFile.getParent() + File.separator + srcFile.getName() + ".zip";
			} else {
				String fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
				destParam = srcFile.getParent() + File.separator + fileName + ".zip";
			}
		} else {
			createDestDirectoryIfNecessary(destParam); // ��ָ��·�������ڵ�����½��䴴������
			if (destParam.endsWith(File.separator)) {
				String fileName = "";
				if (srcFile.isDirectory()) {
					fileName = srcFile.getName();
				} else {
					fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
				}
				destParam += fileName + ".zip";
			}
		}
		return destParam;
	}

	/**
	 * �ڱ�Ҫ������´���ѹ���ļ����Ŀ¼,����ָ���Ĵ��·����û�б�����
	 * 
	 * @param destParam ָ���Ĵ��·��,�п��ܸ�·����û�б�����
	 */
	private static void createDestDirectoryIfNecessary(String destParam) {
		File destDir = null;
		if (destParam.endsWith(File.separator)) {
			destDir = new File(destParam);
		} else {
			destDir = new File(destParam.substring(0, destParam.lastIndexOf(File.separator)));
		}
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
	}

	public static void main(String[] args) {
//		zip("d:\\test\\cc", "d:\\test\\cc.zip", "99");
		try {
			File[] files = unzip("d:\\test\\11\\cc.zip", "13");
			System.out.println(files.length);
			for (int i = 0; i < files.length; i++) {
				System.out.println(files[i]);
			}
		} catch (ZipException e) {
			System.out.println("�������");
			e.printStackTrace();
		}
	}
}