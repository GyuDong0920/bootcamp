package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserSocket extends Thread  {
	
	private InputStream inputStream; // 데이터가 들어오는 통로 byte 단위로
	private OutputStream outputStream; // 데이터가 나가는 통로 byte 단위로
	private DataInputStream dataInputStream; // 데이터를 변환 해주는 클래스
	private DataOutputStream dataOutputStream; // 데이터를 변환 해주는 클래스
	private String userName;
	private String chroomName;
	private Socket userSocket;
	private ServerGUI mContext;
	

	
	public UserSocket(Socket socket, ServerGUI mContext) {
		this.userSocket = socket;
		this.mContext = mContext;
		netWork();
	}
	

	private void netWork() {
		try {
			inputStream = userSocket.getInputStream(); // 들어오는 스트림에 유저 소켓을 연결~
			dataInputStream = new DataInputStream(inputStream); // 유저 소켓에 연결된 들어오는 스트림에 데이터 파일을 읽게 해주기~
			outputStream = userSocket.getOutputStream(); // 나가는 스트림에 유저 소켓 연결 ~
			dataOutputStream = new DataOutputStream(outputStream); // 데이터 파일 나가게 해줌 ~

			// 처음 접속한 유저의 ID를 받는 곳.
			userName = dataInputStream.readUTF(); // 한글 일본어 등 UTF로 닉네임 받아줌
			System.out.println("userName " +  userName);
			//System.out.println();
			//mContext.getTextArea().append("<<" + userName + ">> 입장\n");
			
			
////			// 기존 유저에게 알림
			mContext.getServerNetWork().announCement("NewUser/" + userName);
//
//			// 자신에게 현재 접속 중인 유저를 알린다.
//			for (int i = 0; i < mContext.vc.size(); i++) {
//				User user = mContext.vc.elementAt(i);
//				sendMessage("OldUser/" + user.userName);
//			}
//			for (int i = 0; i < mContext.chRoom.size(); i++) {
//				chatRoom room = mContext.chRoom.elementAt(i);
//				sendMessage("OldRoom/" + room.roomName);
//			}
//			// 사용자에게 본인임을 알린 뒤 리스트에 추가한다.
//			mContext.vc.add(this);
			
			mContext.getServerNetWork().getVc().add(this);

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
						System.out.println("-----------------");
						System.out.println("msg : " + msg);
						System.out.println("-----------------");
//						mContext.getTextArea().append("<<" + userName + ">>" + msg + "\n");
//						mContext.getServerNetWork().inmessage(msg);
					} catch (Exception e) {
						try {
							// 나갔음
							mContext.getTextArea().append(userName + ": 사용자 접속이 끊어졌습니다.\n");
							dataOutputStream.close();
							dataInputStream.close();
							userSocket.close();
							mContext.getServerNetWork().getVc().remove(this);
							mContext.getServerNetWork().getChRoom().remove(this);
							mContext.getServerNetWork().announCement("UserOut/" + userName);
							mContext.getServerNetWork().announCement("ErrorOutRoom/" + chroomName);
							mContext.getServerNetWork().announCement("UserData_Updata/ok");
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

			dataOutputStream.writeUTF(msg);
			dataOutputStream.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
