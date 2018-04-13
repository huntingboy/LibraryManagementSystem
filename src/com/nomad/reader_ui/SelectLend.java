package com.nomad.reader_ui;

import java.awt.ScrollPane;
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

import com.nomad.admin_ui.Login;
import com.nomad.connection.Jdbc;
import com.nomad.entity.Lend;

public class SelectLend {

	private JFrame frame;
	private JTextField textField;
	private JLabel label;
	private JButton button;
	private JButton button_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JScrollPane jScrollPane;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private ButtonGroup buttonGroup;

	/**
	 * Create the application.
	 */
	public SelectLend() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("借阅信息查询");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("请输入读者编号：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(37, 34, 113, 22);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(148, 34, 100, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("开始");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String showResult = new String("<html><tr><td align=\"center\">读者编号</td><td align=\"center\">图书编号</td><td align=\"center\">读者姓名</td><td align=\"center\">借阅日期</td><td align=\"center\">归还日期</td><td align=\"center\">借阅数量</td></tr>");
				Vector<String[]> result = (radioButton.isSelected()) ? new Jdbc().selectLend(Integer.parseInt(textField.getText())) : new Jdbc().selectLend(Integer.parseInt(textField.getText()), 1);
				for(String[] lends : result){
					showResult += "<tr>";
					for(String values : lends){
						showResult += "<td align=\"center\">" + values + "</td>";
					}
					showResult += "</tr>";
					showResult += "<br>";
				}
				showResult += "</html>";
				lblNewLabel_1.setText("查询成功");
				lblNewLabel.setText(showResult);
			}
		});
		button.setBounds(260, 34, 80, 22);
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
		button_1.setBounds(346, 34, 80, 22);
		frame.getContentPane().add(button_1);
		
		lblNewLabel = new JLabel("jieguo");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jScrollPane = new JScrollPane(lblNewLabel);
		jScrollPane.setBounds(0, 66, 450, 219);
		frame.getContentPane().add(jScrollPane);
		
		radioButton = new JRadioButton("读者编号");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				label.setText("请输入读者编号：");
			}
		});
		radioButton.setSelected(true);
		radioButton.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton.setBounds(54, 0, 149, 23);
		frame.getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("书编号");
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				label.setText("请输入书编号：");
			}
		});
		radioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_1.setBounds(174, 0, 149, 23);
		frame.getContentPane().add(radioButton_1);
		
		lblNewLabel_1 = new JLabel("状态");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 285, 450, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton);
		buttonGroup.add(radioButton_1);
	}
}
