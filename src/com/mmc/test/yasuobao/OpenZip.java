package com.mmc.test.yasuobao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class OpenZip {
	public static void main(String[] args) {
		String zipFilePath = "D:\\uplo\\cirros-1114.zip";
		String fileSavePath = "D:\\uplo";
		extTarFileList(zipFilePath, fileSavePath );
	}

	public static  void extTarFileList(String zipFilePath, String fileSavePath) {
		boolean isUnZipSuccess = true;
		try {
			(new File(fileSavePath)).mkdirs();
			File f = new File(zipFilePath);
			if ((!f.exists()) && (f.length() <= 0)) {
				throw new RuntimeException("not find " + zipFilePath + "!");
			}
			// 涓�瀹氳鍔犱笂缂栫爜锛屼箣鍓嶈В鍘嬪彟澶栦竴涓枃浠讹紝娌℃湁鍔犱笂缂栫爜瀵艰嚧涓嶈兘瑙ｅ帇
			ZipFile zipFile = new ZipFile(f);
			String gbkPath, strtemp;
			Enumeration<ZipEntry> e = (Enumeration<ZipEntry>) zipFile.entries();
			while (e.hasMoreElements()) {
				ZipEntry zipEnt = e.nextElement();
				gbkPath = zipEnt.getName();
				strtemp = fileSavePath + File.separator + gbkPath;
				if (zipEnt.isDirectory()) { // 鐩綍
					File dir = new File(strtemp);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					continue;
				} else {
					// 璇诲啓鏂囦欢
					InputStream is = zipFile.getInputStream(zipEnt);
					BufferedInputStream bis = new BufferedInputStream(is);
					// 寤虹洰褰�
					String strsubdir = gbkPath;
					for (int i = 0; i < strsubdir.length(); i++) {
						if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
							String temp = fileSavePath + File.separator + strsubdir.substring(0, i);
							File subdir = new File(temp);
							if (!subdir.exists())
								subdir.mkdir();
						}
					}
					FileOutputStream fos = new FileOutputStream(strtemp);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					int len;
					byte[] buff = new byte[5120];
					while ((len = bis.read(buff)) != -1) {
						bos.write(buff, 0, len);
					}
					bos.close();
					fos.close();
				}
			}
			zipFile.close();
		} catch (Exception e) {
			// logger.error("瑙ｅ帇鏂囦欢鍑虹幇寮傚父锛�", e);
			isUnZipSuccess = false;
			System.out.println("extract file error: " + zipFilePath);
		}
	}
}
