package file_io_ch02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * 입력 스트림 파일에서 한 바이트씩 데이터를 읽기 - 1 
 * try-with-resources : try(...) 오토 클로즈 제공
 * 
 */
public class MainTest2 {

	public static void main(String[] args) {

		System.out.println("시작 파일에서 데이터를 읽어서 화면에 출력");

//	FileInputStream fis = null;
//	fis = new FileInputStream("boot_a.txt");
		int i;

		try (FileInputStream fis = new FileInputStream("boot_a.txt")) {

			while ((i = fis.read()) != -1) {
				System.out.println((char) i);
			}

		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		} catch (IOException e) {
			System.out.println("입출력 오류");
		}
//	try {
//		int i;
//		fis = new FileInputStream("boot_a.txt");
//	while((i = fis.read()) != -1 ) {
//		System.out.println((char)i);
//	}
//		
//	} catch (FileNotFoundException e) {
//		System.out.println("파일이 없습니다.");
//	} catch (IOException e) {
//		System.out.println("입출력 오류");
//	} finally {
//		try {
//			fis.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

		System.out.println("끝");

	}

}
