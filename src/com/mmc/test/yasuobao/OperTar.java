package com.mmc.test.yasuobao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.ice.tar.TarEntry;
import com.ice.tar.TarInputStream;

public class OperTar {
	public static void main(String[] args) {
		String filename = "D:\\uplo\\cirros-ca.tar";
		String directory = "D:\\uplo";
		extTarFileList(filename, directory);
	}

	private static boolean extTarFileList(String filename, String directory) {
		OutputStream out = null;
		TarInputStream in=null;
		try {
			in = new TarInputStream(new FileInputStream(new File(filename)));
			TarEntry entry = null;
			while ((entry = in.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				System.out.println(entry.getName());
				File outfile = new File(directory + "\\" + entry.getName());
				new File(outfile.getParent()).mkdirs();
				out = new BufferedOutputStream(new FileOutputStream(outfile));
				while (true) {
					byte[] buffer = new byte[1024 * 50];
					int readsize = in.read(buffer);
					out.write(buffer);
					if (readsize < 1024 * 50) {
						out.flush();
						break;
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
