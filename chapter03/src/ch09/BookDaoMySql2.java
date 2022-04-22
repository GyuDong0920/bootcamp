package ch09;

import java.util.ArrayList;

public abstract class BookDaoMySql2 implements BookDao{

	// 인터페이스를 활용해서 기능을 구현하세요.
	// ArrayList 사용
	ArrayList<Book> books = new ArrayList<>();
	

	@Override
	public void addBook(Book book) {
		books.add(book);
	}


	@Override
	public void printBook(int index) {
		System.out.println(books.get(index));
	}


	@Override
	public void setBook(int index, Book book) {
		books.set(index, book);
		
	}


	@Override
	public void deleteBook(int index) {
		books.remove(index);
		
	}




}
