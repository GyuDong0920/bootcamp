

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile{

	ServerSocket serverSoket;
	Socket socket;
	BufferedReader bufferedReader; // 클라이언트에서 온 데이터를 읽는 녀석

	////////////////////////////
	BufferedWriter bufferedWriter; // 클라이언트 쪽으로 데이터를 보내는 녀석
	BufferedReader keyboarBufferedReader;
	
	
	public ServerFile() {
		System.out.println("1.>>>>>>>>>>>>서버 소켓시작 <<<<<<<<<<<<<");
		try {
			serverSoket = new ServerSocket(10001);
		
			System.out.println("2. 서버 소켓 생성 완료");

			socket = serverSoket.accept(); // 클라이언트 연결 대기중 ...
	
			System.out.println("3. 클라이언트 연결 완료");

			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 초기화 처리
			keyboarBufferedReader = new BufferedReader(new InputStreamReader(System.in));
			// 클라이언트에게 보낼 스트림 연결 (OutputStream)
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 쓰레드 처리
			writeThread writeThread = new writeThread();
			Thread thread = new Thread(writeThread);
			thread.start();
			
			FileWriter writer = new FileWriter("server.txt", true);
			
			
			while (true) {
				String msg = bufferedReader.readLine();
				System.out.println("4. 클라이언트로 받은 메세지 : \n" + msg);
				writer.write(msg + "\n");
				writer.flush();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("예외 발생 : " + e.getMessage());
		} finally {
			try {
				bufferedReader.close();
				keyboarBufferedReader.close();
				bufferedWriter.close();
				socket.close();
				serverSoket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

	// 내부 클래스 생성
	private class writeThread implements Runnable {

		@Override
		public void run() {
			
			while (true) {
				try {
					// 키보드에서 데이터를 읽어 줌
					String msg = keyboarBufferedReader.readLine();
					// 클라이언트로 데이터 보내기 --> 소켓
					bufferedWriter.write(msg + "\n");
					bufferedWriter.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	public static void main(String[] args) {
		new ServerFile();
	}
}
