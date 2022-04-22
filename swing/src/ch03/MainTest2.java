package ch03;

import java.awt.Graphics;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import ch03.MyImageFrame2.MyImagePanel;

class paint1 extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

}

public class MainTest2 extends JFrame {

	static MyImageFrame2 frame2;

	public static void main(String[] args) {

		// 도전과제
		// 메인함수 외부에서 파일을 전달 받아서 넣어 주세요
		// 여기서 파일명을 선언해서 전달 후에 코드가 동작하게 수정!!

		frame2.initData();
		frame2.setinitLayout();
		new MyImageFrame2();
		
		try {
			BufferedImage backgroudImage = ImageIO.read(new File("image1.jpg"));
			BufferedImage imageIcon = ImageIO.read(new File("icon2.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
			e.printStackTrace();
		}

		
	}

	JTable jt = new JTable();
	
}
