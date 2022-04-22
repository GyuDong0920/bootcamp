package ch02;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NoLayoutEx extends JFrame {

	ArrayList<JButton> buttons = new ArrayList<JButton>();
	private JButton button1;
	private JButton button2;
	private JButton button3;

	public NoLayoutEx() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("NO Layout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
//		for (int i = 0; i < buttons.size(); i++) {
//			buttons.add(new JButton ("button" + i));
//		}
		buttons.add(new JButton("1"));
		buttons.add(new JButton("2"));
		buttons.add(new JButton("3"));

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);

		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setSize(50, 50);
			buttons.get(i).setLocation(50 + (10 * i), (50 + (10 * i)));
			add(buttons.get(i));
		}

		button1.setLocation(10, 10);

		button2.setLocation(100, 100);

		button3.setLocation(200, 200);

		add(button1);
		add(button2);
		add(button3);

	}

	public static void main(String[] args) {
		new NoLayoutEx();
	}
}
