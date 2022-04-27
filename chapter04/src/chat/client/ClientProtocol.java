package chat.client;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class ClientProtocol implements ClientListInterFace{

	private StringTokenizer st;
	
	private ClientGUI clientGUI;
	private ClientNetWorkService netWorkService;
	
	public ClientProtocol(ClientGUI gui, ClientNetWorkService service) {
		clientGUI = gui;
		netWorkService = service;
	}
	
	public void inmessage(String str) {
		System.out.println("inmessage(cprotocol)" + str);
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
			clientGUI.setMyRoomName(message);
			clientGUI.getJoinRoomBtn().setEnabled(false);
			clientGUI.getOutRoomBtn().setEnabled(true);
			clientGUI.getMakeRoomBtn().setEnabled(false);
		} else if (protocol.equals("CreateRoomFail")) {
			JOptionPane.showMessageDialog(null, "같은 방 이름이 존재합니다.!", "알림", JOptionPane.ERROR_MESSAGE);
		} else if (protocol.equals("new_Room")) {
			roomListvc.add(message);
			roomList.setListData(roomListvc);
			
			
		} else if (protocol.equals("Chatting")) {
			String msg = st.nextToken();
			clientGUI.getViewChatTextArea().append(message + " : " + msg + "\n");
		} else if (protocol.equals("OldRoom")) {
			roomListvc.add(message);
			roomList.setListData(roomListvc);
		} else if (protocol.equals("JoinRoom")) {
			clientGUI.setMyRoomName(message);
			JOptionPane.showMessageDialog(null, "채팅방 (  " + clientGUI.getMyRoomName() + " ) 에 입장완료", "알림",
					JOptionPane.INFORMATION_MESSAGE);
			clientGUI.getViewChatTextArea().setText("");
		} else if (protocol.equals("UserOut")) {
			userVclist.remove(message);
			netWorkService.sendmessage("OutRoom/" + clientGUI.getMyRoomName());
		} else if (protocol.equals("UserData_Updata")) {
			totalList.setListData(userVclist);
			roomList.setListData(roomListvc);
		} else if (protocol.equals("OutRoom")) {
			clientGUI.getViewChatTextArea().append("*** (( " + clientGUI.getMyRoomName() + "에서 퇴장 ))***\n");
			clientGUI.setMyRoomName(null);
			clientGUI.getMakeRoomBtn().setEnabled(true);
			clientGUI.getOutRoomBtn().setEnabled(false);
		} else if (protocol.equals("EmptyRoom")) {
			roomListvc.remove(message);
			// 클라이언트가 강제 종료 되었고 방이 비었을때 방 목록에서 그 방을 없애준다.
		} else if (protocol.equals("ErrorOutRoom")) {
			roomListvc.remove(message);
		}
	}

}
