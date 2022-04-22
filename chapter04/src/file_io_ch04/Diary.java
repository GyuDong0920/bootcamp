package file_io_ch04;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Diary extends JFrame {

	JButton button;
	String name;
	JPanel panel;
	JTextArea textArea;
	String fileName; 
	
	public Diary(String name) {
		this.name = name;
	
		initData();
		setInitLayout();

	}

	private void initData() {
		System.out.println("프레임 생성");
		setTitle(this.name);
		panel = new JPanel();
		textArea = new JTextArea();
		button = new JButton();
		setSize(500, 500);
		panel.setSize(300,300);
		panel.setLocation(0,0);
		panel.add(button);
		add(panel);

	}

	private void setInitLayout() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		panel.setVisible(true);
		
		
	}

}
