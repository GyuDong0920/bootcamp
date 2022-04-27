package Socket_ex_ch06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;
import javax.print.attribute.standard.Severity;

public class UserSocket extends Thread{
	
	ServerFile mContext;
	Socket socket;
	// 버퍼 리더 달기
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	
	
	// 의존성 컴포지션 관계
	public UserSocket(ServerFile mContxt,Socket socket) {
		// 생성자 객체가 메모리에 올라갈떄 필요한 초기화 !!!
		this.socket = socket;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			mContxt.brodcast(msg);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void run() {
		
		new Thread (new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						String msg = bufferedReader.readLine();
						System.out.println("서버 --> UserSocket : msg :"+ msg);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		
	}
	
	public void sendMessage(String msg) {
		try {
			bufferedWriter.write(msg + "/n");
			bufferedWriter.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
