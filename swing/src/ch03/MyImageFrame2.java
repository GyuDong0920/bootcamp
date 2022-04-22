package ch03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyImageFrame2 extends JFrame {

	// 코드를 수정해 주세요
	// 상수, static 활용해서 처리해주세요
	private static final int X = 0;
	private static final int Y = 0;

	static BufferedImage backgroudImage;
	static BufferedImage imageIcon;
	static MyImagePanel imagePanel;
	private JButton button;

	public MyImageFrame2() {
		initData();
		setinitLayout();
	}

	void initData() {
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

	void setinitLayout() {
		setVisible(true);
		setResizable(false);
		add(imagePanel);
	}

	public class MyImagePanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 이미지 2개를 그려주는 기능을 완료
			g.drawImage(backgroudImage, X, Y, 500, 500, null);
			g.drawImage(imageIcon, X, Y, 100, 100, null);
		}

	} // end of inner class

	public static void main(String[] args) {
		new MyImageFrame2();

	}

} // end of outer class
