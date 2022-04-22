package ch09;

public class Book {
	
	private String author;
	private String title;
	private int bookNumber;
	

	public Book(String author, String title, int bookNumber) {
		this.author = author;
		this.title = title;
		this.bookNumber = bookNumber;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
	@Override
	public String toString() {
		return "Book [저자=" + author + ", 제목=" + title + ", 책 번호=" + bookNumber + "]";
	}
	
	

}
