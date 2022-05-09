package ch03;

import java.util.ArrayList;

public interface IShowdbDao {
	
	// 이순신, 김유신 (전화번호 , 주소)
	ArrayList<BuyDto> innerjoin1(String userName1, String userName2);
	
	void leftjoin1(userDto dto);
	
	
	// 가장 비싼 구매 상품만 출력 !
	BuyDto buyInfoByUserName(String userName);

}
