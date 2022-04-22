package ch05;

import java.util.Scanner;

public class UserInfoClient{
	
	public static final String MYSQL = "mysql";
	public static final String ORACLE = "oracle";
	 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserInfo info = new UserInfo();

		// UserInfo 스캐너로 받아서 흐름을 만들어 주세요
		// 사용자에게 userIffo = new 정보 받는다
		System.out.print("id : ");
		info.setUserId(sc.next());
		System.out.print("pw : ");
		info.setPassword(sc.next());
		System.out.print("name : ");
		info.setUserName(sc.next());
	
		// 1. mysql 문자열이면 --> userInfoMySqlDao 동작
		// 2. oracle 문자열이면 --> userInfoOracleDao 동작
		// 3. 단, 다형성을 사용해서 처리 해주세요.
		
		UserInfoDao dao = null;
		System.out.println("mysql 또는 oracle 을 입력해주세요");
		String str = sc.next();
		if(str.equals(MYSQL)) {
			dao = new UserInfomysqlDao();
			
		} else if(str.equals(ORACLE)) {
			dao = new UserInfoOracleDao();
			
		} else {
			System.out.println("잘못된 입력 입니다.");
		}
		
	    if(dao != null) {
	    	dao.insertUserInfo(info);
	    }
	
	
	
	
	
	
	}
}
