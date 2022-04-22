package ch03;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyComponents extends JFrame {

	private JPanel jPanel;
	private JButton button;
	private JLabel label;
	JTextField textField;
	private JPasswordField passwordField;
	private JCheckBox checkBox;

	public MyComponents() {
		initData();
		setIntitLayout();
	}

	private void initData() {
		setTitle("컴포넌트 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);

		jPanel = new JPanel();
		
		Dimension dimension = new Dimension(300, 300);
				
		
		
		jPanel.setPreferredSize(dimension);
		
		button = new JButton("botton");
		label = new JLabel("label");
		textField = new JTextField("hint", 20);
		passwordField = new JPasswordField(20);
		checkBox = new JCheckBox("checkBox", true);

	}

	private void setIntitLayout() {
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 100, 200));

		add(jPanel);
		jPanel.setBackground(Color.BLUE);
		jPanel.add(button);
		jPanel.add(label);
		jPanel.add(textField);
		jPanel.add(passwordField);
		jPanel.add(checkBox);
	}
	public static void main(String[] args) {
		new MyComponents();
	}

}
