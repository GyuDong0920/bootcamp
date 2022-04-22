package tenco.com.test_04;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {

	private int x;
	private int y;
	
	private ImageIcon playerR;
	private ImageIcon playerL;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initsettting() {
		x = 80;
		y = 500;
		
		setIcon(playerR);
		setSize(playerR( 50, 50));
	}

	private Dimension playerR(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
}
