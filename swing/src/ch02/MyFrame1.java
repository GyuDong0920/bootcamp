package ch02;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame1 extends JFrame {

	
	// 배열 --> ArrayList로 변경
	private BorderLayout borderLayout;
	JButton[] buttons = new JButton[5];
	String[] titles = { "북", "센터", "남", "동", "서" };
	String[] driections = { 
			BorderLayout.NORTH, 
			BorderLayout.CENTER, 
			BorderLayout.SOUTH, 
			BorderLayout.EAST,
			BorderLayout.WEST };

	public MyFrame1() {
		initData();
		setInitLayout();

	}

	private void initData() {
		setTitle("BorderLayout TEST");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout = new BorderLayout();

//		buttons[0] = new JButton(titles[0]);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(titles[i]);
		}
	}

	private void setInitLayout() {

		setVisible(true);
		setLayout(borderLayout);
		for (int i = 0; i < buttons.length; i++) {
			add(buttons[i], driections[i]);
		}

	}

}
