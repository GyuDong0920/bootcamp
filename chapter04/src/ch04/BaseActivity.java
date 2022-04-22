package ch04;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BaseActivity extends JFrame {

	String name;
	JPanel Panel;

	public BaseActivity(String name) {
		this.name = name;
		initData();
		SetInitLayout();
	}

	protected void SetInitLayout() {
		setVisible(true);
		add(Panel);

	}

	protected void initData() {

		setTitle(this.name);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel = new JPanel();

	}
}
