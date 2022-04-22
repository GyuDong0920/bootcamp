package ch04;

public class ToyRobot implements RemoteController{

	String name;
	
	public ToyRobot() {
		this.name = " 건담 ";
	}
	
	@Override
	public void turnOn() {
		System.out.println("로봇을 켭니다");
		
	}

	@Override
	public void turnOff() {
		System.out.println("로봇을 끕니다");
		
	}

}
