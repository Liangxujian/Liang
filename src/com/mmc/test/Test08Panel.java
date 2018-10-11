package com.mmc.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang.StringUtils;

import com.mmc.test.model.User;

/**
 * GUI�����ϵ
 * ����JTable��������ѡ���ť�¼�
 */
public class Test08Panel extends JFrame {
	private JTable table;
	private Object[] objs;
	
	public void createWindow(final User[] allUsers, Set<User> bUserSet){
		final Map<String, User> userMap = new HashMap<String, User>();
		for(User u : bUserSet){
			userMap.put(u.getUsername(), u);
		}
		
		this.setBounds(600, 100, 600, 550);
		getContentPane().setLayout(new BorderLayout());
		this.setTitle("\u6743\u9650\u63A7\u5236\u5217\u8868");
		
		JPanel funcPanel = new JPanel(new BorderLayout());
		funcPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		final List<User> userList = new ArrayList<User>(Arrays.asList(allUsers));
		final JList<Object> list = new JList<>(userList.toArray());
		funcPanel.add(BorderLayout.NORTH, new JScrollPane(list));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(10, 0, 0, 0));
		final String[] title = {"������", "����"};
		final Object[][] userArray = new Object[2][userMap.size()];
		int i = 0;
		for(User u : userMap.values()){
			userArray[i][0] = u;
			userArray[i][1] = "ɾ��";
			i++;
		}
		final DefaultTableModel model = new DefaultTableModel(userArray,title);
		// ��ֹ��Ԫ�����ݱ༭
		table = new JTable(model){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// ���õ�һ���Զ��峤��
		TableColumnModel cm = table.getColumnModel();
		TableColumn column0 = cm.getColumn(0);
	    column0.setPreferredWidth(800);
	    // ���õڶ������ݾ���
	    TableColumn column1 = cm.getColumn(1);
	    DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	    r.setHorizontalAlignment(JTextField.CENTER);
	    column1.setCellRenderer(r);
	    // ���������ƶ�
	    table.getTableHeader().setReorderingAllowed(false);
	    // ���ӱ��������
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		// �õ�ѡ�е����е�����ֵ
				int r = table.getSelectedRow();
				int c = table.getSelectedColumn();
				// �õ�ѡ�еĵ�Ԫ���ֵ������ж����ַ���
				Object value = table.getValueAt(r, 0);
				// �ж��Ƿ��ǵڶ��У����Ƿ���Ҫ����
				if(c == 1){
					model.removeRow(r);
					userMap.remove(((User)value).getUsername());
				}
	    	}
		});
		funcPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		getContentPane().add(BorderLayout.CENTER, funcPanel);
		
		JPanel searchPanel = new JPanel(new BorderLayout());
		searchPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		final JTextField text_find = new JTextField();
		text_find.addKeyListener(new KeyListener() {
			// ����ĳ����ʱ���ô˷���
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					String keyStr = text_find.getText();
					if(StringUtils.isBlank(keyStr)){
						userList.clear();
						for (int i = 0; i < allUsers.length; i++) {
							userList.add(allUsers[i]);
						}
						list.setListData(userList.toArray());
					} else {
						List<User> l = new ArrayList<User>();
						for(User u : userList){
							String name = u.getUsername();
							if(name != null && name.contains(keyStr)){
								l.add(u);
							}
						}
						userList.clear();
						for(User u : l){
							userList.add(u);
						}
						list.setListData(userList.toArray());
					}
				}
			}
			
			// �ͷ�ĳ����ʱ���ô˷���
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			// ����ĳ����ʱ���ô˷���
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton btnAdd = new JButton("���");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objs = list.getSelectedValues();
				int mapL = userMap.size();
				Vector<Object> vector = null;
				for(int i = 0; i < objs.length; i++){
					User u = (User) objs[i];
					userMap.put(u.getUsername(), u);
					if(mapL < userMap.size()){
						vector = new Vector<Object>();
						vector.add(u);
						vector.add("ɾ��");
						model.addRow(vector);
						mapL++;
					}
				}
			}
		});
		searchPanel.add(BorderLayout.CENTER, text_find);
		searchPanel.add(BorderLayout.WEST, new JLabel("�ؼ��ֲ�ѯ��"));
		searchPanel.add(BorderLayout.EAST, btnAdd);
		getContentPane().add(BorderLayout.NORTH, searchPanel);
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnOk = new JButton("����");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(User u : userMap.values()){
					System.out.println(u);
				}
			}
		});
		btnPanel.add(btnOk);
		JButton btnCanel = new JButton("ȡ��");
		btnCanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
		btnPanel.add(btnCanel);
		getContentPane().add(BorderLayout.SOUTH, btnPanel);
	}
	
	public User[] getAllUsers(){
		final User[] allUsers = {
			new User("������", "666666", 10, "666666@qq.com", "������"),
			new User("��С��", "999999", 18, "999999@qq.com", "����Ԫ"),
			new User("��ɱ��", "952666", 15, "952666@qq.com", "�����"),
			new User("����ħ", "952777", 17, "952777@qq.com", "�����"),
			new User("��ն��", "952888", 25, "952888@qq.com", "�����"),
			new User("������", "159357", 29, "159357@qq.com", "������"),
			new User("Ұ����", "525252", 38, "525252@qq.com", "�ձ���"),
			new User("Ȩ�ɶ�", "222222", 12, "222222@qq.com", "������"),
			new User("��С��", "555555", 22, "555555@qq.com", "��С��"),
			new User("Ҷ����", "123456", 24, "123456@qq.com", "������"),
			new User("¬����", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("����ʤ", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("������", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("³����", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("��С��", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("��С��", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("��С��", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("��˼��", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("��͢��", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("κ����", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("����ȫ", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("�ʸ���", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("������", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("����", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("֣����", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("������", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("������", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("�˴�ɩ", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("�����", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("������", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("������", "123456", 24, "123456@qq.com", "��ɽ��"),
			new User("�ξ�ס", "123456", 24, "123456@qq.com", "��ɽ��")
		};
		return allUsers;
	}
	
	public static void main(String[] args) {
		Test08Panel panel = new Test08Panel();
		User[] allUsers = panel.getAllUsers();
		Set<User> bUserSet = new HashSet<User>();
		bUserSet.add(allUsers[3]);
		bUserSet.add(allUsers[5]);
		panel.createWindow(allUsers, bUserSet);
		panel.setVisible(true);
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		panel.setResizable(false);
	}
}