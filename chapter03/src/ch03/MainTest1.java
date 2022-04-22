package ch03;

public class MainTest1 {

	public static void main(String[] args) {
		
		Televison televison = new Televison();
		Refrigerator refrigerator = new Refrigerator();
		ToyRobot robot = new ToyRobot();
		
		televison.turnOn();
		refrigerator.turnOn();
		robot.turnOn();
		System.out.println("-------------");
		televison.turnOff();
		refrigerator.turnOff();
		robot.turnOff();
		// 다형성 
		
		RemoteController[] remoteControllers = new RemoteController[3];
		remoteControllers[0] = televison;
		remoteControllers[1] = refrigerator;
		remoteControllers[2] = robot;
		
		for (int i = 0; i < remoteControllers.length; i++) {
			remoteControllers[i].turnOn();
		} 
		System.out.println("----------------");
		for (int i = 0; i < remoteControllers.length; i++) {
			remoteControllers[i].turnOff();
		}
		
	
	}

}
