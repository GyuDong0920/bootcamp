package chatting;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame implements ActionListener {

	// GUI자원
	private JPanel mainPanel;
	private JTextField hostIPTextField;
	private JTextField portTextField;
	private JTextField userIDTextField;
	private JTextField chattingTextField;
	private JTextArea viewChatTextArea;
	private JButton connectBtn;
	private JButton confirmBtn;
	private JButton sendNoteBtn;
	private JButton joinRoomBtn;
	private JList totalList; // 전체접속자 리스트
	private JList roomUserList; // 전체접속자 리스트
	private JList roomList; // 방 리스트
	private JButton makeRoomBtn;
	private JButton outRoomBtn;
	private JButton endBtn;
	private JPanel panel;

	// network 자원
	private Socket socket;
	private String ip;
	private int port;
	private String userId;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	// 그외 변수들
	private Vector<String> userVclist = new Vector<String>();
	private Vector<String> roomListvc = new Vector<String>();
	private StringTokenizer st;
	private String myRoomName;

	public Client() {
		
		init();
		addListener();
	}

	private void init() {
		setTitle("KakaoTalk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		setBounds(0, 0, 400, 650);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JTabbedPane Jtab = new JTabbedPane(JTabbedPane.TOP);
		Jtab.setBounds(5, 5, 375, 600);
		mainPanel.add(Jtab);

		panel = new JPanel();
		Jtab.addTab("로그인", null, panel, null);
		panel.setLayout(null);

		JLabel hostIP_lbl = new JLabel("Server_IP");
		hostIP_lbl.setFont(new Font("나눔고딕", Font.BOLD, 13));
		hostIP_lbl.setBounds(12, 25, 91, 15);
		panel.add(hostIP_lbl);

		hostIPTextField = new JTextField();
		hostIPTextField.setFont(new Font("나눔고딕", Font.BOLD, 13));
		hostIPTextField.setBounds(112, 21, 199, 21);
		panel.add(hostIPTextField);
		hostIPTextField.setColumns(10);

		JLabel port_lbl = new JLabel("Server_Port");
		port_lbl.setFont(new Font("나눔고딕", Font.BOLD, 13));
		port_lbl.setBounds(12, 72, 91, 15);
		panel.add(port_lbl);

		portTextField = new JTextField();
		portTextField.setFont(new Font("나눔고딕", Font.BOLD, 13));
		portTextField.setBounds(112, 69, 199, 21);
		panel.add(portTextField);
		portTextField.setColumns(10);

		JLabel userID_lbl = new JLabel("User_ID");
		userID_lbl.setFont(new Font("나눔고딕", Font.BOLD, 13));
		userID_lbl.setBounds(12, 122, 91, 15);
		panel.add(userID_lbl);

		userIDTextField = new JTextField();
		userIDTextField.setBounds(112, 119, 199, 21);
		panel.add(userIDTextField);
		userIDTextField.setColumns(10);

		connectBtn = new JButton("접 속");
		connectBtn.setFont(new Font("나눔고딕", Font.BOLD, 12));
		connectBtn.setBounds(214, 162, 97, 23);
		panel.add(connectBtn);

		JPanel panel = new JPanel();
		Jtab.addTab("대기실", null, panel, null);
		panel.setLayout(null);

		JLabel totalList_lbl = new JLabel("접속 중인 유저");
		totalList_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalList_lbl.setFont(new Font("나눔고딕", Font.BOLD, 13));
		totalList_lbl.setBounds(265, 370, 100, 15);
		panel.add(totalList_lbl);

		JLabel roomList_lbl = new JLabel("Room");
		roomList_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		roomList_lbl.setFont(new Font("나눔고딕", Font.BOLD, 13));
		roomList_lbl.setBounds(-20, 5, 100, 15);
		panel.add(roomList_lbl);

		totalList = new JList();
		totalList.setBounds(265, 390, 100, 150);
		panel.add(totalList);

		roomList = new JList();
		roomList.setBounds(10, 25, 350, 335);
		panel.add(roomList);

		sendNoteBtn = new JButton("Message");
		sendNoteBtn.setFont(new Font("나눔고딕", Font.BOLD, 12));
		sendNoteBtn.setBounds(265, 545, 100, 25);
		panel.add(sendNoteBtn);

		joinRoomBtn = new JButton("채팅방참여");
		joinRoomBtn.setFont(new Font("나눔고딕", Font.BOLD, 12));
		joinRoomBtn.setBounds(10, 365, 100, 25);
		panel.add(joinRoomBtn);
		hostIPTextField.setText("127.0.0.1");

		JPanel panel_2 = new JPanel();
		Jtab.addTab("채팅", null, panel_2, null);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		panel_2.add(scrollPane);
		
		viewChatTextArea = new JTextArea();
		viewChatTextArea.setEnabled(false);
		viewChatTextArea.setEditable(false);
		viewChatTextArea.setFont(new Font("나눔고딕", Font.BOLD, 12));
		viewChatTextArea.setBounds(10, 10, 350, 337);
		scrollPane.add(viewChatTextArea);
		panel_2.add(viewChatTextArea);
		
		chattingTextField = new JTextField();
		chattingTextField.setFont(new Font("나눔고딕", Font.BOLD, 11));
		chattingTextField.setBounds(10, 360, 250, 25);
		panel_2.add(chattingTextField);
		chattingTextField.setColumns(10);

		confirmBtn = new JButton("전 송");
		confirmBtn.setFont(new Font("나눔고딕", Font.BOLD, 12));
		confirmBtn.setBounds(270, 360, 97, 25);
		panel_2.add(confirmBtn);

		makeRoomBtn = new JButton("방 만들기");
		makeRoomBtn.setFont(new Font("나눔고딕", Font.BOLD, 11));
		makeRoomBtn.setBounds(120, 365, 100, 25);
		panel.add(makeRoomBtn);

		roomUserList = new JList();
		roomUserList.setBounds(10, 400, 120, 150);
		panel_2.add(roomUserList);
		
		outRoomBtn = new JButton("방 나가기");
		outRoomBtn.setFont(new Font("나눔고딕", Font.BOLD, 12));
		outRoomBtn.setBounds(160, 540, 100, 25);
		panel_2.add(outRoomBtn);
		outRoomBtn.setEnabled(false);
		endBtn = new JButton("종료");
		endBtn.setFont(new Font("나눔고딕", Font.BOLD, 12));
		endBtn.setBounds(270, 540, 100, 25);
		panel_2.add(endBtn);
		setVisible(true);

	}

	private void connectServer() {
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

			userId = userIDTextField.getText().trim();
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
							inmessage(msg);
						} catch (IOException e) {
							try {
								userVclist.removeAll(userVclist);
								roomListvc.removeAll(roomListvc);
								totalList.setListData(userVclist);
								roomUserList.setListData(userVclist);
								roomList.setListData(roomListvc);
								viewChatTextArea.setText("\n");
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
		connectBtn.setEnabled(false);
	}

	private void inmessage(String str) {

		st = new StringTokenizer(str, "/");

		String protocol = st.nextToken();
		String message = st.nextToken();

		System.out.println("프로토콜" + protocol);
		System.out.println("메세지" + message);

		if (protocol.equals("NewUser")) {
			userVclist.add(message);
			totalList.setListData(userVclist);
		} else if (protocol.equals("OldUser")) {
			userVclist.add(message);
			totalList.setListData(userVclist);
		} else if (protocol.equals("Note")) {
			st = new StringTokenizer(message, "@");
			String user = st.nextToken();
			String note = st.nextToken();
			JOptionPane.showMessageDialog(null, note, user + "로 부터 온 메세지", JOptionPane.CLOSED_OPTION);
		} else if (protocol.equals("CreateRoom")) {
			// 방만들기가 성공했을 경우
			myRoomName = message;
			joinRoomBtn.setEnabled(false);
			outRoomBtn.setEnabled(true);
			makeRoomBtn.setEnabled(false);
		} else if (protocol.equals("CreateRoomFail")) {
			JOptionPane.showMessageDialog(null, "같은 방 이름이 존재합니다.!", "알림", JOptionPane.ERROR_MESSAGE);
		} else if (protocol.equals("new_Room")) {
			roomListvc.add(message);
			roomList.setListData(roomListvc);
		} else if (protocol.equals("Chatting")) {
			String msg = st.nextToken();
			viewChatTextArea.append(message + " : " + msg + "\n");
		} else if (protocol.equals("OldRoom")) {
			roomListvc.add(message);
			roomList.setListData(roomListvc);
		} else if (protocol.equals("JoinRoom")) {
			myRoomName = message;
			JOptionPane.showMessageDialog(null, "채팅방 (  " + myRoomName + " ) 에 입장완료", "알림",
					JOptionPane.INFORMATION_MESSAGE);
			viewChatTextArea.setText("");
		} else if (protocol.equals("UserOut")) {
			userVclist.remove(message);
			sendmessage("OutRoom/" + myRoomName);
		} else if (protocol.equals("UserData_Updata")) {
			totalList.setListData(userVclist);
			roomList.setListData(roomListvc);
		} else if (protocol.equals("OutRoom")) {
			viewChatTextArea.append("*** (( " + myRoomName + "에서 퇴장 ))***\n");
			myRoomName = null;
			makeRoomBtn.setEnabled(true);
			outRoomBtn.setEnabled(false);
		} else if (protocol.equals("EmptyRoom")) {
			roomListvc.remove(message);
			// 클라이언트가 강제 종료 되었고 방이 비었을때 방 목록에서 그 방을 없애준다.
		} else if (protocol.equals("ErrorOutRoom")) {
			roomListvc.remove(message);
		}
	}

	private void sendmessage(String msg) {
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 이벤트리스너
	private void addListener() {
		connectBtn.addActionListener(this);
		confirmBtn.addActionListener(this);
		sendNoteBtn.addActionListener(this);
		joinRoomBtn.addActionListener(this);
		chattingTextField.addActionListener(this);
		endBtn.addActionListener(this);
		makeRoomBtn.addActionListener(this);
		outRoomBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connectBtn) {
			if (hostIPTextField.getText().length() == 0) {
				hostIPTextField.setText("IP를 입력하세요");
				hostIPTextField.requestFocus();
			} else if (hostIPTextField.getText().length() == 0) {
				hostIPTextField.setText("포트번호를 입력하세요");
				hostIPTextField.requestFocus();
			} else if (userIDTextField.getText().length() == 0) {
				userIDTextField.setText("id 를 입력하세요");
				userIDTextField.requestFocus();
			} else {
				ip = hostIPTextField.getText();
				try {
					port = Integer.parseInt(portTextField.getText().trim());
				} catch (Exception e2) {
					portTextField.setText("잘못 입력하였습니다.");
				}
				userId = userIDTextField.getText().trim();
				// 서버연결하기
				connectServer();
				setTitle("[" + userId + " ] 님 KaKaoTalk에 오신걸 환경합니다.");
			}
		} else if (e.getSource() == confirmBtn) {
			System.out.println("전송버튼클릭");
			sendmessage("Chatting/" + myRoomName + "/" + chattingTextField.getText().trim());
		} else if (e.getSource() == sendNoteBtn) {
			System.out.println("쪽지보내기버튼 클릭");
			String user = (String) totalList.getSelectedValue();
			if (user == null) {
				JOptionPane.showMessageDialog(null, "대상을 선택하세요", "알림", JOptionPane.ERROR_MESSAGE);
			}
			String note = JOptionPane.showInputDialog("보낼메세지");
			if (note != null) {
				sendmessage("Note/" + user + "@" + note);
			}
		} else if (e.getSource() == joinRoomBtn)  {
			System.out.println("방입장버튼 클릭");
			String joinRoom = (String) roomList.getSelectedValue();
			outRoomBtn.setEnabled(true);
			makeRoomBtn.setEnabled(false);
			sendmessage("JoinRoom/" + joinRoom);
			roomUserList.setListData(userVclist);
		} else if (e.getSource() == chattingTextField) {
			if (chattingTextField.getText().length() == 0) {
				sendmessage("Chatting/" + myRoomName + "/" + chattingTextField.getText() + "   ");
			} else {
				sendmessage("Chatting/" + myRoomName + "/" + chattingTextField.getText());
			}
		} else if (e.getSource() == makeRoomBtn) {
			System.out.println("방생성버튼클릭");
			String roomName = JOptionPane.showInputDialog("방 이름을 입력하세요");
			roomUserList.getSize();
			if (roomName != null) {
				sendmessage("CreateRoom/" + roomName);
			}
		} else if (e.getSource() == outRoomBtn) {
			System.out.println("방나가기버튼클릭.");
			sendmessage("OutRoom/" + myRoomName);
			roomUserList.remove(roomUserList);
		} else if (e.getSource() == endBtn) {
			System.exit(0);
		}
		chattingTextField.setText("");
		}
	
	public static void main(String[] args) {
		new Client();
	}
}
