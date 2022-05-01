package chat.client;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class ClientProtocol {

	private ClientGUI mContext;
	private ClientNetWorkService netWorkService;
	private StringTokenizer st;
	
	
	public ClientProtocol(ClientGUI mContext, ClientNetWorkService netWorkService) {
		this.mContext = mContext;
		this.netWorkService = netWorkService;
	}
	
	public void inmessage(String str) {
		System.out.println("inmessage(cprotocol)" + str);
		st = new StringTokenizer(str, "/");

		String protocol = st.nextToken();
		String message = st.nextToken();

		System.out.println("프로토콜" + protocol);
		System.out.println("메세지" + message);

		if (protocol.equals("NewUser")) {
			mContext.userVclist.add(message);
			mContext.totalList.setListData(mContext.userVclist);
		} else if (protocol.equals("OldUser")) {
			mContext.userVclist.add(message);
			mContext.totalList.setListData(mContext.userVclist);
		} else if (protocol.equals("Note")) {
			st = new StringTokenizer(message, "@");
			String user = st.nextToken();
			String note = st.nextToken();
			JOptionPane.showMessageDialog(null, note, user + "로 부터 온 메세지", JOptionPane.CLOSED_OPTION);
		} else if (protocol.equals("CreateRoom")) {
			// 방만들기가 성공했을 경우

			StringTokenizer s = new StringTokenizer(message, "@");
			String useId = s.nextToken();
			String roomName1 = s.nextToken();
			//clientGUI.setMyRoomName(message); // clientGui messgae를 통으로 받기 때문에 b@c
			
		} else if (protocol.equals("CreateRoomFail")) {
			JOptionPane.showMessageDialog(null, "같은 방 이름이 존재합니다.!", "알림", JOptionPane.ERROR_MESSAGE);
		} else if (protocol.equals("new_Room")) {
			mContext.roomListvc.add(message);
			mContext.roomList.setListData(mContext.roomListvc);
		} else if (protocol.equals("Chatting")) {
			String msg = st.nextToken();
			mContext.getViewChatTextArea().append(message + " : " + msg + "\n");
		} else if (protocol.equals("OldRoom")) {
			mContext.roomListvc.add(message);
			mContext.roomList.setListData(mContext.roomListvc);
		} else if (protocol.equals("JoinRoom")) {
			mContext.setMyRoomName(message);
			JOptionPane.showMessageDialog(null, "채팅방 (  " + mContext.getMyRoomName() + " ) 에 입장완료", "알림",
					JOptionPane.INFORMATION_MESSAGE);
			mContext.getViewChatTextArea().setText("");
		} else if (protocol.equals("UserOut")) {
			mContext.userVclist.remove(message);
			mContext.getNetWorkService().sendmessage("OutRoom/" + mContext.getMyRoomName());
		} else if (protocol.equals("UserData_Updata")) {
			mContext.totalList.setListData(mContext.userVclist);
			mContext.roomList.setListData(mContext.roomListvc);
		} else if (protocol.equals("OutRoom")) {
			mContext.getViewChatTextArea().append("*** (( " + mContext.getMyRoomName() + "에서 퇴장 ))***\n");
			mContext.setMyRoomName(null);
			mContext.getMakeRoomBtn().setEnabled(true);
			mContext.getOutRoomBtn().setEnabled(false);
		} else if (protocol.equals("EmptyRoom")) {
			mContext.roomListvc.remove(message);
			// 클라이언트가 강제 종료 되었고 방이 비었을때 방 목록에서 그 방을 없애준다.
		} else if (protocol.equals("ErrorOutRoom")) {
			mContext.roomListvc.remove(message);
		}
	}

}
