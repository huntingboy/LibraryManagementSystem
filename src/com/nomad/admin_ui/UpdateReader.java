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

public class UpdateReader {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label;
	private JLabel label_1;
	private JButton button;
	private JButton button_1;
	private JLabel lblNewLabel;

	/**
	 * Create the application.
	 */
	public UpdateReader() {
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
		label.setBounds(28, 50, 143, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(189, 48, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("读者的新姓名：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(28, 102, 143, 15);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(189, 100, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		button = new JButton("确认修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (new Jdbc().updateReader(Integer.parseInt(textField.getText()), textField_1.getText())) {
					lblNewLabel.setText("修改读者成功");
				} else {
					lblNewLabel.setText("修改读者失败！(可能读者编号错误!)");
				}
			}
		});
		button.setBounds(83, 152, 117, 25);
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
		button_1.setBounds(226, 152, 117, 25);
		frame.getContentPane().add(button_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 281, 450, 15);
		frame.getContentPane().add(lblNewLabel);
	}

}
