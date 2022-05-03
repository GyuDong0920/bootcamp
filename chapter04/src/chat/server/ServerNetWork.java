package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerNetWork {

	private ServerGUI mContext;
	private Vector<UserSocket> vc = new Vector<UserSocket>();
	private Vector<chatRoom> chRoom = new Vector<chatRoom>();

	// network
	private ServerSocket serverSocket;
	private Socket socket;
	private int port;

	public ServerNetWork(ServerGUI mContext) {
		this.mContext = mContext;
	}

	public void setNetwork() {

		try {
	
			serverSocket = new ServerSocket(port);
			mContext.getTextArea().append("User wating.\n");
			connect();
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "", "Error", JOptionPane.ERROR_MESSAGE);
			mContext.getStartbutton().setEnabled(true);
			mContext.getStopButton().setEnabled(false);
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "잘못입력하셨습니다..", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	private void connect() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						mContext.getTextArea().append(mContext.getTextField().getText() + "port" + " �젒�냽 ��湲곗쨷...\n");
						socket = serverSocket.accept();
						UserSocket userSocket = new UserSocket(socket, mContext);
						System.out.println("==========");
						userSocket.start();

					} catch (IOException e) {
						mContext.getTextArea().append("접속에 실패했습니다.\n");
						break;

					}
				}

			}
		});
		th.start();
	}

	
	public void announCement(String str) {
		for (int i = 0; i < vc.size(); i++) {
			UserSocket userSocket = vc.elementAt(i);
			userSocket.sendMessage(str);
		}
	}

	public void inmessage(String str) {
		StringTokenizer st = new StringTokenizer(str, "/");

		String protocol = st.nextToken();
		String message = st.nextToken();

		if (protocol.equals("Note")) {
			st = new StringTokenizer(message, "@");
			String user = st.nextToken();
			String note = st.nextToken();
			
			for (int i = 0; i < vc.size(); i++) {
				UserSocket socket = vc.elementAt(i);
				if(socket.getUserName().equals(user)) {
					socket.sendMessage("Note/" + socket.getUserName() + "@" + note);
				}
			}
		} else if (protocol.equals("CreateRoom")) {
			for(int i = 0; i < chRoom.size(); i++) {
				chatRoom room = chRoom.elementAt(i);
			
			}
			

		} else if (protocol.equals("Chatting")) {

		} else if (protocol.equals("JoinRoom")) {

		} else if (protocol.equals("OutRoom")) {

		}
	}

	public class chatRoom {
		// 룸 정보
		String roomName;
		Vector<UserSocket> roomUservc = new Vector<UserSocket>();
		
		public chatRoom() {
			
		}
						
					}

}
