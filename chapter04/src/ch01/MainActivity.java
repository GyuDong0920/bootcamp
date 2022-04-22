package ch01;

import java.awt.FlowLayout;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JLabel;

// 콜백 받는 객체 : IcallbackBtnAciton 인터페이를 구현해서 정의 하면 된다.
public class MainActivity extends JFrame implements IcallBackBtnAction {

	int count;
	JLabel label;



	public MainActivity() {

		
		count = 0;
		label = new JLabel("count : " + count);
		setSize(300, 300);
		setLayout(new FlowLayout());
		setVisible(true);
		add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new SubActivity(this);
	}

	@Override
	public void clickedAddBtn() {
		System.out.println(" + 버튼에 콜백 받았습니다.");
		count++;
		label.setText("count : " + count);
	}

	@Override
	public void clickedminusBtn() {
		System.out.println(" - 버튼에 콜백 받았습니다.");
		count--;
		label.setText("count : " + count);
	}

	@Override
	public void clicked100Btn() {
		System.out.println("100이 추가되었습니다.");
		count += 100;
		label.setText("count : " + count);
	}

	@Override
	public void passValue(int result) {
		label.setText("전달 받은 값 : " + result);
	}


	// - 버튼에 동작을 콜백 메서드 정의

	// - 값을 전달 받는 콜백 메서드 정의

}
