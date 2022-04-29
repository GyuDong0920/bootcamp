package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientNetWorkService implements ClientListInterFace {

	// network 자원
	private Socket socket;
	private String ip;
	private int port;
	private String userId;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ClientGUI clientGUI;
	private ClientProtocol protocol;

	public ClientNetWorkService(ClientGUI gui) {
		clientGUI = gui;
		protocol = new ClientProtocol(gui, this);
	}

	public void connectServer() {
		try {
			// 서버에 접속합니다.
			socket = new Socket(ip, port);
			network();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void network() {

		try {
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);

			// 문제발생
			userId = clientGUI.getUserIDTextField().getText().trim();
			sendmessage(userId);
			// 벡터에 유저의 id 를 저장하고 리스트 화면에 추가시켜준다.
			userVclist.add(userId);
			totalList.setListData(userVclist);
			
			Thread cth = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							// 서버로부터 수신된 메세지.

							String msg = dis.readUTF();
							System.out.println(msg);
							protocol.inmessage(msg);
						} catch (IOException e) {
							e.printStackTrace();
							try {
								userVclist.removeAll(userVclist);
								roomListvc.removeAll(roomListvc);
								totalList.setListData(userVclist);
								roomUserList.setListData(userVclist);
								roomList.setListData(roomListvc);
								clientGUI.getViewChatTextArea().setText("\n");
								is.close();
								os.close();
								dis.close();
								dos.close();
								socket.close();
								JOptionPane.showMessageDialog(null, "서버가 종료됨!", "알림", JOptionPane.ERROR_MESSAGE);
								break;
							} catch (Exception e2) {
								return;
							}
						}
					}
				}
			});
			cth.start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림", JOptionPane.ERROR_MESSAGE);
		} // Stream 준비완료
		clientGUI.getConnectBtn().setEnabled(false);
	}

	public void sendmessage(String msg) {

		try {
			System.out.println("ddddddddddd : " + msg);
			dos.writeUTF(msg);
			dos.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
