package ch04;

import java.awt.Color;

public class Activity1 extends BaseActivity {

	// 변수, 초기화
	CallBackCheckPosition callback = new CallBackCheckPosition() {
		
		@Override
		public void checkPosition(int x, int y) {
			
			System.out.println(name + " 가 콜백을 받았습니다." + x);
			System.out.println(name + " 가 콜백을 받았습니다." + y);
			
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
	protected void SetInitLayout() {
	super.SetInitLayout();
	Panel.setBackground(Color.blue);

	}
}
