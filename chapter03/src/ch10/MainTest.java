package ch10;

import ch09.Book;

public class MainTest {

	public static void main(String[] args) {
		
		BookClient bookClient = new BookClient();
		// 기능이 완성 되었다면
		bookClient.createBookobj();
		
		Book book = bookClient.createBookobj();
		
		bookClient.deleteBook("홍길동전");

		
		BookDaoMySql bookDaoMySql = new BookDaoMySql();
		bookDaoMySql.addBook(book);
	}

}
