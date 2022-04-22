package ch04;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventListener2 extends JFrame implements ActionListener{

	private JButton button1;
	private JButton button2;

	public EventListener2() {
		iniData();
		setInitLayout();
		addEventListener();
	}

	private void iniData() {
		setTitle("이벤트 리스너 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		button1 = new JButton("botton1");
		button2 = new JButton("botton2");
		
	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,20 ,20));
		add(button1);
		add(button2);

	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		
//		// 첫번째 방법
//		
//		System.out.println(e.getActionCommand()+"이 클릭 되었습니다.");
//		if(e.getActionCommand().equals(button1.getText())) {
//			System.out.println("버튼 1이 클릭되었습니다.");
//		} else {
//			System.out.println("버튼 2가 클릭되었습니다.");
//		}
		
		// 두번째 방법
//		Object obj = e.getSource();
		// 하지만 우리는 object --> Jbutton (파악가능)
//		JButton obj = (JButton) e.getSource();
//		if(selectedBtn.getText().equals(this.button1.getText())) {
//			System.out.println(button1.getText()+ " 가 클릭되었습니다.");
//		} else { 
//			System.out.println(button2.getText()+ "가 클릭되었습니다.");
//		}
		
		
		
		// 문제 1
		// 버튼 1이 눌러졌느지 버튼2가 눌러졌는지 구분해서 화면에 출력하세요
	}
}
