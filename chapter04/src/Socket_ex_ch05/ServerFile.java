package Socket_ex_ch05;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ServerFile extends JFrame implements ActionListener {

	// GUI 자원
	private JPanel panel;
	private JTextField textField;
	private JTextArea textArea;
	private JLabel label;
	private JButton btnServerStart;
	private JButton btnServerStop;

	// Network 자원
	private ServerSocket serverSocket;
	private Socket socket;
	private int port;

	// 그외 자원들 Vector 란 배열에 있는 값을 스레드가 동시에 가져갈 수 없고 하나씩 
	// 가져가야함 멀티스레드 환경에서 안전하게 객체 추가, 삭제 가능 단일스레드는 어레이가 효율적임
	private Vector<UserInfomation> vc = new Vector<UserInfomation>();
	private Vector<RoomInfomation> vcRoom = new Vector<RoomInfomation>();
	

	public ServerFile() {
		init();
		addListener();
		textField.requestFocus(); // textField 에 포커스를 맞추기 위한
	}

	// GUI 초기화
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 410);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // EmptyBorder 래이블처럼 평면 형태를 보여줌
		setContentPane(panel);
		panel.setLayout(null);

		ScrollPane scrollPane = new ScrollPane(); // ScrollPane 은 컴포넌트에 스크롤 기능을 추가해준다. https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=sks6624&logNo=150165616213
		scrollPane.setBounds(10, 10, 309, 229);
		textArea = new JTextArea();
		textArea.setBounds(12, 11, 310, 230);
		scrollPane.add(textArea);
		panel.add(scrollPane);
		textArea.setEditable(false); // 입력은 불가하면서 텍스트 색상도 바꾸고 싶다면 setEditable(false)를 사용하면 된다.

		label = new JLabel("포트번호 :");
		label.setBounds(12, 273, 82, 15);
		panel.add(label);

		textField = new JTextField();
		textField.setBounds(98, 270, 224, 21);
		panel.add(textField);
		textField.setColumns(10); // 패널에 붙이고 글자수를 10개로 제한함

		btnServerStart = new JButton("서버실행");
		btnServerStart.setBounds(12, 315, 154, 23);
		panel.add(btnServerStart);

		btnServerStop = new JButton("서버중지");
		btnServerStop.setBounds(168, 315, 154, 23);
		panel.add(btnServerStop);
		btnServerStop.setEnabled(false);

		setVisible(true);
	}

	// 이벤트 리스너
	private void addListener() {

		textField.addActionListener(this);
		btnServerStart.addActionListener(this);
		btnServerStop.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 시작 버튼을 눌렀을 때
		if (e.getSource() == btnServerStart) {
			if (textField.getText().length() == 0) {
				System.out.println("  값을 입력 하세요 ");
				
				// 값이 이미 있을 때 
			} else if (textField.getText().length() != 0) {

				// 값을 가져와서 port변수에 저장시키기
				port = Integer.parseInt(textField.getText());
			
				startNetwork();
				textField.setEditable(false);
				btnServerStart.setEnabled(false);
				btnServerStop.setEnabled(true);
			}
			// 서버 중지 버튼을 눌렀을 때
		} else if (e.getSource() == btnServerStop) {
			try {
				serverSocket.close();
				vc.removeAllElements();
				vcRoom.removeAllElements();
				textField.setEditable(true);
				btnServerStart.setEnabled(true);
				btnServerStop.setEnabled(false);
			} catch (IOException e1) {

			}
		}
	}

	private void startNetwork() {
		try {
			// 서버소켓 생성과 동시에 포트에 연결
			serverSocket = new ServerSocket(port);
			textArea.append("서버를 시작 하겠습니다.\n");
			connect(); // 쓰레드를 줘서 대기하라고 함 .accept
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "이미 사용중인 포트입니다.", "알림",
					JOptionPane.ERROR_MESSAGE);
			btnServerStart.setEnabled(true);
			btnServerStop.setEnabled(false);
		} catch (Exception e) {
			// 숫자가 아닌 값을 넣었을 때
			JOptionPane.showMessageDialog(null, "잘못입력하였습니다.", "알림",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void connect() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						// 소켓 대기 상태 // 메인은 바쁨
						textArea.append("사용자의 접속을 기다립니다.\n");
						socket = serverSocket.accept();

						UserInfomation useInfo = new UserInfomation(socket);
						// 각각의 스레드를 등록시켜준다.
						useInfo.start();
					} catch (IOException e) {
						textArea.append("서버가 중지됨! 다시 스타트 버튼을 눌러주세요\n");
						break;
					}
				}
			}
		});
		th.start();
	}

	// 전체 사용자에게 메세지를 보내는 부분
	public void broadCast(String str) {
		for (int i = 0; i < vc.size(); i++) {
			UserInfomation uinf = vc.elementAt(i);
			// 여기서 프로토콜의 개념을 사용
			uinf.sendmessage(str);
		}
	}

	// 내부클래스 쓰레드를 상속 받고있다.
	class UserInfomation extends Thread {
		private InputStream inputStream; // 데이터가 들어오는 통로 byte 단위로
		private OutputStream outputStream; // 데이터가 나가는 통로 byte 단위로
		private DataInputStream dataInputStream; // 데이터를 변환 해주는 클래스
		private DataOutputStream dataOutputStream; // 데이터를 변환 해주는 클래스
		String nickName; 
		String myCurrentRoomName; 
		private Socket userSocket;

		private boolean roomCheck = true; // 방이 이미 만들어져있는지 췤

		public UserInfomation(Socket soc) {
			this.userSocket = soc;
			network();
		}

		private void network() {
			try {
				inputStream = userSocket.getInputStream(); // 들어오는 스트림에 유저 소켓을 연결~
				dataInputStream = new DataInputStream(inputStream); // 유저 소켓에 연결된 들어오는 스트림에 데이터 파일을 읽게 해주기~
				outputStream = userSocket.getOutputStream(); // 나가는 스트림에 유저 소켓 연결 ~
				dataOutputStream = new DataOutputStream(outputStream); // 데이터 파일 나가게 해줌 ~

				// 처음 접속시 유저의 id를 입력받는다.
				nickName = dataInputStream.readUTF(); // 한글 일본어 등 UTF로 닉네임 받아줌
				textArea.append("[[" + nickName + "]] 입장\n"); 

				// 기존사용자들에게 신규 유저의 접속을 알린다.
				broadCast("NewUser/" + nickName); 

				// 자신에게 기존 사용자들을 알린다.
				for (int i = 0; i < vc.size(); i++) {
					UserInfomation uinf = vc.elementAt(i);
					sendmessage("OldUser/" + uinf.nickName);
				}
				for (int i = 0; i < vcRoom.size(); i++) {
					RoomInfomation room = vcRoom.elementAt(i);
					sendmessage("OldRoom/" + room.roomName);
				}

				// 사용자에게 자신을 알린후 벡터에 자신을 추가한다.
				vc.add(this);

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Stream설정에러!", "알림",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		// 브로드캐스트
		@Override
		public void run() {
			while (true) {
				try {
					String msg = dataInputStream.readUTF(); // 언어 받아들이는 용도
					
					textArea.append("[["+ nickName + "]]" + msg + "\n");
					inmessage(msg);
				} catch (IOException e) {
					try {
						// 나감
						textArea.append(nickName + " : 사용자접속끊어짐\n");
						dataOutputStream.close();
						dataInputStream.close();
						userSocket.close();
						vc.remove(this);
						vcRoom.remove(this);
						broadCast("UserOut/" + nickName);
						broadCast("ErrorOutRoom/"+myCurrentRoomName);
						broadCast("UserData_Updata/ok");
						break;
					} catch (IOException e1) {
						break;
					}
				}
			}
		}

		// Note/a1@안녕어디야?
		// CreateRoom/2번강
		private void inmessage(String str) {
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
					UserInfomation u = vc.elementAt(i);
					// 쪽지는 반드시 찾은 사용자에게 메세지를 보내줘어야 한다.
					if (u.nickName.equals(user)) {
						u.sendmessage("Note/" + nickName + "@" + note);
					}
				}	
			} else if (protocol.equals("CreateRoom")) {
				// 1.현재같은방이 존재하는지 확인한다.
				for (int i = 0; i < vcRoom.size(); i++) {
					RoomInfomation room = vcRoom.elementAt(i);
					if (room.roomName.equals(message)) { // 만들고자하는방이름을 확인한다
						sendmessage("CreateRoomFail/ok");
						roomCheck = false;
						break;
					} else {
						roomCheck = true;
					}
				} // end for
				if (roomCheck == true) {
					// 1.방을 생성한다.
					RoomInfomation new_room = new RoomInfomation(message, this);
					// 2. 전체 방 벡터에 생성된 방을 저장한다.
					vcRoom.add(new_room);
					// 3.사용자들에게 방과 방이름을 생성되었다고 알려준다.
					sendmessage("CreateRoom/" + message); // 자신에게 방 성공 메세지를 보낸다.
					broadCast("new_Room/" + message);
				}
			} else if (protocol.equals("Chatting")) {
				String msg = st.nextToken();
				for (int i = 0; i < vcRoom.size(); i++) {
					RoomInfomation r = vcRoom.elementAt(i);
					if (r.roomName.equals(message)) {
						r.roomBroadcast("Chatting/" + nickName + "/" + msg);
					}
				}
			} else if (protocol.equals("JoinRoom")) {
				for (int i = 0; i < vcRoom.size(); i++) {
					RoomInfomation r = vcRoom.elementAt(i);
					if (r.roomName.equals(message)) {
						// 신규접속자를 알린다.
						r.roomBroadcast("Chatting/[[알림]]/(((" + nickName
								+ " 입장))) ");
						r.addUser(this); // 해당 룸 객체에 자신을 추가시킨다.
						sendmessage("JoinRoom/" + message);
					}
				}
			} else if (protocol.equals("OutRoom")) {
				for (int i = 0; i < vcRoom.size(); i++) {
					RoomInfomation r = vcRoom.elementAt(i);
					if (r.roomName.equals(message)) {
						r.removeRoom(this);
						sendmessage("OutRoom/ok");
						break;
					}
				}
			}
		}

		private void sendmessage(String msg) {
			try {
				dataOutputStream.writeUTF(msg);
				dataOutputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	// 내부클래스
	class RoomInfomation {
		// 룸 정보
		String roomName;
		Vector<UserInfomation> roomUservc = new Vector<UserInfomation>();
		
		public RoomInfomation(String roomName, UserInfomation u) {
			this.roomName = roomName;
			this.roomUservc.add(u);
			
			u.myCurrentRoomName = roomName;
		}

		private void roomBroadcast(String str) { // 현재방의 모든 사람들에게 알린다.
			for (int i = 0; i < roomUservc.size(); i++) {
				UserInfomation u = roomUservc.elementAt(i);
				u.sendmessage(str);
			}
		}

		private void addUser(UserInfomation u) {
			roomUservc.add(u);
		}
		@Override
		public String toString() {
			return roomName;
		}
		// 방 지움
		private void removeRoom(UserInfomation u) {
			roomUservc.remove(u);
			boolean empty = roomUservc.isEmpty();
			if (empty) {
				for (int i = 0; i < vcRoom.size(); i++) {
					RoomInfomation r = vcRoom.elementAt(i);
					if (r.roomName.equals(roomName)) {
						vcRoom.remove(this);
						broadCast("EmptyRoom/"+roomName);
						broadCast("UserData_Updata/ok");
						break;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		new ServerFile();
	}
}

