package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import dto.Post;
import lombok.Getter;
import lombok.Setter;

public class HttpMainTest3 {
public static void main(String[] args) {
	
	// 문자열을 
	try {
		URL url = new URL("https://jsonplaceholder.typicode.com/photos/3");
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
		
		
		
		Gson gson = new Gson();
		
		Post post = gson.fromJson(str, Post.class);
		System.out.println(post.getUserId());
		System.out.println(post.getId());
		System.out.println(post.getTitle());
		System.out.println(post.getUrl());
		System.out.println(post.getThumbnailUrl());
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	
	
}
}
