package ch03;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MainTest3 {

	public static void main(String[] args) {

		JsonObject jo1 = new JsonObject();
		jo1.addProperty("name", "홍길동");
		jo1.addProperty("age", 20);
		jo1.addProperty("address", "부산");

		JsonObject jo2 = new JsonObject();
		jo2.addProperty("name", "이순신");
		jo2.addProperty("age", 33);
		jo2.addProperty("address", "서울");

		JsonObject jo3 = new JsonObject();
		jo3.addProperty("name", "세종대왕");
		jo3.addProperty("age", 59);
		jo3.addProperty("address", "세종시");

		JsonArray ja1 = new JsonArray();

		ja1.add(jo1);
		ja1.add(jo2);
		ja1.add(jo3);

		System.out.println(ja1);

		JsonObject jo4 = new JsonObject();
		jo4.addProperty("id", 1);
		jo4.addProperty("title", "청소");
		jo4.addProperty("complete", false);

		JsonObject jo5 = new JsonObject();
		jo5.addProperty("id", 2);
		jo5.addProperty("title", "영어공부");
		jo5.addProperty("complete", true);

		JsonObject jo6 = new JsonObject();
		JsonObject jo7 = new JsonObject();
		JsonObject jo8 = new JsonObject();

		jo7.addProperty("server_name", "server_1");

		JsonArray ja2 = new JsonArray();

		ja2.add(jo4);
		ja2.add(jo5);
		
		jo6.add("todoList", ja2);
		System.out.println(jo6 + "\"server_name\" : \"server_1\"");
		
	}

}
