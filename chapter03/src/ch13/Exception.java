package ch13;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ch12.MyException;

public class Exception {
	
	String fileName;
	
	public Exception(String fileName) {
		this.fileName = fileName;
	}
	
	public String readFile() throws IOException{
		FileInputStream fis = new FileInputStream(this.fileName);
		Properties properties = new Properties();
		properties.load(fis);
		String dbType = properties.getProperty("DBTYPE");
		return dbType;
	}
	
	public static void mani(String[] ars) {
		
		String dbType = null;
		Exception ex = new Exception("a.txt");
		try {
			dbType = ex.readFile();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
