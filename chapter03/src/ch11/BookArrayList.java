package ch11;

import java.util.ArrayList;
import java.util.Iterator;

public class BookArrayList implements BookService {

	private ArrayList<Book> books = new ArrayList<Book>();
	
	/**
	 * Book 객체를 ArrayList 자료 구조에 저장하기
	 */
	@Override
	public void addBook(Book book) {
		System.out.println("저장 합니다.");
		books.add(book);
		showAllBook();
	}
	/**
	 * 책의 타이틀로 책의 존재 여부를 확인
	 * 있다면 매개 변수로 넘어오는 book 객체를
	 * 수정한다.
	 */
	@Override
	public void updateBook(String title, Book book) {
		System.out.println("수정합니다.");
		// 책 타이틀로 인덱스 번호를 찾아야 한다.
		int bookIndex = -1;
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equals(title));{
				bookIndex = i;
			}
		}
		if(bookIndex == -1) {
			System.out.println(title + "이름의 책이 존재하지 않습니다.");
		} else {
			books.set(bookIndex, book);
		}
		showAllBook();
		
	}
	/**
	 * 책 타이틀로 ArrayList에 객체를 삭제 합니다.
	 */

	@Override
	public void deleteBook(String title) {
		System.out.println("삭제합니다.");
		boolean deleteOk = false;
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equals(title)) {
				books.remove(i);
				deleteOk = true;
			}
		}
		if(deleteOk) {
			System.out.println(title + "책을 삭제하였습니다");
		} else {
			System.out.println(title + " 책이 존재 하지 않습니다.");
		}
		showAllBook();
	}
	/**
	 * 책 제목에 해당하는 객체에 정보를 출력 합니다.
	 */

	@Override
	public void selectedByTitleBook(String title) {
		System.out.println("조회 합니다.");
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equals(title)){
				System.out.println(books.get(i));
				return;
			}
		}
		System.out.println(title + " 제목으로 책을 찾을 수 없습니다.");
	}
	/**
	 * 모든 데이터를 출력
	 */
	@Override
	public void showAllBook() {
		System.out.println(">>>>>>> 현재 저장된 데이터 확인 <<<<<<<");
		for (Book book : books) {
			System.out.println(book);
		}
	}
	
	
}
