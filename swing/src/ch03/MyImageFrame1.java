package ch03;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;


	
}


public class MyImageFrame1 extends JFrame {
	
	
	public MyImageFrame1() {
		initData();
		setInitLayout();
		
	}

	private void initData() {
		setTitle("JPanel 에 이미지 넣기");
		setSize(500, 500);
		
	
	}
	
	private void setInitLayout() {
		setVisible(true);
		add(new imagePanel());
	}
	class imagePanel extends JPanel {

		private Image image;
		
		public imagePanel() {
			image = new imageIcon("image1.jpg").		
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}
	}
	public static void main(String[] args) {
		new MyImageFrame1();
	}
}
	

