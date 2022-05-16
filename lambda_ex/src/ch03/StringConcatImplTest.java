package ch03;

public class StringConcatImplTest {

	public static void main(String[] args) {
		
		String s1 = "hello";
		String s2 = "java";
		
		// 우리가 알고 있던 OOP 방식
		StringConcatImpl impl = new StringConcatImpl();
		impl.makeString(s1, s2);
		
		IStringConcat iStringConcat = new IStringConcat() {

			@Override
			public void makeString(String s1, String s2) {
				System.out.println("[[[" + s1 + " : " + s2 + "]]]");
			}	
		
		};
		// 클래스 설계없이 바로 사용 가능
		// 람다 표현식으로 설계해서 사용해 주세요.
	
		IStringConcat iStringConcat2 = (s9, s10) -> System.out.println("..." + s9 + ", " + s10 + "...");
		iStringConcat2.makeString("ㅎㅇ", "람다");
	
		// 쓰레드를 람다 표현식으로
		new Thread(() -> {});
	}

}
