package ch07;

public class MainTest {
	public static void main(String[] args) {
		
		Car car1 = new Car(1,"BMW 5");
		Car car2 = new Car(2,"BMW 1");
		Car car3 = new Car(3,"BMW 2");
		Car car4 = new Car(4,"BMW 3");
		Car car5 = new Car(5,"BMW 5");
		Driver driver = new Driver();
		
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);
		System.out.println(car4);
		System.out.println(car5);
	
		if(car1.equals(driver)) {
			System.out.println("True 리턴");
		} else {
			System.out.println("false 리턴");
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
}

