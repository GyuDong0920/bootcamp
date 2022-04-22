package file_io_ch02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * 입력 스트림 
 * 파일에서 한 바이트씩 데이터를 읽기 - 1
 *
 * 
 */
public class MainTest1 {

	public static void main(String[] args) {

		FileInputStream fis = null;
		
		
		try {
			fis = new FileInputStream("boot_a.txt");
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		} catch (IOException e) {
			System.out.println("입출력 오류");
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
}
