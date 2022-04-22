package file_io_ch04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author ITPS
 * Bufferdwriter 를 이용한 파일에 문자 쓰기.
 * 
 */

public class MainTest2 {

	public static void main(String[] args) {
		
		
			String text = "File Writer Test";
			String fileName = "result.txt";
			// 버퍼는 자기 공간이 다 채워 지면 자동으로 전달 한다.
			
			try {
				
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
				bw.write(text);
				bw.flush();
				
				bw.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		
		
	}

}
