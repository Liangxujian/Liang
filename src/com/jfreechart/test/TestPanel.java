package com.jfreechart.test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

/**
 * 可操作面板案例
 */
public class TestPanel extends JFrame implements ActionListener {
	private JTextField textField;
	private JComboBox comboBox;
	private String[] changjia = { "海尔", "美的", "TCL", "格力", "华为", "小米" };

	public TestPanel() {
		setTitle("\u6848\u4F8B\u6D4B\u8BD5");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 492, 468);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u96F6\u4EF6\u540D\uFF1A");
		label.setBounds(27, 47, 111, 36);
		label.setFont(new Font("宋体", Font.PLAIN, 22));
		panel.add(label);

		textField = new JTextField();
		textField.setBounds(138, 56, 284, 24);
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u96F6\u4EF6\u4EA7\u5546\uFF1A");
		label_1.setBounds(27, 106, 111, 36);
		label_1.setFont(new Font("宋体", Font.PLAIN, 22));
		panel.add(label_1);

		JLabel label_2 = new JLabel("\u96F6\u4EF6\u7C7B\u578B\uFF1A");
		label_2.setBounds(27, 172, 111, 36);
		label_2.setFont(new Font("宋体", Font.PLAIN, 22));
		panel.add(label_2);

		comboBox = new JComboBox(changjia);
		comboBox.setBounds(138, 113, 284, 24);
		comboBox.addActionListener(this);
		panel.add(comboBox);

		JCheckBox checkBox = new JCheckBox("\u5B9E\u4F53\u4EF6");
		checkBox.setBounds(146, 181, 111, 25);
		panel.add(checkBox);

		JCheckBox checkBox_1 = new JCheckBox("\u865A\u62DF\u4EF6");
		checkBox_1.setBounds(146, 224, 111, 25);
		panel.add(checkBox_1);

		JCheckBox checkBox_2 = new JCheckBox("\u7ED3\u6784\u4EF6");
		checkBox_2.setBounds(146, 270, 111, 25);
		panel.add(checkBox_2);

		this.setResizable(true);
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocation(200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBox) {
			int index = comboBox.getSelectedIndex();
			switch (index) {
			case 0:
				System.out.println("index0");
				break;
			case 1:
				System.out.println("index1");
				break;
			case 2:
				System.out.println("index2");
				break;
			case 3:
				System.out.println("index3");
				break;
			case 4:
				System.out.println("index4");
				break;
			case 5:
				System.out.println("index5");
				break;
			case 6:
				System.out.println("index6");
				break;
			}
		}
	}

	public static void main(String[] args) {
		TestPanel test = new TestPanel();
	}
}
