package ch11;

public class BookClient {
	
	public static int serialBookNumber = 0;
	// 북 객체 생성
	public Book createBook(String title, String author) {
		serialBookNumber++;
		return new Book(serialBookNumber, title, author);
	}
}
