package com.mmc.test.util;

import java.awt.Window;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang.StringUtils;

public class FileUtil {
	private static class Temp {
		static FileUtil instance = new FileUtil();
	}

	public static FileUtil getInstance() {
		return Temp.instance;
	}
	
	public File chooseDirectoryFile (Window parent, String... suffixs) {
		File[] files = chooseFiles(parent, null, false, suffixs);
		if (files != null && files.length > 0)
			return files[0];
		return null;
	}
	
	public File[] chooseDirectoryFiles (Window parent, String... suffixs) {
		File[] files = chooseFiles(parent, null, true, suffixs);
		return files;
	}
	
	public File chooseAppointFile (Window parent, String appoint, String... suffixs) {
		File[] files = chooseFiles(parent, appoint, false, suffixs);
		if (files != null && files.length > 0)
			return files[0];
		return null;
	}
	
	public File[] chooseAppointFiles (Window parent, String appoint, String... suffixs) {
		File[] files = chooseFiles(parent, appoint, true, suffixs);
		return files;
	}
	
	/**
	 * 定位选择文件
	 * @param parent 该对话框的父组件，可以为 null
	 * @param appoint 自定义的初始路径
	 * @param multiSelect 是否开启多选
	 * @param suffixs 指定文件类型的尾缀，没有则通用
	 * @return
	 */
	private File[] chooseFiles (Window parent, String appoint, boolean multiSelect, final String... suffixs) {
		JFileChooser chooser = null;
		boolean isCustom = false;
		if (appoint != null || StringUtils.isNotBlank(appoint)) {
			File file = new File(appoint);
			if (file.exists() && file.isDirectory())
				isCustom = true;
		}
		if (isCustom) {
			chooser = new JFileChooser(appoint);
		} else {
			FileSystemView fsv = FileSystemView.getFileSystemView();
			File com = fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
			chooser = new JFileChooser(com.getPath());
		}
		if (multiSelect) {
			chooser.setMultiSelectionEnabled(true);//最重要一句，启用多选
		}
		chooser.setFileFilter(new FileFilter() {
			// 返回此过滤器的描述
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				String describe = "";
				if (suffixs == null) {
					describe = "所有文件";
				} else {
					describe = StringUtils.join(suffixs, " & ");
					describe = describe.replace(".", "");
				}
				return describe;
			}
			
			// 选出指定的文件类型
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if (f.isDirectory())
					return true;
				boolean include = false;
				if (suffixs == null) {
					include = true;
				} else {
					for (String suffix : suffixs) {
						if (f.getName().endsWith(suffix)) {
							include = true;
							break;
						}
					}
				}
				return include;
			}
		});
		int result = chooser.showOpenDialog(parent);
		if(result == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = null;
			if (multiSelect) {
				selectedFiles = chooser.getSelectedFiles();
			} else {
				File selectedFile = chooser.getSelectedFile();
				selectedFiles = new File[]{selectedFile};
			}
			return selectedFiles;
		}
		return null;
	}
	
	public File chooseDirectoryFolder (Window parent) {
		File[] folders = chooseFolders(parent, null, false);
		if (folders != null && folders.length > 0)
			return folders[0];
		return null;
	}
	
	public File[] chooseDirectoryFolders (Window parent) {
		File[] folders = chooseFolders(parent, null, true);
		return folders;
	}
	
	public File chooseAppointFolder (Window parent, String appoint) {
		File[] folders = chooseFolders(parent, appoint, false);
		if (folders != null && folders.length > 0)
			return folders[0];
		return null;
	}
	
	public File[] chooseAppointFolders (Window parent, String appoint) {
		File[] folders = chooseFolders(parent, appoint, true);
		return folders;
	}
	
	/**
	 * 定位选择文件夹
	 * @param parent 该对话框的父组件，可以为 null
	 * @param appoint 自定义的初始路径
	 * @param multiSelect 是否开启多选
	 * @return
	 */
	private File[] chooseFolders (Window parent, String appoint, boolean multiSelect) {
		JFileChooser chooser = null;
		boolean isCustom = false;
		if (appoint != null || StringUtils.isNotBlank(appoint)) {
			File file = new File(appoint);
			if (file.exists() && file.isDirectory())
				isCustom = true;
		}
		if (isCustom) {
			chooser = new JFileChooser(appoint);
		} else {
			FileSystemView fsv = FileSystemView.getFileSystemView();
			File com = fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
			chooser = new JFileChooser(com.getPath());
		}
		if (multiSelect) {
			chooser.setMultiSelectionEnabled(true);//最重要一句，启用多选
		}
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//设置只能选择目录
		int result = chooser.showOpenDialog(parent);
		if(result == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = null;
			if (multiSelect) {
				selectedFiles = chooser.getSelectedFiles();
			} else {
				File selectedFile = chooser.getSelectedFile();
				selectedFiles = new File[]{selectedFile};
			}
			return selectedFiles;
		}
		return null;
	}
	
	public File saveDirectoryFile (Window parent) {
		File saveFolder = saveAppointFile(parent, null);
		return saveFolder;
	}
	
	/**
	 * 选择保存文件的目录
	 * @param parent 该对话框的父组件，可以为 null
	 * @param appoint 自定义的初始路径
	 * @return 返回自定义选择路径后的文件夹，进行路径的拼接
	 */
	public File saveAppointFile (Window parent, String appoint) {
		JFileChooser chooser = null;
		boolean isCustom = false;
		if (appoint != null || StringUtils.isNotBlank(appoint)) {
			File file = new File(appoint);
			if (file.exists() && file.isDirectory())
				isCustom = true;
		}
		if (isCustom) {
			chooser = new JFileChooser(appoint);
		} else {
			FileSystemView fsv = FileSystemView.getFileSystemView();
			File com = fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
			chooser = new JFileChooser(com.getPath());
		}
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//设置只能选择目录
		chooser.setDialogTitle("保存");
		// 设置保存
		int result = chooser.showSaveDialog(new JFrame());
		try {
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				if (!selectedFile.exists()) {
					selectedFile.createNewFile();
				}
				return selectedFile;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		File[] result = FileUtil.getInstance().chooseFolders(null, null, false);
//		System.out.println(result[0].getAbsolutePath());
	}
	
	private FileUtil() {
		// TODO Auto-generated constructor stub
	}
}
