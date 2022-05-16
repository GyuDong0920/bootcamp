package ch01;

public class MainTest2 {

	public static void main(String[] args) {
		
		ICalc iCalcAdd = new ICalc() {

			@Override
			public double cala(int a, int b, int c) {
				return a + b +c;
			}
		};
		ICalc ICalMinus = new ICalc() {
			
			@Override
			public double cala(int a, int b, int c) {
				return a - b- c;
			}
		};
		
		ICalc iCalMulti = new ICalc() {
			
			@Override
			public double cala(int a, int b, int c) {
				return a * b * c;
			}
		};
		

	
	
		}	
	}	
