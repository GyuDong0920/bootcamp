package ch11_1;

public interface BookService {

	// 북 객체 저장 기능
	void addBook(Book book);
	// 북 객체 수정
	void updateBook(String title, Book book);
	// 북 객체 삭제
	void deleteBook(String title);
	// 책 한권 출력
	void selectedByTitleBook(String title);
	// 저장 전부 출력
	void showAllBook();

}
