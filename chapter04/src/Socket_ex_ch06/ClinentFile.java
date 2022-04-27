package Socket_ex_ch06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClinentFile {

	Socket socket;
	BufferedWriter bufferedWriter; // 소켓에다가 연결할 OutputStream
	final String IP = "localhost"; // 자기 자신을 나타내는 주소 127.0.0.1
	final int PORT = 10001;

	BufferedReader keybordbufferedReader; // 키보드에 연결할 스트림
	
	// 서버에서 보낸 메세지를 읽기 위해 버퍼 장착
	// 새로운 쓰레드가 필요
	BufferedReader bufferedReader;
	
	
	public ClinentFile() {

		System.out.println("1. 클라이언트 소켓 시작");
		try {

			socket = new Socket(IP, PORT);

			System.out.println("2. 버퍼 연결");

			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 키보드 연결
			System.out.println("3. 키보드 버퍼에 연결");
			keybordbufferedReader = new BufferedReader(new InputStreamReader(System.in));

			// 초기화 처리
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 새로운 쓰레드 시작
			readThread readthread = new readThread();
			Thread thread = new Thread(readthread);
			thread.start();
			
			
			while (true) {
				System.out.println("4. 키보드 입력 대기");
				String msg = keybordbufferedReader.readLine(); // 입력 대기 중...

				// 사용자 문제열을 받았으면 보내야 한다 -->
				// 중요 : 메세지 끝을 알려줘야 한다.
				bufferedWriter.write(msg + "\n");
				bufferedWriter.flush();

			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				bufferedReader.close();
				bufferedWriter.close();
				keybordbufferedReader.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	// 내부 클래스 생성
	private class readThread implements Runnable {

		@Override
		public void run() {
			while(true) {
				try {
					
					String msg = bufferedReader.readLine();
					System.out.println("서버로 부터 받은 메세지" + msg);
				} catch (IOException e) {
					
					e.printStackTrace();
				}	
			
			}
			
			
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		new ClinentFile();

	}

}
