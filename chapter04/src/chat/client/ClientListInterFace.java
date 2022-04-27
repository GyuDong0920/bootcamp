package chat.client;

import java.util.Vector;

import javax.swing.JList;

public interface ClientListInterFace {

	Vector<String> userVclist = new Vector<String>();
	Vector<String> roomListvc = new Vector<String>();
	public static final JList totalList = new JList();
	public static final JList roomUserList = new JList();
	public static final JList roomList = new JList();
}
