package ch09;

import java.util.ArrayList;
import java.util.Scanner;

public class BookClient {

	Book book1 = new Book("파울로코엘료", "흐르는강물처럼", 1);
	Book book2 = new Book("김근호", "플러터실전", 2);
	Book book3 = new Book("김진명", "무궁화꽃이피었습니다", 3);
	Book book4 = new Book("이지성", "리딩으로리드하라", 4);
	Book book5 = new Book("유발하라리", "사피엔스", 5);
	Scanner scanner = new Scanner(System.in);
	
	public void bookInsert() {
		boolean flag = true;
		int userInput = 0;
		BookDaoMySql bookDaoMySql = new BookDaoMySql(); 
		// 생성 
		while (flag) {
				System.out.println("책을 생성해주세요 1~5");
			    userInput = scanner.nextInt();
			if (userInput == 1) {
				System.out.println("넣기 완료" + userInput);
				bookDaoMySql.addBook(book1);
			} else if (userInput == 2) {
				System.out.println("넣기 완료" + userInput);
				bookDaoMySql.addBook(book2);
			} else if (userInput == 3) {
				System.out.println("넣기 완료" + userInput);
				bookDaoMySql.addBook(book3);
			} else if (userInput == 4 ) {
				System.out.println("넣기 완료" + userInput);
				bookDaoMySql.addBook(book4);
			} else if (userInput == 5 ) {
				System.out.println("넣기 완료" + userInput);
				bookDaoMySql.addBook(book5);
			} else {
				System.out.println("책 생성 종료" + userInput);
				flag = false;
			}
		}
	}
	
	public Book selectBook() {
		Book selectbook = null;
		boolean flag = true;
		System.out.println("수정할 책을 골라주세요 1~5");
		int num = scanner.nextInt();
		scanner.nextLine();
		while(flag) {
			if (num == 1) {
				selectbook = book1;
				flag =false;
			} else if (num == 2) {
				selectbook = book2;
				flag =false;
			} else if (num == 3) {
				selectbook = book3;
				flag =false;
			} else if (num == 4 ) {
				selectbook = book4;
				flag =false;
			} else if (num == 5 ) {
				selectbook = book5;
				flag =false;
			} else {
				System.out.println("잘못입력하셨습니다.");
			
			}
		}System.out.println(selectbook +"책을 선택하셨습니다.");
		return selectbook;
		
	}
	
	public void bookUpdate() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("몇번자리의 책을 수정할까요?1~5");
		int userInput = scanner.nextInt();
		Book book;
		BookDaoMySql bookDaoMySql = new BookDaoMySql(); 
		
		if (userInput  >= 1 && userInput <= 5) {
			book = selectBook();
		}else {
			return;
		}
		bookDaoMySql.setBook(userInput - 1, book);
		System.out.println(book);
	}
	
	public void bookDelete() {
		Scanner scanner = new Scanner(System.in);
		int userInput = scanner.nextInt();
		scanner.nextLine();
		BookDaoMySql bookDaoMySql = new BookDaoMySql(); 
		
		if (userInput == 1) {
			bookDaoMySql.deleteBook(1);
			System.out.println();
		} else if (userInput == 2) {
			bookDaoMySql.deleteBook(2);
		} else if (userInput == 3) {
			bookDaoMySql.deleteBook(3);
		} else if (userInput == 4 ) {
			bookDaoMySql.deleteBook(4);
		} else if (userInput == 5 ) {
			bookDaoMySql.deleteBook(5);
		} else {
			return;
		}
	}
	public void bookPrint() {
		Scanner scanner = new Scanner(System.in);
		int userInput = scanner.nextInt();
		scanner.nextLine();
		BookDaoMySql bookDaoMySql = new BookDaoMySql();
	
		if (userInput == 1) {
			bookDaoMySql.printBook(1);
		} else if (userInput == 2) {
			bookDaoMySql.printBook(2);
		} else if (userInput == 3) {
			bookDaoMySql.printBook(3);
		} else if (userInput == 4 ) {
			bookDaoMySql.printBook(4);
		} else if (userInput == 5 ) {
			bookDaoMySql.printBook(5);
		} else {
			return;
		}
	
	
	
	
	
	
	
	}
		
	
	
	
} // end of class