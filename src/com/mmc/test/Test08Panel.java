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
 * GUI面板联系
 * 包含JTable、下拉框选项、按钮事件
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
		final String[] title = {"对象名", "操作"};
		final Object[][] userArray = new Object[2][userMap.size()];
		int i = 0;
		for(User u : userMap.values()){
			userArray[i][0] = u;
			userArray[i][1] = "删除";
			i++;
		}
		final DefaultTableModel model = new DefaultTableModel(userArray,title);
		// 防止单元格内容编辑
		table = new JTable(model){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// 设置第一列自定义长度
		TableColumnModel cm = table.getColumnModel();
		TableColumn column0 = cm.getColumn(0);
	    column0.setPreferredWidth(800);
	    // 设置第二列内容居中
	    TableColumn column1 = cm.getColumn(1);
	    DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	    r.setHorizontalAlignment(JTextField.CENTER);
	    column1.setCellRenderer(r);
	    // 不可整列移动
	    table.getTableHeader().setReorderingAllowed(false);
	    // 增加表格点击监听
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		// 得到选中的行列的索引值
				int r = table.getSelectedRow();
				int c = table.getSelectedColumn();
				// 得到选中的单元格的值，表格中都是字符串
				Object value = table.getValueAt(r, 0);
				// 判断是否是第二列，即是否需要弹窗
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
			// 按下某个键时调用此方法
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
			
			// 释放某个键时调用此方法
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			// 键入某个键时调用此方法
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton btnAdd = new JButton("添加");
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
						vector.add("删除");
						model.addRow(vector);
						mapL++;
					}
				}
			}
		});
		searchPanel.add(BorderLayout.CENTER, text_find);
		searchPanel.add(BorderLayout.WEST, new JLabel("关键字查询："));
		searchPanel.add(BorderLayout.EAST, btnAdd);
		getContentPane().add(BorderLayout.NORTH, searchPanel);
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnOk = new JButton("保存");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(User u : userMap.values()){
					System.out.println(u);
				}
			}
		});
		btnPanel.add(btnOk);
		JButton btnCanel = new JButton("取消");
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
			new User("赵日天", "666666", 10, "666666@qq.com", "天龙人"),
			new User("王小明", "999999", 18, "999999@qq.com", "二次元"),
			new User("李杀神", "952666", 15, "952666@qq.com", "外地人"),
			new User("王诛魔", "952777", 17, "952777@qq.com", "外地人"),
			new User("刘斩仙", "952888", 25, "952888@qq.com", "外地人"),
			new User("哆啦梦", "159357", 29, "159357@qq.com", "喵星人"),
			new User("野比五", "525252", 38, "525252@qq.com", "日本人"),
			new User("权律二", "222222", 12, "222222@qq.com", "韩国银"),
			new User("王小萌", "555555", 22, "555555@qq.com", "萌小银"),
			new User("叶良辰", "123456", 24, "123456@qq.com", "本地人"),
			new User("卢俊义", "123456", 24, "123456@qq.com", "梁山人"),
			new User("公孙胜", "123456", 24, "123456@qq.com", "梁山人"),
			new User("呼延灼", "123456", 24, "123456@qq.com", "梁山人"),
			new User("鲁智深", "123456", 24, "123456@qq.com", "梁山人"),
			new User("阮小二", "123456", 24, "123456@qq.com", "梁山人"),
			new User("阮小五", "123456", 24, "123456@qq.com", "梁山人"),
			new User("阮小七", "123456", 24, "123456@qq.com", "梁山人"),
			new User("郝思文", "123456", 24, "123456@qq.com", "梁山人"),
			new User("单廷珪", "123456", 24, "123456@qq.com", "梁山人"),
			new User("魏定国", "123456", 24, "123456@qq.com", "梁山人"),
			new User("安道全", "123456", 24, "123456@qq.com", "梁山人"),
			new User("皇甫端", "123456", 24, "123456@qq.com", "梁山人"),
			new User("扈三娘", "123456", 24, "123456@qq.com", "梁山人"),
			new User("金大坚", "123456", 24, "123456@qq.com", "梁山人"),
			new User("郑天寿", "123456", 24, "123456@qq.com", "梁山人"),
			new User("陶宗旺", "123456", 24, "123456@qq.com", "梁山人"),
			new User("丁得孙", "123456", 24, "123456@qq.com", "梁山人"),
			new User("顾大嫂", "123456", 24, "123456@qq.com", "梁山人"),
			new User("孙二娘", "123456", 24, "123456@qq.com", "梁山人"),
			new User("王定六", "123456", 24, "123456@qq.com", "梁山人"),
			new User("郁保四", "123456", 24, "123456@qq.com", "梁山人"),
			new User("段景住", "123456", 24, "123456@qq.com", "梁山人")
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