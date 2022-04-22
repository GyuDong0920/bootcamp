package ch01;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


// 호출자(콜리) : 멤버 변수로 징검다리 역할을 하는 인터페이스를 멤버 변수로 먼저 선언한다.
public class SubActivity extends JFrame {

	
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	// 마이너스 버튼 + 1 
	// 값을 전달 하는 버튼 +1
	int result = 999;
	
	
	
	IcallBackBtnAction icallBackBtnAction;
	
	
	// 호출자(콜리) : 콜백 받는 객체의 주소값을 알고 있어야 메서드가 호출 되었다고 알릴 수 있다.
	public SubActivity(IcallBackBtnAction icallBackBtnAction) {
		
		this.icallBackBtnAction = icallBackBtnAction;
		
		setSize(300, 300);
		setLayout(new GridLayout(4, 1));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button1 = new JButton("더하기 버튼 + ");
		button2 = new JButton("마이너스 버튼 - ");
		button3 = new JButton("100 더하기 버튼 + ");
		button3 = new JButton(" 값 전달 버튼 ");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("더하기 버튼 클릭 !!!");
				// 하지만 (new X)
				icallBackBtnAction.clickedAddBtn();
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("마이너스 버튼 클릭 !!!");
				icallBackBtnAction.clickedminusBtn();
				
			}
		});
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(" 100 + 버튼 클릭 !!!");
				icallBackBtnAction.clicked100Btn();
			}
		});
		button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				icallBackBtnAction.passValue(result);
				
			}
		});
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
	}


}
