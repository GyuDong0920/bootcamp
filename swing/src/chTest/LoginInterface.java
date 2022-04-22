package chTest;

public interface LoginInterface {

	
	// 회원 가입
	void signUp();
	
	// 회원 정보 수정
	void setUserInfo(Login loginedUser);
	
	// 회원 정보 조회
	void showUserInfo();
	
	// 회원 탈퇴
	boolean deleteInfo(Login loginedUser);
	
	// 모든 회원 정보 출력
	void showInfoAll();
	
	// 로그인 시도
	Login signIn();
	
	// 내 정보 수정 및 조회
	void completeLogin(Login login, String username, String password, String phoneNumber ,String address);
	
	// 내 정보 확인
	void showMyInfo(Login user);
}
