package ch09;

public interface BookDao {
	
	// 저장하는 메서드
	void addBook(Book book);
	// 수정하는 메서드
	void setBook(int index, Book book);
	// 삭제하는 메서드
	void deleteBook(int index);
	// 출력하는 메서드
	void printBook(int index);
	void printBook();
}
