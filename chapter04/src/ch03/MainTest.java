package ch03;

public class MainTest {
	
	public static void main(String[] args) {
		

	CallList callList = new CallList();
	
	Cellphone cellphone = new Cellphone("친구에게 전화옴", callList);
	
	cellphone.complete();
	
	}
}
