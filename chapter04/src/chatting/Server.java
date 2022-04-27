package chatting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Socket_ex_ch04.jFrame;

public class Server extends jFrame implements ActionListener{

	// GUI
	protected JTextField textField;
	protected JTextArea textArea;
	protected JButton startbutton;
	protected JButton stopButton;
	protected JButton savelogButton;
	protected JButton deletelogButton;
	protected JButton loadLogButton;
	protected JButton exitButton;
	private BufferedImage img = null;
	ScrollPane scrollPane2 = new ScrollPane();
	// network
	private ServerSocket serverSocket;
	private Socket socket;
	private int port;
	// List
	Vector<ClientList> vc = new Vector<ClientList>();
	Vector<chatRoom> chRoom = new Vector<chatRoom>();
	Vector v = new Vector();
	JList vList = new JList(v);

	public Server() {

		try {
			img = ImageIO.read(new File("images/kakao.png"));
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "이미지 불러오기 실패");

		}
		init();
		addListener();
		startbutton.requestFocus();
		stopButton.requestFocus();
		savelogButton.requestFocus();
		deletelogButton.requestFocus();
		exitButton.requestFocus();
		loadLogButton.requestFocus();
		textField.requestFocus();

	}

	private void init() {
		setTitle("KakaoNetService");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(380, 590);
		setResizable(false);

		myFrame panel = new myFrame();
		setContentPane(panel);
		panel.setLayout(null);
		panel.setBounds(0, 0, 370, 590);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 170, 235, 350);
		textArea = new JTextArea();
		textArea.setBounds(11, 171, 221, 351);
		scrollPane.add(textArea);
		panel.add(scrollPane);
		textArea.setEditable(false);

		scrollPane2.setBounds(255, 370, 100, 150);
		add(scrollPane2);
		scrollPane2.add(vList);

		textField = new JTextField();
		textField.setBounds(10, 130, 235, 25);
		panel.add(textField);
		textField.setColumns(6);
		textField.setText("Port Number");

		startbutton = new JButton("Sever ON");
		startbutton.setBounds(255, 130, 100, 25);
		startbutton.setBackground(new Color(59, 30, 30));
		startbutton.setForeground(Color.white);
		panel.add(startbutton);

		stopButton = new JButton("Sever OFF");
		stopButton.setBounds(255, 170, 100, 25);
		stopButton.setBackground(new Color(59, 30, 30));
		stopButton.setForeground(Color.white);
		panel.add(stopButton);
		stopButton.setEnabled(false);

		savelogButton = new JButton("Save Log");
		savelogButton.setBounds(255, 210, 100, 25);
		savelogButton.setBackground(new Color(59, 30, 30));
		savelogButton.setForeground(Color.white);
		panel.add(savelogButton);

		deletelogButton = new JButton("Delete Log");
		deletelogButton.setBounds(255, 250, 100, 25);
		deletelogButton.setBackground(new Color(59, 30, 30));
		deletelogButton.setForeground(Color.white);
		panel.add(deletelogButton);

		loadLogButton = new JButton("Load Log");
		loadLogButton.setBounds(255, 290, 100, 25);
		loadLogButton.setBackground(new Color(59, 30, 30));
		loadLogButton.setForeground(Color.white);
		panel.add(loadLogButton);

		exitButton = new JButton("Exit Server");
		exitButton.setBounds(255, 330, 100, 25);
		exitButton.setBackground(new Color(59, 30, 30));
		exitButton.setForeground(Color.white);
		panel.add(exitButton);

		String DATADIRECTORY = "C:\\bootcamp\\chapter04";
		File dir = new File(DATADIRECTORY);

		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File f) {

				return f.getName().endsWith(".txt");

			}
		};
		File files[] = dir.listFiles(filter);
		for (File file : files) {
			v.addElement(file);
			vList.setListData(v);

		}

		setVisible(true);

	}

	class myFrame extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	private void addListener() {
		textField.addActionListener(this);
		savelogButton.addActionListener(this);
		startbutton.addActionListener(this);
		stopButton.addActionListener(this);
		deletelogButton.addActionListener(this);
		loadLogButton.addActionListener(this);
		exitButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String txt = ".txt";
		String text = textArea.getText();
		String fileName = textField.getText() + txt;

		// 종료 버튼
		if (e.getSource() == exitButton) {
			System.exit(0);
		}

		// 서버 ON 을 눌렀을 때
		if (e.getSource() == startbutton) {
			try {

				if (textField.getText().length() == 0 || Integer.parseInt(textField.getText()) >= 65536) {
					textArea.append("0 ~ 65535 사이의 값을 입력해주세요.\n");

				} else if (textField.getText().length() != 0) {
					port = Integer.parseInt(textField.getText());
					setNetwork();
					textField.setEditable(false);
					startbutton.setEnabled(false);
					stopButton.setEnabled(true);

				}

			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "0 ~ 65536 사이의 값을 입력해주세요.\n");

			}

		} else if (e.getSource() == stopButton) {
			try {

				serverSocket.close();
				vc.removeAllElements();
				chRoom.removeAllElements();
				textField.setEditable(true);
				stopButton.setEnabled(false);
				startbutton.setEnabled(true);
				
			} catch (IOException e1) {
				startbutton.setEnabled(true);
				
			}
		} else if (e.getSource() == savelogButton) {

			textArea.append(fileName + " 내용이 저장되었습니다.\n");
			v.add(fileName);
			vList.setListData(v);

			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
				bw.write(text);
				bw.flush();
				bw.close();
			} catch (IOException e1) {

			}
			// 파일 삭제 기능 추가해야함!
		} else if (e.getSource() == deletelogButton) {
			if (v.size() == 0) {
				textArea.append("파일이 없습니다.\n");
			}
			try {
				int index = vList.getSelectedIndex();
				v.remove(index);
				vList.ensureIndexIsVisible(index);
			} catch (Exception e2) {
				textArea.append("파일을 선택해주세요 \n");
			}
			// 파일을 선택해서 불러와야함
		} else if (e.getSource() == loadLogButton) {

			Scanner scanner;
			try {
				scanner = new Scanner(new File("C:\\bootcamp\\chapter04\\214.txt"));
				while (scanner.hasNext()) {
					String str = scanner.next();
					textArea.append(str + "\n");
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	private void setNetwork() {

		try {
			// 서버소켓 생성과 동시에 포트에 연결
			serverSocket = new ServerSocket(port);
			textArea.append("서버를 실행하겠습니다.\n");
			connect();
		} catch (IOException e) {
			// 예외처리 메세지 문구가 뜨는 창
			JOptionPane.showMessageDialog(null, "이미 사용중인 포트입니다.", "Error", JOptionPane.ERROR_MESSAGE);
			startbutton.setEnabled(true);
			stopButton.setEnabled(false);
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
						textArea.append(textField.getText() + "port" + " 접속 대기중...\n");
						socket = serverSocket.accept();

						ClientList clientList = new ClientList(socket);
						clientList.start();
					} catch (IOException e) {
						textArea.append("서버 중지 @Server ON 버튼을 눌러주세요.\n");
						break;

					}
				}

			}
		});
		th.start();
	}

	public void announCement(String str) {
		for (int i = 0; i < vc.size(); i++) {
			ClientList clientList = vc.elementAt(i);
			clientList.sendMessage(str);

		}
	}

	class ClientList extends Thread {
		private InputStream inputStream; // 데이터가 들어오는 통로 byte 단위로
		private OutputStream outputStream; // 데이터가 나가는 통로 byte 단위로
		private DataInputStream dataInputStream; // 데이터를 변환 해주는 클래스
		private DataOutputStream dataOutputStream; // 데이터를 변환 해주는 클래스
		String name;
		String chroomName;
		private Socket userSocket;

		private boolean checkRoom = true; // 방이 이미 만들어져있는지 췤

		public ClientList(Socket socket) {
			this.userSocket = socket;
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
				textArea.append("<<" + name + ">> 입장\n");

				// 기존 유저에게 알림
				announCement("NewUser/" + name);

				// 자신에게 현재 접속 중인 유저를 알린다.
				for (int i = 0; i < vc.size(); i++) {
					ClientList clientList = vc.elementAt(i);
					sendMessage("OldUser/" + clientList.name);
				}
				for (int i = 0; i < chRoom.size(); i++) {
					chatRoom room = chRoom.elementAt(i);
					sendMessage("OldRoom/" + room.roomName);
				}
				// 사용자에게 본인임을 알린 뒤 리스트에 추가한다.
				vc.add(this);

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Straem set Error", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					String msg = dataInputStream.readUTF();
					textArea.append("<<" + name + ">>" + msg + "\n");
					inmessage(msg);
				} catch (Exception e) {
					try {
						// 나갔음
						textArea.append(name + ": 사용자 접속이 끊어졌습니다.\n");
						dataOutputStream.close();
						dataInputStream.close();
						userSocket.close();
						vc.remove(this);
						chRoom.remove(this);
						announCement("UserOut/" + name);
						announCement("ErrorOutRoom/" + chroomName);
						announCement("UserData_Updata/ok");
						break;
					} catch (IOException e1) {
						break;
					}

				}

			} 
		}

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
					ClientList clientList = vc.elementAt(i);
					// 쪽지는 반드시 찾은 사용자에게 메세지를 보내줘어야 한다.
					if (clientList.name.equals(user)) {
						clientList.sendMessage("Note/" + name + "@" + note);

					}
				}
			} else if (protocol.equals("CreateRoom")) {
				// 1.현재같은방이 존재하는지 확인한다.
				for (int i = 0; i < chRoom.size(); i++) {
					chatRoom room = chRoom.elementAt(i);
					if (room.roomName.equals(message)) {
						sendMessage("CreateRoomFail/ok");
						checkRoom = false;
						break;
					} else {
						checkRoom = true;
					}
				}
			
			if (checkRoom == true) {
				// 1.방을 생성한다.
				chatRoom newroom = new chatRoom(message, this);
				// 2. 리스트에 생성된 방을 저장한다.
				chRoom.add(newroom);
				// 3.사용자들에게 방과 방이름을 생성되었다고 알려준다.
				sendMessage("CreateRoom/" + message);
				announCement("new_Room/" + message);
			}
			} else if (protocol.equals("Chatting")) {
				String msg = st.nextToken();
				for (int i = 0; i < chRoom.size(); i++) {
					chatRoom room = chRoom.elementAt(i);
					if (room.roomName.equals(message)) {
						room.roomAnnounCement("Chatting/" + name + "/" + msg);

					}
				}
			} else if (protocol.equals("JoinRoom")) {
				for (int i = 0; i < chRoom.size(); i++) {
					chatRoom room = chRoom.elementAt(i);
					if (room.roomName.equals(message)) {
						// 신규 접속자 알림
						room.roomAnnounCement("Chatting/[알림]/ <<<" + name + "입장 >>>");
						room.addUser(this); // 룸에 자신을 추가
						sendMessage("JoinRoom/" + message);
					}
				}
			} else if (protocol.equals("OutRoom")) {
				for (int i = 0; i < chRoom.size(); i++) {
					chatRoom room = chRoom.elementAt(i);
					if (room.roomName.equals(message)) {
						room.deleteRoom(this);
						sendMessage("OutRoom/ok");
						break;

					}
				}
			}
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

	class chatRoom {
		String roomName;
		Vector<ClientList> roomUservc = new Vector<ClientList>();
		public chatRoom(String roomName, ClientList clientList) {
			this.roomName = roomName;
			this.roomUservc.add(clientList);

			clientList.chroomName = roomName;
		}

		private void roomAnnounCement(String str) {
			for (int i = 0; i < chRoom.size(); i++) {
				ClientList room = roomUservc.elementAt(i);
				room.sendMessage(str);
			}
		}

		private void addUser(ClientList clientList) {
			roomUservc.add(clientList);
		}

		@Override
		public String toString() {
			return roomName;
		}

		private void deleteRoom(ClientList clientList) {
			roomUservc.remove(clientList);
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



	public static void main(String[] args) {
		new Server();
	}

}
