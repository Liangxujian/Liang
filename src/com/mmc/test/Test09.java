package com.mmc.test;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;
/**
 * JTable单元格内容换行案例（暂时没用上）
 */
public class Test09 extends JFrame {
	public Test09() {
		JTable tbl = new JTable(new String[][] { {
				"JTable 里单元格内容的显示器是 TableCellRenderer。",
				"默认的显示器（DefaultTableCellRenderer） 继承 JLabel 所以不方便多行显示。",
				"要多行显示应该继承 JTextArea（参看下面的 TableCellTextAreaRenderer 类）。",
				"当然，别忘了调用 JTable.setDefaultRenderer() 登记你的显示器。" } },
				"A B C D".split(" "));
		tbl.setDefaultRenderer(Object.class, new TableCellTextAreaRendererr());
		add(new JScrollPane(tbl));
		setSize(800, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Test09();
	}
}

class TableCellTextAreaRendererr extends JTextArea implements TableCellRenderer {
	public TableCellTextAreaRendererr() {
		setLineWrap(true);
		setWrapStyleWord(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// 计算当下行的最佳高度
		int maxPreferredHeight = 0;
		for (int i = 0; i < table.getColumnCount(); i++) {
			setText("" + table.getValueAt(row, i));
			setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
			maxPreferredHeight = Math.max(maxPreferredHeight,
					getPreferredSize().height);
		}

		if (table.getRowHeight(row) != maxPreferredHeight) // 少了这行则处理器瞎忙
			table.setRowHeight(row, maxPreferredHeight);

		setText(value == null ? "" : value.toString());
		return this;
	}
}