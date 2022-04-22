package data_structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class DataStructureTest3 {
	
	public static void main(String[] args) {
		
		Set set;
		
		HashSet<Integer> set1 = new HashSet<>();
		set1.add(1);
		set1.add(1);
		set1.add(2);
		set1.add(2);
		set1.add(3);
		set1.add(3);
		set1.add(4);
		
		
		System.out.println(set1.size());
		//값을 삭제하는 방법
		set1.remove(1);
//		set1.clear();
//		System.out.println(set1);
		
//		while
		Iterator<Integer> iter = set1.iterator();
		while(iter.hasNext()) {
//			System.out.println("값 확인" + iter.next());
		}
		
		System.out.println("==========================");
		
		HashSet<Integer> set2 = new HashSet<>();
		set2.add(getRandom());
		set2.add(getRandom());
		set2.add(getRandom());
		set2.add(getRandom());
		set2.add(getRandom());
		set2.add(getRandom());
//		System.out.println(set2);
//		System.out.println(set2.size());
		while(true) {
			System.out.println();
		}
		
		
		
		
		// 무조건 6개의 번호가 저장된 set2를 완성하시오 ~
		
	} // end of main
	
	public static int getRandom() {
		Random rd = new Random();
		int value = rd.nextInt(45) + 1;
		return value;
	}
	
	
}
