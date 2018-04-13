package com.nomad.admin_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.nomad.connection.Jdbc;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteLend {

	private JFrame frame;
	private JTextField textField_1;
	private JLabel label_1;
	private JButton button;
	private JButton button_1;
	private JLabel lblNewLabel;

	/**
	 * Create the application.
	 */
	public DeleteLend() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("删除借阅信息");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label_1 = new JLabel("读者编号：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(24, 42, 104, 22);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(134, 42, 92, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		button = new JButton("确认删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(new Jdbc().deleteLend(Integer.parseInt(textField_1.getText()))){
					lblNewLabel.setText("删除借阅信息成功");
				}else{
					lblNewLabel.setText("删除借阅信息失败！");
				}
			}
		});
		button.setBounds(235, 42, 86, 22);
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
		button_1.setBounds(333, 42, 82, 22);
		frame.getContentPane().add(button_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 76, 450, 224);
		frame.getContentPane().add(lblNewLabel);
	}
}
