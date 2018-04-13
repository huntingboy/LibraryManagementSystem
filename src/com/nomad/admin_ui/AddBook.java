package com.nomad.admin_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.nomad.admin_ui.Login;
import com.nomad.admin_ui.Main;
import com.nomad.connection.Jdbc;
import com.nomad.entity.Book;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBook {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JButton button;
	private JButton button_1;


	/**
	 * Create the application.
	 */
	public AddBook() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("添加图书");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("图书编号：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(99, 47, 70, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(203, 47, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("图书名：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(99, 77, 70, 15);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(203, 77, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		label_2 = new JLabel("作者：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(99, 107, 70, 15);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(203, 107, 114, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		label_3 = new JLabel("出版社：");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(99, 137, 70, 15);
		frame.getContentPane().add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(203, 137, 114, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		label_4 = new JLabel("数量：");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(99, 167, 70, 15);
		frame.getContentPane().add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBounds(203, 167, 114, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 285, 450, 15);
		frame.getContentPane().add(lblNewLabel);
		
		button = new JButton("确认");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book book = new Book();
				book.setBookNo(Integer.parseInt(textField.getText()));
				book.setBookName(textField_1.getText());
				book.setBookAuthor(textField_2.getText());
				book.setBookPublishment(textField_3.getText());
				book.setBookQuantity(Integer.parseInt(textField_4.getText()));
				
				if (new Jdbc().addBook(book)) {
					lblNewLabel.setText("添加图书信息成功");
				} else {
					lblNewLabel.setText("添加图书信息失败！(可能图书已经存在,尝试修改图书或编号！或者数量有误！)");
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
