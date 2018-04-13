package com.nomad.admin_ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import com.nomad.connection.Jdbc;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField textField_1;
	private JButton button;
	private JButton button_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("注册");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("用户名：");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(94, 61, 70, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(165, 61, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("密码：");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(94, 102, 70, 15);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(165, 100, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		button = new JButton("注册");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				点击注册后的处理
				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					label_3.setText("用户名或密码不能为空，请重新输入!");
					return;
				} else {
					if (new Jdbc().addUser(textField.getText(), textField_1.getText())) {
						
						label_3.setText("注册用户成功！");
						frame.dispose();
						new Login();
					}else {
						label_3.setText("用户名已经存在！");
					}
					
				}
				
			}
		});
		button.setBounds(127, 162, 70, 25);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new Login();
			}
		});
		button_1.setBounds(219, 162, 70, 25);
		frame.getContentPane().add(button_1);
		
		label_2 = new JLabel("用户注册界面");
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(155, 12, 129, 37);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(12, 251, 426, 15);
		frame.getContentPane().add(label_3);
	}
}
