package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;


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
			URL url = new URL("https://yts.mx/api/v2/list_movies.json");
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
			System.out.println(str);
			
			Yts yts = new Gson().fromJson(str, Yts.class);
			System.out.println(yts.getData().getMovieCount());
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
