package file_io_ch01;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class MainTest1 {

	public static PrintStream out;
	public static InputStream in;
	
	
	
	public static void main(String[] args) {
		
		// 표준 출력(모니터) 스트림
		// system.out
		System.out.println("표준 출력 스트림(모니터)");
		
		// 표준 입력(키보다) 스트림
		// system.in
	    try {
		int k = System.in.read();
	    System.out.println("K : " + k);
		System.out.println((char)k);
	    } catch(IOException e) {
	    	e.printStackTrace();
	    }
	
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
