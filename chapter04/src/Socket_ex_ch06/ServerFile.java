package Socket_ex_ch06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile{

	ServerSocket serverSoket; // 다른 클라이언트 연결 대비 (포트번호 설정)
//	Socket socket; 
//	BufferedReader bufferedReader; // 클라이언트에서 온 데이터를 읽는 녀석

	////////////////////////////
//	BufferedWriter bufferedWriter; // 클라이언트 쪽으로 데이터를 보내는 녀석
//	BufferedReader keyboarBufferedReader;
	
	
	public ServerFile() {
		System.out.println("1.>>>>>>>>>>>>서버 소켓시작 <<<<<<<<<<<<<");
		try{
			serverSoket = new ServerSocket(10001);

		}catch (Exception e) {
		
	}
		while(true) {
			try {
				Socket socket = serverSoket.accept();
				UserSocket userSocket = new UserSocket(socket);
				userSocket.start();
			} catch(IOException e) {
				
			}
		}
		
	}
	public static void main(String[] args) {
		new ServerFile();
	}
}
