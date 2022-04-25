package chatting;

import java.util.Vector;

import chatting.serverControllers.chatRoom;


public interface serverInterface {
	
	Vector<ClientList> vc = new Vector<ClientList>();
	Vector<chatRoom> chRoom = new Vector<chatRoom>();
}
