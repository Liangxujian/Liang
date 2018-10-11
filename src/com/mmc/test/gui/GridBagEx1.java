package com.mmc.test.gui;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class GridBagEx1 extends Applet {
	public GridBagEx1() {
	}
	private static final long serialVersionUID = 1L;

	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c) {
		Button button = new Button(name);
		gridbag.setConstraints(button, c);
		add(button);
	}

	public void init() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setLayout(gridbag);

		// ���ʣ��ռ�
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		makebutton("Button1", gridbag, c);
		makebutton("Button2", gridbag, c);
		makebutton("Button3", gridbag, c);

		// ���������һ���������/�У�
		c.gridwidth = GridBagConstraints.REMAINDER;
		makebutton("Button4", gridbag, c);
		
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 0.0;
//		makebutton("Button1", gridbag, c);
//		makebutton("Button2", gridbag, c);
//		makebutton("Button3", gridbag, c);
//
//		c.gridwidth = GridBagConstraints.REMAINDER; // end row
//		makebutton("Button4", gridbag, c);

		c.weightx = 0.0; // reset to the default
		makebutton("Button5", gridbag, c); // another row

		// �������Ϊ���л��У�gridwidth��gridheight���еĵ����ڶ�������������ô������������ǰ��ӵ������gridx��gridy��֮��
		c.gridwidth = GridBagConstraints.RELATIVE;
		makebutton("Button6", gridbag, c);

		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		makebutton("Button7", gridbag, c);

		// ����ȣ��������դ��ռ����դ��
		// ָ�������ʾ�����ĳһ���еĵ�Ԫ����
		c.gridwidth = 2;
		// ��Ԫ������ֱ�����ϣ��Ҳ�Ԫ��>3?3��:�����Ҳ�Ԫ����ռ������
		c.gridheight = 3;
		// ָ����ηֲ�����Ĵ�ֱ�ռ�
		c.weighty = 1.0;
		makebutton("Button8", gridbag, c);

		c.weighty = 0.0; // reset to the default
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		c.gridheight = 1; // reset to the default
		makebutton("Button9", gridbag, c);
		makebutton("Button10", gridbag, c);
		
		setSize(300, 100);
	}

	public static void main(String args[]) {
		JFrame f = new JFrame("GridBag Layout Example");
		GridBagEx1 ex1 = new GridBagEx1();

		ex1.init();

		f.getContentPane().add("Center", ex1);
		f.pack();
		f.setSize(f.getPreferredSize());
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
