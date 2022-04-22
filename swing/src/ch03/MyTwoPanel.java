package ch03;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTwoPanel extends JFrame {

	private JPanel jPanel1;
	private JPanel jPanel2;

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;

	public MyTwoPanel() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("패널 연습");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jPanel1 = new JPanel();
		jPanel1.setBackground(Color.yellow);

		jPanel2 = new JPanel();
		jPanel2.setBackground(Color.blue);

		button1 = new Button("botton1");
		button2 = new Button("botton2");
		button3 = new Button("botton3");
		button4 = new Button("botton4");
		button5 = new Button("botton5");

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(new GridLayout(2, 1));
		jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		add(jPanel1);
		add(jPanel2);
	
		jPanel1.add(button1);
		jPanel1.add(button2);
		jPanel1.add(button3);
		
		jPanel2.add(button4);
		jPanel2.add(button5);
	}

	public static void main(String[] args) {
		new MyTwoPanel();
	}
}
