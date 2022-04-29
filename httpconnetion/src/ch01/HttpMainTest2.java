package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import dto.Post;

public class HttpMainTest2 {
public static void main(String[] args) {
	
	// 문자열을 
	try {
		URL url = new URL("https://jsonplaceholder.typicode.com/photos/20");
		HttpURLConnection connetion = (HttpURLConnection) url.openConnection();
		connetion.setRequestMethod("GET"); // REST API
		
		int statusCode = connetion.getResponseCode();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connetion.getInputStream()));
		
		StringBuffer buffer = new StringBuffer();
		String line = null;
		
		if(statusCode == 200) {
			while((line = reader.readLine()) != null ) {
				buffer.append(line + "\n");
			}
		} else if(statusCode == 400) {
			System.out.println("네트워크 연결이 불안정합니다.");
		}
		
		String str = buffer.toString();
		System.out.println(str);
		System.out.println("------------------");
		
		Post post = new Post();
		// 문자열을 파싱해서 post 객체에 값을 담아 보세요
		// 출력까지 !!!
		post.userId = Integer.parseInt(str.substring(15,16));
		post.id = Integer.parseInt(str.substring(25, 28));
		post.Title = (str.substring(40, 123));
		post.url = (str.substring(134, 174));
	    post.thumbnailUrl = (str.substring(194, 235));
		
		System.out.println("UserID : " + post.userId);
		System.out.println("id : " + post.id);
		System.out.println("Title : " + post.Title);
		System.out.println("url : " + post.url);
		System.out.println("thumbnailUrl : " +  post.thumbnailUrl);
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	
	
}
}
