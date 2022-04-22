package ch09;

import java.util.ArrayList;

public class BookDaoMySql implements BookDao{

	// 인터페이스를 활용해서 기능을 구현하세요.
	// ArrayList 사용
	static ArrayList<Book> books = new ArrayList<>();
	
	public void showbooks() {
		System.out.println(books);
	}
	
	@Override
	public void addBook(Book book) {
		books.add(book);
		System.out.println(book + "책이 생성되었습니다.");
	}


	@Override
	public void printBook(int index) {
		System.out.println(books.get(index));
	}
	
	@Override
	public void printBook() {
		System.out.println(books);
	}



	@Override
	public void setBook(int index, Book book) {
		books.set(index, book);
		System.out.println(books);
		System.out.println("정보수정이 완료되었습니다."); 
	}


	@Override
	public void deleteBook(int index) {
		books.remove(index);
		System.err.println(books + "정보가 삭제되었습니다");
		
	}




}
