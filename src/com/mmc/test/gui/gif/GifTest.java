package com.mmc.test.gui.gif;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

public class GifTest {
	FileSystemView fsv = FileSystemView.getFileSystemView();
	File com = fsv.getHomeDirectory(); // ����Ƕ�ȡ����·���ķ�����
	String path = com.getAbsolutePath();
	String s = path + "\\11.gif";
	ImageIcon icon = new ImageIcon(s);

	public static void main(String[] s) {
		JFrame frame = new JFrame();
		JLabel lable = new JLabel(new GifTest().icon);
		frame.add(lable, new BorderLayout().CENTER);
		frame.setBounds(100, 100, 500, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}