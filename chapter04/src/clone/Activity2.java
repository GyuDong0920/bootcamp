package clone;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Activity2 extends BaseActivity {

	CallBackCheckPosition callBackCheckPosition;
	
	public Activity2(String name) {
		super(name);
		
	}
	public void setCallBackCheckPosition(CallBackCheckPosition callBackCheckPosition) {
		this.callBackCheckPosition = callBackCheckPosition;
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
