package com.mmc.test.yasuobao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import net.lingala.zip4j.exception.ZipException;

import com.mmc.test.util.FileUtil;
import com.mmc.test.util.TxTUtil;

public class MyZipTest extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField fileAddress;
	private File appointFolder = null;

	FileSystemView fsv = FileSystemView.getFileSystemView();
	File com = fsv.getHomeDirectory();    //����Ƕ�ȡ����·���ķ�����
	
	public static void main(String[] args) {
		MyZipTest test = new MyZipTest();
		test.setVisible(true);
	}
	
	public MyZipTest() {
		setTitle("ZipTest");
		getContentPane().setLayout(null);
		
		fileAddress = new JTextField();
		fileAddress.setBounds(12, 31, 307, 22);
		getContentPane().add(fileAddress);
		fileAddress.setColumns(10);
		
		JButton selectBtn = new JButton("ѡ��");
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(){
					public void run(){
						// ѡ���ļ���
						appointFolder = FileUtil.getInstance().chooseDirectoryFolder(null);
					}
				}.start();
			}
		});
		selectBtn.setBounds(331, 30, 99, 25);
		getContentPane().add(selectBtn);
		
		JButton okBtn = new JButton("��ʼ");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ʼ���ù�����
				if (appointFolder != null) {
					File targetFile = new File(fileAddress.getText());
					File pwdFile = new File(com.getPath() + "\\����.txt");
					if (targetFile != null && targetFile.exists()) {
						File[] zipFiles = targetFile.listFiles();
						if (zipFiles != null && zipFiles.length > 0) {
							if (pwdFile != null && pwdFile.exists()) {
								File[] files = null;
								String ppwd = null;
								List<String> pwds = TxTUtil.txt2List(pwdFile);
								if (pwds.size() > 0) {
									for (File zipFile : zipFiles) {
										for (String pwd : pwds) {
											try {
												System.out.println(zipFile.getName() + "--" + pwd);
												files = ZipUtilTest.unzip(zipFile.getAbsolutePath(), pwd);
												if (files.length > 0) {
													ppwd = pwd;
													break;
												}
											} catch (ZipException e1) {
												continue;
											}
										}
										if (files != null && files.length > 0) {
											System.out.println(zipFile.getName() + "��ѹ�ɹ�������Ϊ��" + ppwd);
										} else {
											System.out.println(zipFile.getName() + "��ѹʧ�ܣ�");
										}
									}
								}
							}
						}
					}
				}
			}
		});
		okBtn.setBounds(102, 90, 99, 25);
		getContentPane().add(okBtn);
		
		JButton canelBtn = new JButton("ȡ��");
		canelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		canelBtn.setBounds(231, 90, 99, 25);
		getContentPane().add(canelBtn);
		
		this.setBounds(600, 200, 450, 160);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
