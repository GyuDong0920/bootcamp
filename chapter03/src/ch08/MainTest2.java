package ch08;

import java.util.ArrayList;

public class MainTest2 {
public static void main(String[] args) {
	

	Book book1 = new Book(1,"흐르는강물처럼","파울로코엘료");
	Book book2 = new Book(2,"플러터UI실전","김근호");
	Book book3 = new Book(3,"무궁화꽃이피었습니다","김진명");
	Book book4 = new Book(4,"리딩으로리드하라","이지성");
	Book book5 = new Book(5,"사피엔스","유발하라리");
	
	
	ArrayList<Book> cart = new ArrayList<>();

	cart.add(book1);
	cart.add(book2);
	cart.add(book3);
	
	for (Book book : cart) {
		System.out.println(book);
	}
	
	cart.remove(0);
	
	System.out.println(cart.size());
	System.out.println("-----------");
	System.out.println(cart.get(0));
	
	cart.set(0, book5);
	System.out.println(cart.size());












} // end of main
} // end of class