package ch02;

public class MainTest {

	public static void main(String[] args) {

		MemberInfoDao memberInfoDao = new MemberInfoDao();
		
//		ArrayList<MemberDto> data = memberInfoDao.select();
//		System.out.println(data);
		
		// object -->
		MemberDto dto = new MemberDto("abc", "강감찬", "서울시 동작구");
		memberInfoDao.update(dto);
//		memberInfoDao.insert(dto);
		
	}

}
