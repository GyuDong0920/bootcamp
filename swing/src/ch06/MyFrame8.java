package ch06;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;

import ch03.MyImageFrame2.MyImagePanel;

public class MyFrame8 extends JFrame implements ActionListener {

	// !!! 변수의 효용 !!!

	private static int X = 0;
	private static int Y = 0;

	static BufferedImage backgroudImage;
	static BufferedImage imageIcon;
	static MyImagePanel imagePanel;

	// 내부 클래스 선언 - paint

	// 이벤트 리스너 등록 1, 2, 3
	public MyFrame8() {
		initData();
		setInitLayout();
		addEventListener();

	}

	private void addEventListener() {
	}

	private void initData() {
		setTitle("이미지 백그라운드 연습");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			backgroudImage = ImageIO.read(new File("image1.jpg"));
			imageIcon = ImageIO.read(new File("icon2.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
			e.printStackTrace();
		}

		imagePanel = new MyImagePanel();
	}


	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		add(imagePanel);

	}

	public class MyImagePanel extends JPanel implements KeyListener {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroudImage, X, Y, 500, 500, null);
			g.drawImage(imageIcon, X, Y, 100, 100, null);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			int k = e.getKeyCode();
			switch (k) {
			case KeyEvent.VK_LEFT:
				X -= 10;
				repaint();
				break;
			case KeyEvent.VK_RIGHT:
				X += 10;
				repaint();
				break;
			case KeyEvent.VK_UP:
				Y -= 10;
				repaint();
				break;
			case KeyEvent.VK_DOWN:
				Y += 10;
				repaint();
				break;

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void actionPerformed(ActionEvent e) {

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new MyFrame8();
	}

}
