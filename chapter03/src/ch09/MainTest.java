package ch09;

public class MainTest {

	public static void main(String[] args) {
		BookDaoMySql bookDaoMySql = new BookDaoMySql();
		// 테스트 
		BookClient bookClient = new BookClient();
		bookClient.bookInsert();
		System.out.println(bookDaoMySql.books);
		bookClient.bookUpdate();
//		bookClient.bookUpdate();
//		bookClient.bookDelete();
//		bookClient.bookPrint();
		

	}

}
         