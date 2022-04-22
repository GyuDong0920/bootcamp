package straem_ex;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.attribute.AclEntry;

public class StreamEx02 {

	public static void main(String[] args) {
		
	
	
	InputStream in = System.in; // 키보드에 연결
	InputStreamReader ir = new InputStreamReader(in); // 데코레이터 방식 점진적으로 추가하는 // 65 --> A 부호화, 추가적 기능 .....
	// ir 단점 !!! 
	
	
	try {
		char[] data = new char[3];
		ir.read(data);
		
		// char[1000]
		// A -> 999 개 공간 낭비
		// 잘 사용하지 않음 (특히 통신) ---> 해결방안 buffer
		System.out.println(data);
	} catch (Exception e) {
		System.out.println("예외 : " + e.getMessage());
	}
}
}
