package ch02;

public class MainTest2 {

	public static void main(String[] args) {

		IMaxNumber iMaxNumber = (x, y) -> x > y ? x : y;
		System.out.println(iMaxNumber.getMax(100, 1000));
		
	}
}
