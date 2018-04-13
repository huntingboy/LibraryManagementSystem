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
import com.nomad.entity.Book;
import com.nomad.entity.Reader;

public class AddReader {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JButton button;
	private JButton button_1;

	/**
	 * Create the application.
	 */
	public AddReader() {
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
		
		label = new JLabel("读者编号：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(99, 47, 70, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(203, 47, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("读者姓名：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(99, 77, 70, 15);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(203, 77, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		label_2 = new JLabel("性别：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(99, 107, 70, 15);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(203, 107, 114, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 285, 450, 15);
		frame.getContentPane().add(lblNewLabel);
		
		button = new JButton("确认");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reader reader = new Reader();
				reader.setReaderNo(Integer.parseInt(textField.getText()));
				reader.setReaderName(textField_1.getText());
				reader.setReaderSex((textField_2.getText()));
				
				if (new Jdbc().addReader(reader)) {
					lblNewLabel.setText("添加读者信息成功");
				} else {
					lblNewLabel.setText("添加读者信息失败！(可能读者已经存在,尝试修改读者或编号！)");
				}
			}
		});
		button.setBounds(124, 207, 117, 25);
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
		button_1.setBounds(270, 207, 117, 25);
		frame.getContentPane().add(button_1);
	}

}
