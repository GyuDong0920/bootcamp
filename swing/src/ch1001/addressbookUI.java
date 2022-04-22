package ch1001;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class addressbookUI extends JFrame implements MouseListener {

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private GridLayout gridLayout;
	private FlowLayout flowLayout;
	private JPanel jpanel;
	private JTextField textField;

	public addressbookUI() {

		initData();
		setInitLayout();

	}

	private void initData() {

		setTitle("주소록 메인");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button1 = new JButton("주소록 보기");
		button2 = new JButton("추가하기");
		button3 = new JButton("수정하기");
		button4 = new JButton("");
		button5 = new JButton("");
		gridLayout = new GridLayout(1, 4);
		jpanel = new JPanel();

		add(button1);
		add(button2);
		add(button3);
		add(jpanel);

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(flowLayout);
		jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpanel.add(button1);
		jpanel.add(button2);
		jpanel.add(button3);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		button1.addActionListener(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new addressbookUI();

	}

}
