package ch03;

import java.util.ArrayList;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Student {
	String name;
	int age;
	String address;
	
}

public class MainTest2 {

	public static void main(String[] args) {
		
		Student student1 = new Student("홍길동", 20, "부산");
		Student student2 = new Student("이순신", 33, "서울");
		Student student3 = new Student("세종대왕", 59, "세종시");

		// Object -->  형식이 있는 문자열(JSON)
		Gson gson = new Gson();
		String jsonStr = gson.toJson(student1);
		System.out.println(jsonStr);
		
		// ArrayList<Object> --> jsonArray[Object]
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		System.out.println(list);

		String jsonArratStr = gson.toJson(list);
		System.out.println(jsonArratStr);
	}
}
