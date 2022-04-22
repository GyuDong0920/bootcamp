package ch01;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame{

	
		public MyFrame ( ) {
			super.setTitle("MyFrame");
			super.setSize(500, 500);
			super.setVisible(true);
		}
		
		
		
public static void main(String[] args) {
			
		String title = JOptionPane.showInputDialog("입력값 받기");
	   System.out.println(title);
	} // end of main

} // end of class
