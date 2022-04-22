package ch03;

public class ToyRobot implements RemoteController, SoundEffect {

	String name;

	public ToyRobot() {
		this.name = " 건담 로봇 ";
	}

	@Override
	public void turnOn() {
		System.out.println("로봇 온");
	}

	@Override
	public void turnOff() {
		System.out.println("로봇 오프");
	}

	@Override
	public void notification() {
		System.out.println("띠리링~~~~~~");
		
	}

}
