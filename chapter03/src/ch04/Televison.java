package ch04;

public class Televison extends HomeAppliances implements RemoteController {

	@Override
	public void turnOn() {
		System.out.println("티비를 켭니다");
		
	}

	@Override
	public void turnOff() {
		System.out.println("티비를 끕니다");
		
	}

}