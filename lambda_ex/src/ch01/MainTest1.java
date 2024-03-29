package ch01;

public class MainTest1 {

	public static void main(String[] args) {
			// 익명 클래스 구현 방법
		IAdd iAdd = new IAdd() {
			
			@Override
			public int add(int x, int y) {
				return x + y;
			}
		};
		
		// 사용하는 방법
		System.out.println(iAdd.add(10, 20));
		
		// 람다식으로 변경하면 ???
		// 타입 추론이 가능하다.
		IAdd iAddLambda = (x, y) -> {return x + y;};	
		
		System.out.println(iAddLambda.add(10, 20));
		
		// 결론
		// 기본 OOP 문법 : 인터페이스를 --> 익명 구현 객체 -->격공
		// 람다 표현식 : 인터페이스를 --> 람다 표현식--> 사용
		
	}
}
