package tenco.com.test_01.ch01;

public class MainTest2 {

	public static void main(String[] args) {

	
		Powder powder = new Powder();
		Plastic plastic = new Plastic();
		
		
		
		ThreeDPrinter dPrinter = new ThreeDPrinter();
		dPrinter.setMeterial(plastic);
		
		ThreeDPrinter2 dPrinter2 = new ThreeDPrinter2();	
		dPrinter2.setMaterial(powder);
		
		ThreeDPrinter3 dPrinter3 = new ThreeDPrinter3();
		dPrinter3.setMeterial(plastic);
		
		
		
		
		
		// 모든 클래스에 최상위 클래스는 object 이다.
	
		
		// 1. dp1 재료 꺼내기
		 Plastic getPlastic = dPrinter.getMeterial();
		System.out.println(getPlastic);
		
		// 2. dp2 재료 꺼내기
		Powder getPowder = dPrinter2.getMaterial();
		System.out.println(getPowder);
	
		// 3. dp3 재료 꺼내기
		Plastic tempPlastic = (Plastic)dPrinter3.getMeterial();
		System.out.println(tempPlastic);
		// 형 변환을 해야하는 번거로움이 있다.. --> 코드를 살펴서 확인을 해야 한다.
		
	}
}
