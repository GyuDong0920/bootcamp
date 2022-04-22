package ch11_1;

import java.util.ArrayList;

public class BookArrayList implements BookService {

	static int i = 0;
	Book[] books = new Book[10];

	@Override
    public void addBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if(books[i] == null) {
                books[i] = book;
                showAllBook();
              return;
            }
        }
	}
	@Override
	public void updateBook(String title, Book book) {
		System.out.println("수정합니다.");
		int bookIndex = -1;
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				continue;
			} else if(books[i].getTitle().equals(title)) {
				books[i] = book;
				showAllBook();
                return;
                
			}
		}
	}

	@Override
	public void deleteBook(String title) {
		System.out.println("삭제합니다.");
		for (int i = 0; i < books.length; i++) {
			if(books[i] == null) {
				continue;
			} else if(books[-i].getTitle().equals(title));
			books[i] = null;
			if(null == null) {
				
			}
			showAllBook();
			return;
		}
			System.out.println(title + "책을 삭제하였습니다.");
	
	}

	@Override
	public void selectedByTitleBook(String title) {
		System.out.println("조회 합니다.");
		for (int i = 0; i < books.length; i++) {
			if(books[i] == null) {
				continue;
			} else if(books[i].getTitle().equals(title)) {
				System.out.println(books[i]);
				return;
			}

		}
		System.out.println(title + " 라는 책이 존재하지않습니다.");
	}

	@Override
	public void showAllBook() {
		System.out.println(">>>>>>> 현재 저장된 데이터 확인 <<<<<<<");
		for (int i = 0; i < books.length; i++) {
			if(books[i] == null) {
				continue;
		} else {
			System.out.println(books[i]);
		}
			
		}
	}
}
