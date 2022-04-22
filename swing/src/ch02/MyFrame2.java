package ch02;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import javax.print.attribute.standard.PresentationDirection;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame2 extends JFrame {

	private static final Point NORTH = null;
	BorderLayout borderLayout = new BorderLayout();
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ArrayList<String> titles = new ArrayList<String>();
	ArrayList<String> driections = new ArrayList<String>();
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	

	public MyFrame2() {

		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("어레이리스트로 만들기");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		titles.add("북");
		titles.add("센터");
		titles.add("남");
		titles.add("동");
		titles.add("서");
		driections.add(BorderLayout.NORTH);
		driections.add(BorderLayout.SOUTH);
		driections.add(BorderLayout.EAST);
		driections.add(BorderLayout.WEST);
		driections.add(BorderLayout.CENTER);
		buttons.add(new JButton("1"));
		buttons.add(new JButton("2"));
		buttons.add(new JButton("3"));
		buttons.add(new JButton("4"));
		buttons.add(new JButton("5"));
		
	
		
		
	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(borderLayout);

		for (int i = 0; i < buttons.size(); i++) 
		{
			add(buttons.get(i), driections.get(i));
		}
		
		
	}
	

	public static void main(String[] args) {
		new MyFrame2();
	}
}
