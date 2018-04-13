package com.nomad.admin_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.nomad.connection.Jdbc;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField;
	private JPasswordField textField_1;
	private JLabel textField_2;
	private JButton button;
	private JButton button_1;
	public static String Uname = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		//			new Jdbc();
					new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @return the textField
	 */
	public JTextField getTextField() {
		return textField;
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("登录");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("图书管理系统");
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(155, 12, 129, 37);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("用户名：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setBounds(94, 61, 70, 15);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(165, 61, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_2 = new JLabel("密码：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_2.setBounds(94, 102, 70, 15);
		frame.getContentPane().add(label_2);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(165, 100, 114, 19);
		frame.getContentPane().add(textField_1);
		
		button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				点击登录后的事件处理
				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					textField_2.setText("用户名或密码不能为空,请重新输入！");
					return;
				} else {
					//登录合法
					if (new Jdbc().isLegal(textField.getText(), textField_1.getText())) {
						textField_2.setText("登录成功！");
						frame.dispose();
						Main mMain = new Main();
						Uname = textField.getText();
						mMain.getLblNewLabel().setText("当前用户：" + Uname);
						mMain.showAllBook();
					} else {
						textField_2.setText("用户名或密码不合法，请重新输入！");
					}
						
				}
				
			}
		});
		button.setBounds(127, 162, 70, 25);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("注册");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new Register();
			}
		});
		button_1.setBounds(219, 162, 70, 25);
		frame.getContentPane().add(button_1);
		
		textField_2 = new JLabel("");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(12, 258, 426, 15);
		frame.getContentPane().add(textField_2);
	}
}
