package com.mmc.test.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * ����������򣬵���JTable��ĳһ��title��
 * ���title��Ӧ���оͻᰴ������/�����������У�
 * �������Criteria�ı���������"ese"��
 * ���"Do Filter"��ť��
 * JTable��ֻ��ʾ����"ese"�ַ������У�
 * Ҳ����China��Japan���У�
 * ����ı�������ʲô��û�У�
 * ���"Do Filter"��ť��
 * ��ʱJTable����ʾ���е��С�
 */
public class JTableTester {
    static String data[][] = {
            {"China","Beijing","Chinese"},
            {"America","Washington","English"},
            {"Korea","Seoul","Korean"},
            {"Japan","Tokyo","Japanese"},
            {"France","Paris","French"},
            {"England","London","English"},
            {"Germany","Berlin","German"},
    };
    static String titles[] = {"Country","Capital","Language"};
    public static void main(String[] args) {
        DefaultTableModel model = new DefaultTableModel(data,titles);
        JTable jTable = new JTable(model);
        final TableRowSorter sorter = new TableRowSorter(model);
        jTable.setRowSorter(sorter); //ΪJTable����������
 
        JScrollPane sPane = new JScrollPane();
        sPane.setViewportView(jTable);
 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        JLabel label = new JLabel("Criteria:");
        final JTextField jTextField = new JTextField();
        JButton button = new JButton("Do Filter");
        panel.add(label);
        panel.add(jTextField);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jTextField.getText().length()==0){
                    sorter.setRowFilter(null);
                }else{
                    sorter.setRowFilter(RowFilter.regexFilter(jTextField.getText()));//ΪJTable���û���������ʽ�Ĺ�������
                }
            }
        });
 
        JFrame f = new JFrame("JTable Sorting and Filtering");
        f.getContentPane().add(sPane, BorderLayout.CENTER);
        f.getContentPane().add(panel,BorderLayout.SOUTH);
        f.setSize(400,300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}