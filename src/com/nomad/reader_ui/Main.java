package com.nomad.reader_ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.text.StyledEditorKit.FontFamilyAction;

import com.nomad.admin_ui.Login;
import com.nomad.connection.Jdbc;
import com.sun.istack.internal.FinalArrayList;

import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {

	private JFrame frame;
	private JMenuBar menuBar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JScrollPane jScrollPane;
	private JMenu menu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem_8;
	private JMenuItem mntmNewMenuItem_9;
	private JMenuItem mntmNewMenuItem_10;
	private JMenuItem mntmNewMenuItem_11;
	
	
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * @return the lblNewLabel
	 */
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	/**
	 * @return the lblNewLabel_1
	 */
	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public void showAllBook() {
		String showResult = new String("<html><tr><td></td><td></td><td align=\"center\">图书列表</td></tr>"
				+ "<tr><td align=\"center\">图书编号</td><td align=\"center\">图书名</td><td align=\"center\">作者</td><td align=\"center\">出版社</td><td align=\"center\">数量</td></tr>");
		Vector<String[]> result = new Jdbc().selectAllBook();
		for(String[] strings : result){
			showResult += "<tr>";
			for(String values : strings){
				System.out.println(values + "\t");
				showResult += "<td align=\"center\">" + values + "</td>";
			}
			System.out.println("\n");
			showResult += "</tr><br>";
		}
		showResult += "</html>";
		
		lblNewLabel_1.setText(showResult);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("图书管理系统（主界面）");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		frame.getContentPane().add(menuBar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 285, 450, 15);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
//		lblNewLabel_1.setBounds(22, 34, 384, 209);
//		lblNewLabel_1.setLineWrap(true);
//		lblNewLabel_1.setWrapStyleWord(true);
		jScrollPane = new JScrollPane(lblNewLabel_1);
		jScrollPane.setBounds(0, 21, 450, 264);
		frame.getContentPane().add(jScrollPane);
		
		menu = new JMenu("图书信息");
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menu);
		
		mntmNewMenuItem = new JMenuItem("查询图书");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				查询图书的事件处理
				
				frame.dispose();
				new SelectBook();
				
			}
		});
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		menu.add(mntmNewMenuItem);
		
		mnNewMenu_1 = new JMenu("借阅信息");
		mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_1);
		
		mntmNewMenuItem_8 = new JMenuItem("查询借阅信息");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new SelectLend();
			}
		});
		mntmNewMenuItem_8.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		mntmNewMenuItem_9 = new JMenuItem("修改借阅信息");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new UpdateLend();
			}
		});
		mntmNewMenuItem_9.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		mntmNewMenuItem_10 = new JMenuItem("添加借阅信息");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new AddLend();
			}
		});
		mntmNewMenuItem_10.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(mntmNewMenuItem_10);
		
		JMenu menu_1 = new JMenu("关于");
		menuBar.add(menu_1);
		
		JMenuItem menuItem = new JMenuItem("帮助");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel_1.setText("<html><tr><td \"center\">帮助信息</td></tr>"
						+ "<tr><td \"center\">图书信息：完成对图书信息的增删改查。</td></tr>"
						+ "<tr><td \"center\">借阅信息：完成对借阅信息的增删该查。</td></tr>"
						+ "<tr><pre>	    增：用户第一次来借书。</pre></tr>"
						+ "<tr><pre>	    删：用户毕业或者退学。</pre></tr>"
						+ "<tr><pre>	    改：借书或者还书。</pre></tr>"
						+ "<tr><pre>	    查：借阅情况（依据读者编号或者图书编号）。</pre></tr>"
						+ "<tr><td \"center\">注意：此系统仅含有管理员的使用使用界面，拥有最高权限！</td></tr>"
						+ "<tr><td \"center\">感谢使用，欢迎提出产品建议！</td></tr>"
						+ "<tr><td \"center\">Gmail:1076441856gy@gmail.com</td></tr></html>");
			}
		});
		menu_1.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("注销");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new Login();
			}
		});
		menu_1.add(menuItem_1);
	}
}
