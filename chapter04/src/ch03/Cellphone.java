package ch03;

// 호출자
public class Cellphone {
	
	String list;
	CallBook onCallBook;
	
	// 주소값 연결
	public Cellphone(String list, CallBook onCallBook) {
		this.list = list;
		this.onCallBook = onCallBook;
	}

	public void complete() {
		onCallBook.printList(list);
	}



}
