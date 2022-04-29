//package ch03;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import lombok.AllArgsConstructor;
//@AllArgsConstructor
//public class MainTest5 {
//
//	public static void main(String[] args) {
//		Gson gson = new Gson();
//		Student[] students = new Student[3];
//		Student student1 = new Student("홍길동", 20, "부산");
//		Student student2 = new Student("이순신", 33, "서울");
//		Student student3 = new Student("세종대왕", 59, "세종시");
//
//		students[0] = student1;
//		students[1] = student2;
//		students[2] = student3;
//		
//		String studentArr = gson.toJson(students);
//		System.out.println(studentArr);
//		// Object --> 형식이 있는 문자열(JSON)
//		
//		String jsonStrArr = gson.toJson(student1);
////		System.out.println(jsonStr);
//
//		// ArrayList<Object> --> jsonArray[Object]
//		ArrayList<Student> list = new ArrayList<Student>();
//		list.add(student1);
//		list.add(student2);
//		list.add(student3);
////		System.out.println(list);
//		
//		// list --> String
//		String jsonArratStr = gson.toJson(list);
//		System.out.println(jsonArratStr);
//
////		System.out.println("=======================");
//		// 자바에서 사용하는 Object ----> 문자열로 변환(JSON) :
//		// 서버측에 데이터를 보낼때 서버가 이ㅏ해할 수 있는 문자열(JSON)
//
//		// 서버에서 JSON 을 던져준다면 우리가 자바에서 사용하기 위해서 class로 변환 해야 한다.
//		// dto 개념을 배웠음 !!!
//
////		System.out.println(jsonStr);
////		// 파싱하는 방법 -1
////		Student objS1 = gson.fromJson(jsonStr, Student.class);
////		System.out.println(objS1.name);
////		System.out.println(objS1.age);
////		System.out.println(objS1.address);
//		System.out.println("-------------------------");
//		// 파싱하는 방법 -2
////		Student[] objA1 = gson.fromJson(studentArr, Student[].class);
////		for (Student student : objA1) {
////			System.out.println(student);
////		}
////		System.out.println(objA1);
//		
//		// JsonArray;
//		// 파싱하는 방법 -3
//		// Array list 만드는 방법
//		// 타입 명시함!!!
//		Type studentType = new TypeToken<ArrayList<Student>>(){}.getType();;
//		
//		ArrayList(Student) arrayList = gson.fromJson(jsonArratStr, studentType);
//		
//		// 파시방하는 방법 3
//		// 1. : Object
//		// 2. : 배열
//		// 3. : ArrayList
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	}
//}
