package clone;

import java.awt.Color;


public class Activity1 extends BaseActivity{

	
	CallBackCheckPosition callback = new CallBackCheckPosition() {
		
		@Override
		public void checkPosition(int x, int y) {
			System.out.println(name + "가 콜백을 받았습니다." + x);
			System.out.println(name + "가 콜백을 받았습니다." + y);
			
			
		}
	};
	
	
	
	public Activity1(String name) {
		super(name);
		
	}

	@Override
	protected void initData() {
		super.initData();


	}
	
	@Override
	protected void setInitLayout() {
	super.setInitLayout();
	panel.setBackground(Color.blue);

	}

}
