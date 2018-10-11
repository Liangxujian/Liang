package com.mmc.test.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Example extends JFrame {
	public Example() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);
		// 当格子有剩余空间时，填充空间
		c.fill = GridBagConstraints.BOTH;
		// 表明竖直方向占两个单元格
		c.gridheight = 2;
		// 表明水平方向占一个单元格
		c.gridwidth = 1;
		// 当窗口放大时，长度不变（0.0（默认））
		c.weightx = 0.0;
		// 当窗口放大时，高度不变（0.0（默认））
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.SOUTHWEST;
		JButton jButton1 = new JButton("按钮1");
		gridbag.setConstraints(jButton1, c);
		this.add(jButton1);
		
		// 不填充剩余空间
		c.fill = GridBagConstraints.NONE;
		// 表明是一列中的最后一个元素
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.weightx = 1.0;// 默认值为0.0
		c.weighty = 0.8;
		JButton jButton2 = new JButton("按钮2");
		gridbag.setConstraints(jButton2, c);
		this.add(jButton2);
		// 添加按钮3
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0.2;
		JButton jButton3 = new JButton("按钮3");
		gridbag.setConstraints(jButton3, c);
		this.add(jButton3);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
	}
	
	public static void main(String[] args) {
		Example exam = new Example();
		exam.setVisible(true);
	}
}