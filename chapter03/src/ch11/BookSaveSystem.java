package ch11;

import java.util.Scanner;

public class BookSaveSystem {

	public static void main(String[] args) {

		
		
		System.out.println(removeBlankString("공 백 제거 확인~~~~"));
		// 스캐너
		BookClient bookClient = new BookClient();
		BookArrayList bookarrayList = new BookArrayList();
		Scanner scanner = new Scanner(System.in);

		// do while

		String selectedMenu = "";
		do {
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("1. 책 생성 2. 책 조회 3. 책 삭제 4.  책 전체 조회 5. 책 수정 0. 프로그램 종료");
			System.out.println("-----------------------------------------------------------------------------");
			selectedMenu = scanner.nextLine();
			if (selectedMenu.equals("0")) {
				System.out.println("프로그램을 종료 합니다.");
				scanner.close();
			} else if (selectedMenu.equals("1")) {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("책 제목을 입력 합니다.");
				String title = scanner.nextLine();
				System.out.println("작가의 이름을 입력 하세요");
				String author = scanner.nextLine();
				Book book = bookClient.createBook(title, author);
				bookarrayList.addBook(book);
			} else if (selectedMenu.equals("2")) {
				System.out.println(" 책 제목을 입력해주세요");
				String title = scanner.nextLine();
				bookarrayList.selectedByTitleBook(title);
			} else if (selectedMenu.equals("3")) {
				System.out.println(" 삭제하려는 책 제목을 입력해주세요");
				String title = scanner.nextLine();
				bookarrayList.deleteBook(title);
			} else if (selectedMenu.equals("4")) {
				System.out.println("저장 되어 있는 책 목록 조회");
				bookarrayList.showAllBook();
			} else if (selectedMenu.equals("5")) {
				System.out.println("수정하려는 책 제목을 입력해주세요");
			 	String savedtitle = scanner.nextLine();
				System.out.println("새로운 책 제목을 입력 하세요");
			    String title = scanner.nextLine();
			    System.out.println("새로운 작가 이름을 입력 하세요");
			    String author = scanner.nextLine();
			    Book book = bookClient.createBook(title, author);
				bookarrayList.updateBook(savedtitle, book);
			}else {
				System.out.println("잘못입력하였습니다");
				}
		} while (!selectedMenu.equals("0"));
	} //end of main
	public static String removeBlankString(String str) {
		String result1 = str.trim();
		String result2 = result1.replace(" ", "");
		return result2;
	}
} // end of class
