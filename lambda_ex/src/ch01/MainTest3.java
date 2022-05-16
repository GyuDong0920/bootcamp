package ch01;

public class MainTest3 {

	public static void main(String[] args) {
		
	ICalc iCalAdd= (a, b, c) -> {
		return a + b + c;
	};
	
	ICalc iCalMinus = (a, b, c) -> {
		return a - b - c;
	};
	
	// 실행문이 한 문장인 경우 중괄호와 return 키워드를 생략할 수 있
	ICalc iCallMulti = (a, b, c) ->  a * b * c;
	
			
	}	
}