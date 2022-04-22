package ch03;

public class Refrigerator extends HomeAppliances implements RemoteController, SoundEffect {

	@Override
	public void turnOn() {
		System.out.println("냉장고 온");
		
	}

	@Override
	public void turnOff() {
		System.out.println("냉장고 오프");
	}

	@Override
	public void notification() {
		System.out.println("띠리링~~~~~");
		
	}

	

	

}
