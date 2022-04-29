package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import chat.server.ServerNetWork.chatRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerNetWork implements ServerInterFace {
	
	
	Vector<User> vc = new Vector<User>();
	Vector<chatRoom> chRoom = new Vector<chatRoom>();
	

	// network
	ServerNetWork mcontext;
	private ServerSocket serverSocket;
	private Socket socket;
	private int port;

	ServerGUI serverGUI;

	public ServerNetWork(ServerGUI gui) {
		serverGUI = gui;
		mcontext = this;
	}

	public void setNetwork() {

		try {
			// 서버소켓 생성과 동시에 포트에 연결
			serverSocket = new ServerSocket(port);
			serverGUI.getTextArea().append("서버를 실행하겠습니다.\n");
			connect();
		} catch (IOException e) {
			// 예외처리 메세지 문구가 뜨는 창
			JOptionPane.showMessageDialog(null, "이미 사용중인 포트입니다.", "Error", JOptionPane.ERROR_MESSAGE);
			serverGUI.getStartbutton().setEnabled(true);
			serverGUI.getStopButton().setEnabled(false);
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
						serverGUI.getTextArea().append(serverGUI.getTextField().getText() + "port" + " 접속 대기중...\n");
						socket = serverSocket.accept();
						User user = new User(socket, serverGUI, mcontext);
						user.start();
					} catch (IOException e) {
						serverGUI.getTextArea().append("서버 중지 @Server ON 버튼을 눌러주세요.\n");
						break;

					}
				}

			}
		});
		th.start();
	}

	public void announCement(String str) {
		for (int i = 0; i < vc.size(); i++) {
			User user = vc.elementAt(i);
			user.sendMessage(str);

		}
	}

	public void inmessage(String str) {
		StringTokenizer st = new StringTokenizer(str, "/");

		String protocol = st.nextToken();
		String message = st.nextToken();

		System.out.println("protocol : " + protocol);
		System.out.println("message : " + message);

		if (protocol.equals("Note")) {
			System.out.println(message);
			st = new StringTokenizer(message, "@");
			String user = st.nextToken();
			String note = st.nextToken();
			// 백터에서 해당 사용자를 찾아서 쪽지를 전송한다.
			for (int i = 0; i < vc.size(); i++) {

				User userInfo = vc.elementAt(i);

				// 쪽지는 반드시 찾은 사용자에게 메세지를 보내줘어야 한다. // 확인 필요함.
				if (userInfo.getUserName().equals(user)) {
					userInfo.sendMessage("Note/" + userInfo.getUserName() + "@" + note);

				}
			}
		} else if (protocol.equals("CreateRoom")) {

			StringTokenizer s = new StringTokenizer(message, "@");
			String useId = s.nextToken();
			String roomName1 = s.nextToken();

			for (User user : vc) {
				System.out.println("createroom: " + user.getName());

				if (user.getUserName().equals(useId)) {
					chatRoom newroom = new chatRoom(roomName1, user);
					System.out.println(newroom.getRoomName());

					// 벡터 이다.
					chRoom.add(newroom);

					System.out.println("-----------------");
					System.out.println(chRoom);
					System.out.println(chRoom.size());
					System.out.println("-----------------");

					// user.sendMessage("CreateRoom/" + roomName1);

				}
			}
			announCement("new_Room/" + roomName1);

		} else if (protocol.equals("Chatting")) {
			String msg = st.nextToken();
			for (int i = 0; i < chRoom.size(); i++) {
				chatRoom room = chRoom.elementAt(i);

				if (room.roomName.equals(message)) {
					// room.roomAnnounCement("Chatting/" + user.name + "/" + msg);

				}
			}
		} else if (protocol.equals("JoinRoom")) {
			//
			StringTokenizer st1 = new StringTokenizer(message, "@");
			String jRoomName = st1.nextToken();
			String juserId = st1.nextToken();
			System.out.println("joinrom[1]: " + jRoomName);
			System.out.println("joinrom[2]: " + juserId);

			chatRoom room = null;
			User juser = null;
			for (chatRoom chatRoom : chRoom) {
				if (chatRoom.getRoomName().equals(jRoomName)) {
					room = chatRoom;
					break;
				}
			}
			System.out.println("???????" + room.getRoomName());
			for (User user : vc) {
				if (user.getName().equals(juserId)) {
					juser = user;
					break;
				}
			}

			room.addUser(juser);
			System.out.println("??????? juser name: " + juser.getName());
			for (User user : room.getRoomUservc()) {
				if (!(user.getName().equals(juserId))) {
					user.sendMessage("JoinRoom/" + juserId);
					System.out.println("이게 실행이 되나요 ?");
				}
			}

//			for (int i = 0; i < chRoom.size(); i++) {	
//				for (int y = 0; y < vc.size(); y++) {
//					User user = vc.elementAt(y);
//					chatRoom room = chRoom.elementAt(i);
//					if (room.roomName.equals(message)) {
//						// 신규 접속자 알림
//						room.roomAnnounCement("Chatting/[알림]/ <<<" + user.name + "입장 >>>");
//						room.addUser(user); // 룸에 자신을 추가
//						user.sendMessage("JoinRoom/" + message);
//						
//					}
//				}
//			}
		} else if (protocol.equals("OutRoom")) {
//			for (int i = 0; i < chRoom.size(); i++) {
//				chatRoom room = chRoom.elementAt(i);
//				if (room.roomName.equals(message)) {
//					room.deleteRoom(user);
//					user.sendMessage("OutRoom/ok");
//					break;
//
//				}
//			}
		}
	}

	public class chatRoom {
		String roomName;
		Vector<User> roomUservc = new Vector<User>();

		public String getRoomName() {
			return roomName;
		}

		public void setRoomName(String roomName) {
			this.roomName = roomName;
		}

		public Vector<User> getRoomUservc() {
			return roomUservc;
		}

		public void setRoomUservc(Vector<User> roomUservc) {
			this.roomUservc = roomUservc;
		}

		public chatRoom(String roomName, User user) {
			this.roomName = roomName;
			this.roomUservc.add(user);

			user.chroomName = roomName;
		}

		private void roomAnnounCement(String str) {
			for (int i = 0; i < chRoom.size(); i++) {
				User room = roomUservc.elementAt(i);
				room.sendMessage(str);
			}
		}

		private void addUser(User user) {
			roomUservc.add(user);
		}

		@Override
		public String toString() {
			return roomName;
		}

		private void deleteRoom(User user) {
			roomUservc.remove(user);
			boolean empty = roomUservc.isEmpty();
			if (empty) {
				for (int i = 0; i < chRoom.size(); i++) {
					chatRoom room = chRoom.elementAt(i);
					if (room.roomName.equals(roomName)) {
						chRoom.remove(this);
						announCement("EmptyRoom/" + roomName);
						announCement("UserData_Updata/ok");
						break;
					}
				}
			}
		}
	}
}
