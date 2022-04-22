package ch1000;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChange extends JFrame implements ActionListener {

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	private GridLayout gridLayout;

	public ColorChange() {
		initData();
		setInitLayout();
		addEventListener();

	}

	public void initData() {
		setTitle("도전 실습 버튼 과제");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1 = new JButton("노란색");
		button2 = new JButton("빨간색");
		button3 = new JButton("파란색");
		button4 = new JButton("흰색");
		add(jp1);
		add(jp2);
		jp1.add(button1);
		jp1.add(button2);
		jp2.add(button3);
		jp2.add(button4);
		gridLayout = new GridLayout(2, 2);

	}

	public void setInitLayout() {
		setVisible(true);
		setLayout(gridLayout);

	}

	public void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			jp1.setBackground(Color.YELLOW);
		} else if (e.getSource() == button2) {
			jp1.setBackground(Color.red);
		} else if (e.getSource() == button3) {
			jp2.setBackground(Color.blue);
		} else if (e.getSource() == button4) {
			jp2.setBackground(Color.white);
		}

	}

	public static void main(String[] args) {
		new ColorChange();
	}

}
