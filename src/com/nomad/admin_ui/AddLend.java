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
import com.nomad.entity.Lend;

public class AddLend {

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
	public AddLend() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("添加借阅信息");
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
		
		label_1 = new JLabel("读者编号：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(99, 77, 70, 15);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(203, 77, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		label_2 = new JLabel("借阅日期：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(99, 107, 70, 15);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(203, 107, 114, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		label_3 = new JLabel("归还日期：");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(99, 137, 70, 15);
		frame.getContentPane().add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(203, 137, 114, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		label_4 = new JLabel("借阅数量：");
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
				Lend lend = new Lend();
				lend.setBookNo(Integer.parseInt(textField.getText()));
				lend.setReaderNo(Integer.parseInt(textField_1.getText()));
				lend.setLendTime(textField_2.getText());
				lend.setReturnTime(textField_3.getText());
				lend.setQuantity(Integer.parseInt(textField_4.getText()));
				
				if (new Jdbc().addLend(lend)) {
					lblNewLabel.setText("添加借阅信息成功");
				} else {
					lblNewLabel.setText("添加借阅信息失败!(图书编号或者读者编号或者数量有误!)");
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
