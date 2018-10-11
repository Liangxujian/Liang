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
		// ��������ʣ��ռ�ʱ�����ռ�
		c.fill = GridBagConstraints.BOTH;
		// ������ֱ����ռ������Ԫ��
		c.gridheight = 2;
		// ����ˮƽ����ռһ����Ԫ��
		c.gridwidth = 1;
		// �����ڷŴ�ʱ�����Ȳ��䣨0.0��Ĭ�ϣ���
		c.weightx = 0.0;
		// �����ڷŴ�ʱ���߶Ȳ��䣨0.0��Ĭ�ϣ���
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.SOUTHWEST;
		JButton jButton1 = new JButton("��ť1");
		gridbag.setConstraints(jButton1, c);
		this.add(jButton1);
		
		// �����ʣ��ռ�
		c.fill = GridBagConstraints.NONE;
		// ������һ���е����һ��Ԫ��
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.weightx = 1.0;// Ĭ��ֵΪ0.0
		c.weighty = 0.8;
		JButton jButton2 = new JButton("��ť2");
		gridbag.setConstraints(jButton2, c);
		this.add(jButton2);
		// ��Ӱ�ť3
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0.2;
		JButton jButton3 = new JButton("��ť3");
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