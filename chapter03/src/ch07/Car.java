package ch07;

public class Car {

	private int carNumber;
	private String name;
	
	public Car(int carNumber, String name) {
		super();
		this.carNumber = carNumber;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car [carNumber=" + carNumber + ", name=" + name + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Car) {
			Car tempCar = (Car)obj;
			String name = tempCar.name;
			if(this.name == name) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
}
