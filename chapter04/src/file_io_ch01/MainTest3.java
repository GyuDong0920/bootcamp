package file_io_ch01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 
 * @author ITPS 
 * 입력 스트림 
 * 바이트 단위 스트림 
 * 파일에서 바이트를 배열 단위로 읽기
 *
 *
 */

public class MainTest3 {

	public static void main(String[] args) {

		
		try(FileInputStream fis = new FileInputStream("boot_b.txt")) {
			byte[] bs = new byte[10];
			int i;
//			while((i = fis.read(bs)) != -1) {
//				for(int j = 0; j < i; j++) {
//				
//					System.out.println(" i : " + i);
//					System.out.println(" j : " + j);
//					System.out.println((char)bs[j]);
//				}
			
			while((i = fis.read(bs, 1, 9)) != -1) {
				for(int j = 0; j < i; j++) {
				
//					System.out.println(" i : " + i);
//					System.out.println(" j : " + j);
					System.out.println((char)bs[j]);
				}
				
				 System.out.println(" i : " + i + "바이트 읽음");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
