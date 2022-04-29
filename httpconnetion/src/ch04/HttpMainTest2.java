package ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@ToString
class status {
	int movie_count;
	int limit;
	int page_number;
	Data date;	
}
class Data{
	
	int mobie_cont;
	int limit;
	int page_number;
	
	List<Movie> movies;
}


class Movie {

	
	int id;
	int url;
	int imdb_Code;
	String title;
	String title_english;
	String title_long;
	String slug;
	int year;
	float rating;
	int runtime;
	String summary;
	String description_full;
	String yt_trailer_code;
	List<String> geners;
	
	
}
class geners {
	String Action;
	String Drama;
	String Thriller;
	
}
public class HttpMainTest2 {

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
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
