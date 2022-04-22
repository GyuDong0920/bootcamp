package chTest;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInfo implements LoginInterface {

	private static int serialLoginNumber = 0; 
	private static UserInfo instance;
	private ArrayList<Login> logins;
	private LoginClient loginClient;
	private Scanner scanner;
	
	private UserInfo() {
		logins = new ArrayList<Login>();
		scanner = new Scanner(System.in);
		loginClient = new LoginClient();
	}
	
	public static UserInfo getInstance() {
		if(instance == null) {
			instance = new UserInfo();
		}
		return instance;
	}

	private Login createUser(String username, String password, String phoneNumber, String address) {
		return new Login(++serialLoginNumber, username, password, phoneNumber, address);
	}
	
	@Override
	public void signUp() {
		loginClient.showTypeUsernameMessage();
		String username = scanner.nextLine();
		
		loginClient.showTypePasswordMessage();
		String password = scanner.nextLine();
		
		loginClient.showTypePhoneMessage();
		String phoneNumber = scanner.nextLine();
		
		loginClient.showTypeAddressMessage();
		String address = scanner.nextLine();
		
		Login signUpUser = createUser(username, password, phoneNumber, address);
		
		logins.add(signUpUser);
		System.out.println("회원가입이 완료되었습니다");
		System.out.println(signUpUser);
	}
	
	private Login selectUser() {
		System.out.println("아이디 : ");
		String username = scanner.nextLine();
		System.out.println("비밀번호 : ");
		String password = scanner.nextLine();
		
		Login user = null;
		for (int i = 0; i < logins.size(); i++) {
			user = logins.get(i);
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
				return user;
			} else {
				user = null;
			}
		}
		return user;
	}
	
	private Login selectUser(Login loginedUser) {
		System.out.println("아이디 : ");
		String username = scanner.nextLine();
		System.out.println("비밀번호 : ");
		String password = scanner.nextLine();
		
		return loginedUser.checkUserInfo(username, password) ? loginedUser : null;
	}
    
	// 수정
	@Override
	public void setUserInfo(Login loginedUser) {
		Login user = selectUser(loginedUser);
		if(user == null) {
			System.out.println("아이디 혹은 비밀번호가 맞지 않습니다.");
		} else {
			System.out.println(user);
			System.out.println("바꾸실 비밀번호를 입력해주세요");
			String password = scanner.nextLine();
			System.out.println("변경할 주소를 입력해주세요.");
			String address = scanner.nextLine();
			System.out.println("변경할 전화번호를 입력해주세요.");
			String phoneNumber = scanner.nextLine();
			
			user.setPassword(password);
			user.setAddress(address);
			user.setPhoneNumber(phoneNumber);
			
			System.out.println("변경이 완료 되었습니다.");
			System.out.println(user);
		}
	}
	
	// 회원조회
	@Override
	public void showUserInfo() {
		System.out.println("아이디를 조회 합니다.");
		Login user = selectUser();
		
		if(user == null) {
			System.out.println("아이디 혹은 비밀번호가 틀렸습니다");
		} else {
			System.out.println(user);
		}
	}
	
	// 정보 삭제
	@Override
	public boolean deleteInfo(Login loginedUser) {
		Login user = selectUser(loginedUser);
		boolean flag = false;
		if(user == null) {
			System.out.println("아이디 혹은 비밀번호가 맞지 않습니다.");
		} else { 
		   flag = logins.remove(loginedUser);
		}
		return flag;
	}
	
	// 전체 정보 조회
	@Override
	public void showInfoAll() {
		System.out.println("회원 정보 확인");
		 for (Login login : logins) {
			 System.out.println(login);
		}
		
	}
	
	// 로그인
	@Override 
	public Login signIn() {
		Login user = selectUser();
		if(user == null) {
			System.out.println("아이디 혹은 비밀번호가 틀렸습니다");
		} else {
			System.out.println(user.getUsername()+ "님 환영합니다.");
		}
		return user;
	
	}
	
	// 로그인 완료
	@Override
	public void completeLogin(Login login, String id, String pw, String PhoneNum, String ad) {
		 System.out.println("반갑습니다 " + id + "님");
	}
	
	// 내 정보 보기
	@Override
	public void showMyInfo(Login target) {
		for (int i = 0; i < logins.size(); i++) {
			if(target.equals(logins.get(i))) {
				System.out.println(logins.get(i));
				return;
			}
		}
		System.out.println("유저 정보를 찾을 수 없습니다.");
	}
}

