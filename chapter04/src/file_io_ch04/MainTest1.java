package file_io_ch04;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author ITPS 문자 단위 출력 스트림 파일에 문자 쓰기 - 1
 * 
 */

public class MainTest1 {

	public static void main(String[] args) {

		try (FileWriter fw = new FileWriter("write_1.txt")) {
			
			fw.write('A'); // 문자 하나 출력
			
			char buf[] = {'B', 'C', 'D', 'E', 'F', 'G'};
			
			fw.write(buf);
			
			fw.write("\n안녕하세요 ~ 가나다라마"); // String
			
			fw.write(buf, 1 ,2);
			
			fw.write("/");
			
			fw.write(65);
			
			
			
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
