package chTest;

import java.util.Scanner;

public class UserInterface {

	private LoginClient loginClient;
	private UserInfo userInfo;
	private Scanner scanner;
	
	public UserInterface() {
		loginClient = new LoginClient();
		userInfo = UserInfo.getInstance();
		scanner = new Scanner(System.in);
	
	}
	
	public void mainMenu() {
		String sc = "";
		do {
			loginClient.mainView();
			
			sc = scanner.nextLine();
			
			if (sc.equals("1")) {
				Login user = userInfo.signIn();
				if(user != null) {
					toUserMenu(user);
				}
			}else if (sc.equals("2")) {
				loginClient.showSignupTitle();
				
				userInfo.signUp();
				
			} else if (sc.equals("3")) {
				loginClient.showselectUserMessage();
				
				userInfo.showUserInfo();
			}
			
		} while (!sc.equals("0"));
		System.out.println("종료 되었습니다.");
	}

	public void toUserMenu(Login loginedUser) {
		userInfo.showMyInfo(loginedUser);
		
		boolean flag = true;
		String sc = "";
		
		while (flag) {
			loginClient.userMain();

			sc = scanner.nextLine();
			
			if(sc.equals("1")) {
				userInfo.setUserInfo(loginedUser);
			} else if(sc.equals("2")) {
				boolean deleteFlag = userInfo.deleteInfo(loginedUser);
				if(deleteFlag == true) {
					flag = false;
				}
			} else if(sc.equals("3")) {
				flag = false;
			} else {
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
		
	}

}
