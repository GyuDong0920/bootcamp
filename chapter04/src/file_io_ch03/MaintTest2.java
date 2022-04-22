package file_io_ch03;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 
 * @author ITPS
 *  바이트 단위 출력
 *  byte[] 배열 A-Z까지 넣고
 *  한꺼번에 파일에 쓰기-2
 */
// D - X 까지만 파일에 쓰기
public class MaintTest2 {

	public static void main(String[] args) throws FileNotFoundException {
		
		FileOutputStream fos = new FileOutputStream("output_b.txt", true);
		// FileoutputStream 은 파일이 없다면 자동으로 파일을 생성
	
	try(fos) {
		
		byte[] bs = new byte[26];
				
		byte data = 65;
				
		for(int i = 0; i < bs.length; i ++) {
					
			bs[i] = data;
				
			data++;
				}
		fos.write(bs,3,21);
	}catch (Exception e) {
		e.printStackTrace();
	}
		
		System.out.println("출력 처리 끝");
	}
	
}
