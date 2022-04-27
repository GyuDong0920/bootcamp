package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import chat.server.ServerNetWork.chatRoom;

public class User extends Thread implements ServerInterFace {
	private InputStream inputStream; // 데이터가 들어오는 통로 byte 단위로
	private OutputStream outputStream; // 데이터가 나가는 통로 byte 단위로
	private DataInputStream dataInputStream; // 데이터를 변환 해주는 클래스
	private DataOutputStream dataOutputStream; // 데이터를 변환 해주는 클래스
	String name;
	String chroomName;
	private Socket userSocket;
	
	ServerGUI serverGUI;
	ServerNetWork netWork;
	
	public User(Socket socket, ServerGUI gui, ServerNetWork net) {
		this.userSocket = socket;
		serverGUI = gui;
		netWork = net;
		netWork();
	}

	private void netWork() {
		try {
			inputStream = userSocket.getInputStream(); // 들어오는 스트림에 유저 소켓을 연결~
			dataInputStream = new DataInputStream(inputStream); // 유저 소켓에 연결된 들어오는 스트림에 데이터 파일을 읽게 해주기~
			outputStream = userSocket.getOutputStream(); // 나가는 스트림에 유저 소켓 연결 ~
			dataOutputStream = new DataOutputStream(outputStream); // 데이터 파일 나가게 해줌 ~

			// 처음 접속한 유저의 ID를 받는 곳.
			name = dataInputStream.readUTF(); // 한글 일본어 등 UTF로 닉네임 받아줌
			serverGUI.getTextArea().append("<<" + name + ">> 입장\n");
			
			// 기존 유저에게 알림
			netWork.announCement("NewUser/" + name);
 
			// 자신에게 현재 접속 중인 유저를 알린다.
			for (int i = 0; i < vc.size(); i++) {
				User user = vc.elementAt(i);
				sendMessage("OldUser/" + user.name);
			}
			for (int i = 0; i < chRoom.size(); i++) {
				chatRoom room = chRoom.elementAt(i);
				sendMessage("OldRoom/" + room.roomName);
			}
//			 사용자에게 본인임을 알린 뒤 리스트에 추가한다.
			vc.add(this);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Straem set Error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void run() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						
						String msg = dataInputStream.readUTF();
						serverGUI.getTextArea().append("<<" + name + ">>" + msg + "\n");
						netWork.inmessage(msg);
					} catch (Exception e) {
						try {
							// 나갔음
							serverGUI.getTextArea().append(name + ": 사용자 접속이 끊어졌습니다.\n");
							dataOutputStream.close();
							dataInputStream.close();
							userSocket.close();
							vc.remove(this);
							chRoom.remove(this);
							netWork.announCement("UserOut/" + name);
							netWork.announCement("ErrorOutRoom/" + chroomName);
							netWork.announCement("UserData_Updata/ok");
							break;
						} catch (IOException e1) {
							break;
						}

					}

				}
				
			}
		}).start();
		
	}
	public void sendMessage(String msg) {
		try {
			System.out.println("sssss : " + msg);
			dataOutputStream.writeUTF(msg);
			dataOutputStream.flush();
			System.out.println(name +" <<<< " + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
