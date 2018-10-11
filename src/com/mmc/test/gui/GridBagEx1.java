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

		// 填充剩余空间
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		makebutton("Button1", gridbag, c);
		makebutton("Button2", gridbag, c);
		makebutton("Button3", gridbag, c);

		// 声明是最后一个组件（行/列）
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

		// 定此组件为其行或列（gridwidth、gridheight）中的倒数第二个组件，或者让此组件紧跟在以前添加的组件（gridx、gridy）之后
		c.gridwidth = GridBagConstraints.RELATIVE;
		makebutton("Button6", gridbag, c);

		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		makebutton("Button7", gridbag, c);

		// 左侧宽度（总有五个栅格，占两个栅格）
		// 指定组件显示区域的某一行中的单元格数
		c.gridwidth = 2;
		// 让元素在竖直方向上（右侧元素>3?3行:跟随右侧元素所占行数）
		c.gridheight = 3;
		// 指定如何分布额外的垂直空间
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
