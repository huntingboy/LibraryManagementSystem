package com.nomad.admin_ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nomad.connection.Jdbc;

public class DeleteReader {

	private JFrame frame;
	private JTextField textField;
	private JLabel label;
	private JLabel lblNewLabel_1;
	private JButton button;
	private JButton btnNewButton;

	/**
	 * Create the application.
	 */
	public DeleteReader() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("请输入读者的编号：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(14, 42, 124, 22);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(134, 42, 92, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("确认删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String showResult = new String("<html><tr><td align=\"center\">图书编号</td><td align=\"center\">图书名</td><td align=\"center\">作者</td><td align=\"center\">出版社</td><td align=\"center\">数量</td></tr>");
//				Vector<String[]> result = new Jdbc().selectBook(textField.getText());
//				for(String[] strings : result){
//					showResult += "<tr>";
//					for(String values : strings){
//						showResult += "<td align=\"center\">" + values + "</td>";
//					}
//					showResult += "</tr>";
//					showResult += "<br>";
//				}
//				showResult += "</html>";
				if(new Jdbc().deleteReader(Integer.parseInt(textField.getText()))){
					lblNewLabel_1.setText("删除读者成功");
				}else{
					lblNewLabel_1.setText("删除读者失败！");
				}
				
			}
		});
		button.setBounds(235, 42, 86, 22);
		frame.getContentPane().add(button);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(0, 76, 450, 224);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton("返回");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Main mMain = new Main();
				mMain.getLblNewLabel().setText("当前用户：" + Login.Uname);
				mMain.showAllBook();
			}
		});
		btnNewButton.setBounds(333, 42, 82, 22);
		frame.getContentPane().add(btnNewButton);
	}

}
