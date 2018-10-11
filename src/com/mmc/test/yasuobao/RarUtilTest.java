package com.mmc.test.yasuobao;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
 
import org.apache.commons.io.IOUtils;
 
import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;
 
/**
 * RAR��ʽѹ���ļ���ѹ������
 * ��֧��RAR��ʽѹ��
 * ֧�����ĵ�RARѹ���ļ�����
 */
public class RarUtilTest {
	public static final String SEPARATOR = File.separator;
	
	// =============================== RAR Format ================================
	/**
	 * ��ѹָ��RAR�ļ�����ǰ�ļ���
	 * 
	 * @param srcRar ָ����ѹ
	 * @param password ѹ���ļ�ʱ�趨������
	 * @throws IOException 
	 */
	public static void unrar(String srcRar, String password) throws IOException {
		unrar(srcRar, null, password);
	}
	
	/**
	 * ��ѹָ����RARѹ���ļ���ָ����Ŀ¼��
	 * 
	 * @param srcRar ָ����RARѹ���ļ�
	 * @param destPath ָ����ѹ����Ŀ¼
	 * @param password ѹ���ļ�ʱ�趨������
	 * @throws IOException 
	 */
	public static void unrar(String srcRar, String destPath, String password) throws IOException {
		File srcFile = new File(srcRar);
		if (!srcFile.exists()) {
			return;
		}
		if (null == destPath || destPath.length() == 0) {
			unrar(srcFile, srcFile.getParent(), password);
			return;
		}
		unrar(srcFile,destPath, password);
	}
	
	/**
	 * ��ѹָ��RAR�ļ�����ǰ�ļ���
	 * 
	 * @param srcRarFile ��ѹ�ļ�
	 * @param password ѹ���ļ�ʱ�趨������
	 * @throws IOException 
	 */
	public static void unrar(File srcRarFile, String password) throws IOException {
		if (null == srcRarFile || !srcRarFile.exists()) {
			throw new IOException("ָ���ļ�������.");
		}
		unrar(srcRarFile, srcRarFile.getParent(),password);
	}
	
	/**
	 * ��ѹָ��RAR�ļ���ָ����·��
	 * 
	 * @param srcRarFile ��Ҫ��ѹRAR�ļ�
	 * @param destPath ָ����ѹ·��
	 * @param password ѹ���ļ�ʱ�趨������
	 * @throws IOException 
	 */
	public static void unrar(File srcRarFile, String destPath, String password) throws IOException {
		if (null == srcRarFile || !srcRarFile.exists()) {
			throw new IOException("ָ��ѹ���ļ�������.");
		}
		if (!destPath.endsWith(SEPARATOR)) {
			destPath += SEPARATOR;
		}
		Archive archive = null;
		OutputStream unOut = null;
		try {
			archive = new Archive(srcRarFile);
			FileHeader fileHeader = archive.nextFileHeader();
			while(null != fileHeader) {
//				if (!fileHeader.isDirectory()) {
//					// 1 ���ݲ�ͬ�Ĳ���ϵͳ�õ���Ӧ�� destDirName �� destFileName
//					String destFileName = "";
//					String destDirName = "";
//					if (SEPARATOR.equals("/")) {		// ��windowsϵͳ
//						destFileName = (destPath + fileHeader.getFileNameW()).replaceAll("\\\\", "/");
//						destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
//					} else {		// windowsϵͳ
//						destFileName = (destPath + fileHeader.getFileNameW()).replaceAll("/", "\\\\");
//						destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
//					}
//					// 2�����ļ���
//					File dir = new File(destDirName);
//					if (!dir.exists() || !dir.isDirectory()) {
//						dir.mkdirs();
//					}
//					// ��ȡѹ���ļ�
//					unOut = new FileOutputStream(new File(destFileName));
//					archive.extractFile(fileHeader, unOut);
//					unOut.flush();
//					unOut.close();
//				}
				if (!fileHeader.isDirectory()) {
					File destFile = new File(destPath, fileHeader.getFileNameString());
					File destDir = destFile.getParentFile();
					if (!destDir.exists()) {
						destDir.mkdirs();
					}
					// ��ȡѹ���ļ�
					unOut = new FileOutputStream(destFile);
					archive.extractFile(fileHeader, unOut);
					unOut.flush();
					unOut.close();
				}
				fileHeader = archive.nextFileHeader();
			}
			archive.close();
		} catch (RarException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(unOut);
		}
	}
	
	public static void main(String[] args) {
		try {
			unrar("d:\\test\\11\\cc.rar", "123");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
