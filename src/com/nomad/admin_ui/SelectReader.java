package com.nomad.admin_ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nomad.connection.Jdbc;

public class SelectReader {

	private JFrame frame;
	private JTextField textField;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JScrollPane jScrollPane;
	private JButton button;
	private JButton btnNewButton;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private ButtonGroup buttonGroup;

	/**
	 * Create the application.
	 */
	public SelectReader() {
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
		
		label = new JLabel("请输入读者姓名：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(15, 34, 111, 22);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(134, 34, 92, 22);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 285, 450, 15);
		frame.getContentPane().add(lblNewLabel);
		
		button = new JButton("开始搜索");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String showResult = new String("<html><tr><td align=\"center\">读者编号</td><td align=\"center\">读者姓名</td><td align=\"center\">性别</td></tr>");
				Vector<String[]> result = (radioButton.isSelected() ? new Jdbc().selectReader(textField.getText()) : new Jdbc().selectReader(Integer.parseInt(textField.getText())));
				for(String[] strings : result){
					showResult += "<tr>";
					for(String values : strings){
						showResult += "<td align=\"center\">" + values + "</td>";
					}
					showResult += "</tr>";
					showResult += "<br>";
				}
				showResult += "</html>";
				lblNewLabel.setText("查询成功");
				lblNewLabel_1.setText(showResult);
			}
		});
		button.setBounds(235, 34, 86, 22);
		frame.getContentPane().add(button);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		jScrollPane = new JScrollPane(lblNewLabel_1);
		jScrollPane.setBounds(0, 66, 450, 219);
		frame.getContentPane().add(jScrollPane);
		
		btnNewButton = new JButton("返回");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Main mMain = new Main();
				mMain.getLblNewLabel().setText("当前用户：" + Login.Uname);
				mMain.showAllBook();
			}
		});
		btnNewButton.setBounds(333, 34, 82, 22);
		frame.getContentPane().add(btnNewButton);
		
		radioButton = new JRadioButton("读者姓名");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				label.setText("请输入读者姓名：");
			}
		});
		radioButton.setSelected(true);
		radioButton.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton.setBounds(30, 0, 149, 23);
		frame.getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("读者编号");
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				label.setText("请输入读者编号：");
			}
		});
		radioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_1.setBounds(198, 0, 149, 23);
		frame.getContentPane().add(radioButton_1);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton);
		buttonGroup.add(radioButton_1);
	}

}
