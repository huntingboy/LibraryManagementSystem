package com.nomad.reader_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.nomad.admin_ui.Login;
import com.nomad.connection.Jdbc;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateLend {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JButton button;
	private JButton button_1;
	private JLabel lblNewLabel;

	/**
	 * Create the application.
	 */
	public UpdateLend() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("修改借阅信息");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("书编号：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(113, 54, 70, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(201, 52, 91, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("读者编号：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(113, 92, 70, 15);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(201, 92, 91, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		label_2 = new JLabel("数量：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(113, 130, 70, 15);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(201, 130, 91, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		button = new JButton("确认修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (new Jdbc().updateLend(Integer.parseInt(textField.getText()), Integer.parseInt(textField_1.getText()), Integer.parseInt(textField_2.getText()))) {
					lblNewLabel.setText("修改借阅信息成功");
				} else {
					lblNewLabel.setText("修改借阅信息失败！（可能借阅信息没有添加，需添加！）");
				}
			}
		});
		button.setBounds(92, 175, 117, 25);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Main mMain = new Main();
				mMain.getLblNewLabel().setText("当前用户：" + Login.Uname);
				mMain.showAllBook();
			}
		});
		button_1.setBounds(233, 175, 117, 25);
		frame.getContentPane().add(button_1);
		
		lblNewLabel = new JLabel("状态");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 285, 450, 15);
		frame.getContentPane().add(lblNewLabel);
	}

}
