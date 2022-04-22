package ch12;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyException {

	String fileName;
	
	public MyException(String fileName) {
		this.fileName = fileName;
	}
	// throws 란 던지기 (사용하는 사람이 직접 예외 처리를 해달라 지시 함)
	public String readFile() throws IOException{
		FileInputStream fis = new FileInputStream(this.fileName);
		Properties properties = new Properties();
		properties.load(fis);
		String dbType = properties.getProperty("DBTYPE");
		return dbType;
	}
	
	public static void mani(String[] ars) {
		
		
		String dbType = null;
		MyException my = new MyException("a.txt");
		try {
			dbType = my.readFile();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
				
				
				
	

}
