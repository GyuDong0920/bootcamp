package ch03;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyHouseFrame extends JFrame {

	MyHouseFramePanel myHouseFramePanel;

	public MyHouseFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("직접 그려보기 연습");
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		myHouseFramePanel = new MyHouseFramePanel();

	}

	private void setInitLayout() {
		setVisible(true);
		add(myHouseFramePanel);

	}

	public static void main(String[] args) {
		new MyHouseFrame();
	} // end of main

	// 내부클래스
	private class MyHouseFramePanel extends JPanel {
		public void paint(Graphics g) {
			super.paint(g); // 이거 그대로 둬야 제대로 동작함.
//			g.drawString("안녕 자바 2D", 100,200);
//			g.drawLine(20,30,100,100); // y좌표값은 (-)인듯
//			g.drawRect(100,100,150,150); // 사각형 그리기

			// 집 바디
			g.drawRect(200, 400, 200, 200);

			// 지붕
			g.drawLine(200, 400, 300, 300);
			g.drawLine(400, 400, 300, 300);

			// 유리창
			g.drawRect(250, 450, 100, 100);
			g.drawLine(250, 500, 350, 500);
			g.drawLine(300, 450, 300, 550);

			// 굴뚝
			g.drawLine(350, 350, 350, 300);
			g.drawLine(350, 300, 380, 300);
			g.drawLine(380, 300, 380, 380);

			// 별
			g.drawString("✰", 50, 380);
			g.drawString("✰", 100, 350);
			g.drawString("✰", 150, 300);
			g.drawString("✰", 200, 100);
			g.drawString("✰", 250, 30);
			g.drawString("✰", 300, 200);
			g.drawString("✰", 350, 150);
			g.drawString("✰", 80, 170);

			g.drawString("✰", 500, 100);
			g.drawString("✰", 550, 30);
			g.drawString("✰", 750, 200);
			g.drawString("✰", 600, 150);
			g.drawString("✰", 500, 170);
			g.drawString("✰", 720, 300);
			g.drawString("✰", 620, 300);
			g.drawString("✰", 520, 250);
			g.drawString("✰", 580, 200);
			g.drawString("✰", 400, 200);

		}

	} // end of MyHouseFramePanel
} // end of MyHouseFrame