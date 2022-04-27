package chat.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientGUI extends JFrame implements ActionListener, ClientListInterFace {

	// GUI
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
	private JButton makeRoomBtn;
	private JButton outRoomBtn;
	private JButton endBtn;
	private JPanel panel;
	private String myRoomName;

	private ClientNetWorkService netWorkService;

	public ClientGUI() {
		netWorkService = new ClientNetWorkService(this);
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

		totalList.setBounds(265, 390, 100, 150);
		panel.add(totalList);

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
				netWorkService.setIp(hostIPTextField.getText());
				try {
					netWorkService.setPort(Integer.parseInt(portTextField.getText().trim()));
				} catch (Exception e2) {
					portTextField.setText("잘못 입력하였습니다.");
				}
				netWorkService.setUserId(userIDTextField.getText().trim());
				// 서버연결하기
				netWorkService.connectServer();
				setTitle("[" + netWorkService.getUserId() + " ] 님 KaKaoTalk에 오신걸 환경합니다.");
			}
		} else if (e.getSource() == confirmBtn) {
			System.out.println("전송버튼클릭");
			netWorkService.sendmessage("Chatting/" + myRoomName + "/" + chattingTextField.getText().trim());
		} else if (e.getSource() == sendNoteBtn) {
			System.out.println("쪽지보내기버튼 클릭");
			String user = (String) totalList.getSelectedValue();
			if (user == null) {
				JOptionPane.showMessageDialog(null, "대상을 선택하세요", "알림", JOptionPane.ERROR_MESSAGE);
			}

			String note = JOptionPane.showInputDialog("보낼메세지");
			if (note != null) {
				netWorkService.sendmessage("Note/" + user + "@" + note);
			}
		} else if (e.getSource() == joinRoomBtn) {
			System.out.println("방입장버튼 클릭");
			String joinRoom = (String) roomList.getSelectedValue();
			outRoomBtn.setEnabled(true);
			makeRoomBtn.setEnabled(false);
			netWorkService.sendmessage("JoinRoom/" + joinRoom);
			roomUserList.setListData(userVclist);
		} else if (e.getSource() == chattingTextField) {
			if (chattingTextField.getText().length() == 0) {
				netWorkService.sendmessage("Chatting/" + myRoomName + "/" + chattingTextField.getText() + "   ");
			} else {
				netWorkService.sendmessage("Chatting/" + myRoomName + "/" + chattingTextField.getText());
			}
		} else if (e.getSource() == makeRoomBtn) {
			System.out.println("방생성버튼클릭");
			String roomName = JOptionPane.showInputDialog("방 이름을 입력하세요");
			
			if (roomName != null) {
				netWorkService.sendmessage("CreateRoom/" + roomName + "@" + netWorkService.getUserId());
			}
		} else if (e.getSource() == outRoomBtn) {
			System.out.println("방나가기버튼클릭.");
			netWorkService.sendmessage("OutRoom/" + myRoomName);
			roomUserList.remove(roomUserList);
		} else if (e.getSource() == endBtn) {
			System.exit(0);
		}
		chattingTextField.setText("");
	}

	public static void main(String[] args) {
		new ClientGUI();
	}
}
