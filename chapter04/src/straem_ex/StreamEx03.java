package straem_ex;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.attribute.AclEntry;

public class StreamEx03 {

	public static void main(String[] args) {
		
	
	
	InputStream in = System.in; // 키보드에 연결
	InputStreamReader ir = new InputStreamReader(in); // 데코레이터 방식 점진적으로 추가하는 // 65 --> A 부호화, 추가적 기능 .....
	BufferedReader br = new BufferedReader(ir); // String 으로 받을 수 있다.
	// 통신에는 항상 인코딩 디코딩이 들어간다 (버퍼가 달려있다)
	                                   
	
	
	
	
	
	// ir 단점 !!! 
	try {
//		"HEL"+"LLO";
		String data = br.readLine();
		System.out.println(data);
		
	} catch (Exception e) {
		System.out.println("예외 : " + e.getMessage());
	}
}
}
