package ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


	class Todos {
		int userId;
		int id;
		String Title;
		boolean completed;
	}


public class HttpMainTest3 {

	public static void main(String[] args) {
		
		// http 통신을 위해 주소 값을 주는 클래스 URL
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/todos");
			// 준비물2
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			// 100, 200, 300, 400, 500 대 종류가 있다.
			int statusCode = connection.getResponseCode();
//			System.out.println("statusCod : " + statusCode);
		
			// http 통신할 때 스트림을 달아야 한다.
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			StringBuffer sb = new StringBuffer();
			String line = null;
			
			if(statusCode == 200) {
				while((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			}
			
			String str = sb.toString();
			Gson gson = new Gson();
			Type todoListType = new TypeToken<ArrayList<Todos>>(){}.getClass();
			
			
			Todos todos = gson.fromJson(str, Todos.class);
			ArrayList<Todos> todoss;
			System.out.println(todos);
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
