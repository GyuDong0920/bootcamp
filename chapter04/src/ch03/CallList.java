package ch03;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// 콜백 받는 객체
public class CallList implements CallBook {
	
	private String printDate() {
		DateFormat dateFormat = new SimpleDateFormat("a H:mm");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	
	@Override
	public void printList(String list) {
		System.out.println("********* Call List **********");
		System.out.println();
		System.out.println("      "+ list);
		System.out.println();
		System.out.println("전화 온 시간 : " + printDate());
		System.out.println("******************************");
		
		
	}

}
