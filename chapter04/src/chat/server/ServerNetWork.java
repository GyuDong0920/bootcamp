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
			// 서버소켓 생성과 동시에 포트에 연결
			serverSocket = new ServerSocket(port);
			mContext.getTextArea().append("서버를 실행하겠습니다.\n");
			connect();
		} catch (IOException e) {
			// 예외처리 메세지 문구가 뜨는 창
			JOptionPane.showMessageDialog(null, "이미 사용중인 포트입니다.", "Error", JOptionPane.ERROR_MESSAGE);
			mContext.getStartbutton().setEnabled(true);
			mContext.getStopButton().setEnabled(false);
		} catch (Exception e) {
			// 숫자가 아닌 값을 넣었을 때
			JOptionPane.showMessageDialog(null, "잘못입력하였습니다.", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	private void connect() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						mContext.getTextArea().append(mContext.getTextField().getText() + "port" + " 접속 대기중...\n");
						socket = serverSocket.accept();
						UserSocket userSocket = new UserSocket(socket, mContext);
						userSocket.start();

					} catch (IOException e) {
						mContext.getTextArea().append("서버 중지 @Server ON 버튼을 눌러주세요.\n");
						break;

					}
				}

			}
		});
		th.start();
	}

	// 전체 메세지
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
			
		} else if (protocol.equals("CreateRoom")) {

			

		} else if (protocol.equals("Chatting")) {

		} else if (protocol.equals("JoinRoom")) {

		} else if (protocol.equals("OutRoom")) {

		}
	}

	public class chatRoom {

	}
}
