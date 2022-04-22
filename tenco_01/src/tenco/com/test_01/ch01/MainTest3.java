package tenco.com.test_01.ch01;

public class MainTest3 {
 
	public static void main(String[] args) {
		
		// 재료
		Powder powder = new Powder();
		Plastic plastic = new Plastic();
		
		// 사용하는 시점에 지정된 자료형으로 컴파일 됩니다.
		GenericPrinter<Powder> genericPrinter = new GenericPrinter<>();
		
		genericPrinter.setMaterial(powder);
		System.out.println(genericPrinter.getMaterial());
	
		GenericPrinter<Plastic> genericPrinter2 = new GenericPrinter<Plastic>();
		
		genericPrinter2.setMaterial(plastic);
		
		System.out.println(genericPrinter2.getMaterial());
	
		GenericPrinter<Water> genericPrinter3 = new GenericPrinter<Water>();
		genericPrinter3.setMaterial(genericPrinter3.getMaterial());
		
		GenericPrinter2<Powder> g2 = new GenericPrinter2<>();
		g2.setMaterial(powder);
		System.out.println(g2);
		
		GenericPrinter3<Plastic> genericPrinter4 = new GenericPrinter3<>();
		genericPrinter4.setMaterial(plastic);
		System.out.println(genericPrinter4);
	
		// <T extends 클래스> 사용하기
		
		// 상위 클래스의 필요성
		// T 자료형의 범위를 제한할 수 있다(제한 하지 않으면 아무 클래스나 올 수 있다)
		
	
	
	
	
	}
}
