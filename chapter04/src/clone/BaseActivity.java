package clone;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BaseActivity extends JFrame {

	String name;
	JPanel panel;

	public BaseActivity(String name) {
		this.name = name;
		initData();
		setInitLayout();

	}

	protected void initData() {
		setVisible(true);
		add(panel);

	}

	protected void setInitLayout() {
		setTitle(this.name);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();

	}
}

	







