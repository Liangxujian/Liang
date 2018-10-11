package com.mmc.test.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Test extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	
	public Test() {
		setTitle("GridBagConstraints\u5E03\u5C40\u6D4B\u8BD5\u7A97\u53E3");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnUp = new JButton("上移");
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.gridheight = 3;
		gbc_btnUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnUp.fill = GridBagConstraints.VERTICAL;
		gbc_btnUp.anchor = GridBagConstraints.WEST;
		gbc_btnUp.gridx = 0;
		gbc_btnUp.gridy = 0;
		getContentPane().add(btnUp, gbc_btnUp);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"序号", "用户名", "密码"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnDown = new JButton("下移");
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.fill = GridBagConstraints.VERTICAL;
		gbc_btnDown.anchor = GridBagConstraints.WEST;
		gbc_btnDown.gridheight = 3;
		gbc_btnDown.insets = new Insets(0, 0, 0, 5);
		gbc_btnDown.gridx = 0;
		gbc_btnDown.gridy = 3;
		getContentPane().add(btnDown, gbc_btnDown);
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.setBounds(400, 250, 500, 300);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}
