package ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import dto.Post;
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

@AllArgsConstructor
@ToString
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

public class MainTest4 {

	public static void main(String[] args) {
		
	JsonArray ja1 = new JsonArray();
	ArrayList<Movie> list = new ArrayList<Movie>();
	
	try {
		URL url = new URL("https://yts.mx/api/v2/list_movies.json");
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
//		System.out.println(str);
		
		
		
		Gson gson = new Gson();
		
		Movie movie = gson.fromJson(str, Movie.class);
		System.out.println(movie);

	} catch (IOException e) {
		e.printStackTrace();
	}
	
		
	}
}
