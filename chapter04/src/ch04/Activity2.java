package ch04;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

// 콜리 : 호출자
public class Activity2 extends BaseActivity{

	CallBackCheckPosition callBackCheckPosition;

	public void setCallBackCheckPosition(CallBackCheckPosition callBackCheckPosition) {
		this.callBackCheckPosition = callBackCheckPosition;
	}

	public Activity2(String name) {
		super(name);
	
	}
	
	private void addEventListener() {
		
		this.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int x = e.getX();
			int y = e.getY();
			callBackCheckPosition.checkPosition(x, y);
			
		}
		
		
		});
	}
	

}
