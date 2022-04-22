package thread_ex;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyMiniGame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	BufferedImage bgimage;
	BufferedImage moveimage2;
	BufferedImage moveimage3;
	CustomJpanel jpanel;
	buttonpanel bpanel;
	BorderLayout borderLayout;
	GridLayout gridLayout;

	String bgImageName = "park.png";
//	String moveimage2Name = "car1.png";
	String moveimage3Name = "car2.png";

	ImageIcon icd = new ImageIcon("car1.png");
	ImageIcon icl = new ImageIcon("car1l.png");
	ImageIcon icr = new ImageIcon("car1r.png");
	ImageIcon icu = new ImageIcon("car1u.png");
	Image img = icd.getImage();
	
	private JButton startButton;
	private JButton stopButton;
	
	boolean stopFlag = false;

	int xPoint = 200;
	int yPoint = 200;
	int xPoint2 = 310;
	int yPoint2 = 120;

	public MyMiniGame() {
		initData();
		setInitLayout();
		addEventListener();

		new Thread(jpanel).start();
	}

	private void initData() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			bgimage = ImageIO.read(new File(bgImageName));
//			moveimage2 = ImageIO.read(new File(moveimage2Name));
			moveimage3 = ImageIO.read(new File(moveimage3Name));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
		jpanel = new CustomJpanel();
		bpanel = new buttonpanel();
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		borderLayout = new BorderLayout();
		gridLayout = new GridLayout();
	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		add(bpanel, borderLayout.SOUTH);
		bpanel.setLayout(new GridLayout(1,2));
		bpanel.add(startButton);
		bpanel.add(stopButton);
		add(jpanel);

	}

	private synchronized void addEventListener() {
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				int k = e.getKeyCode();
				if (k == KeyEvent.VK_UP) {
					img = icu.getImage();
				} else if (k == KeyEvent.VK_DOWN) {
					img = icd.getImage();
				} else if (k == KeyEvent.VK_RIGHT) {
					img = icr.getImage();
				} else if (k == KeyEvent.VK_LEFT) {
					img = icl.getImage();

				}

				if (k == KeyEvent.VK_UP) {
					yPoint = (yPoint < 0) ? 0 : yPoint - 10;
					System.out.println(yPoint);
				} else if (k == KeyEvent.VK_DOWN) {
					yPoint = (yPoint > 450) ? 450 : yPoint + 10;
					System.out.println(yPoint);
				} else if (k == KeyEvent.VK_LEFT) {
					xPoint = (xPoint < 0) ? 0 : xPoint - 10;
					System.out.println(yPoint);
				} else if (k == KeyEvent.VK_RIGHT) {
					xPoint = (xPoint > 500) ? 500 : xPoint + 10;
					System.out.println(yPoint);
				}
				repaint();

			}
		});
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				JButton temp = (JButton) e.getSource();
				System.out.println(temp.getText());
				if(temp.getText().equals("Start")) {
					synchronized (jpanel) {
						stopFlag = false;
						jpanel.notify();
					}
				}
			}
		});
		stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e);
				JButton temp = (JButton) e.getSource();
				System.out.println(temp.getText());
				if(temp.getText().equals("Stop")) {
					stopFlag = true;
				}
			}
		});
	}

	private class CustomJpanel extends JPanel implements Runnable {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(bgimage, 0, 0, getWidth(), getHeight(), null);
//			g.drawImage(moveimage2, xPoint + 50, yPoint, 50, 90, null);
			g.drawImage(moveimage3, xPoint2, yPoint2, 70, 120, null);
			g.drawImage(img, xPoint, yPoint, 70, 90, this);

		}

		@Override
		public void run() {
			boolean direction = true;

			while (true) {
				repaint();
				if (direction) {
					yPoint2 += 10;
				} else {
					yPoint2 -= 10;
				}
				if (yPoint2 == 200) {
					direction = false;
				}
				if (yPoint2 == 100) {
					direction = true;
				}
				try {
					if(stopFlag == true) {
						synchronized (this) {
							this.wait();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}

	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
//		requestFocusInWindow();
		
		
	}

	private class buttonpanel extends JPanel {
		
	}

	public static void main(String[] args) {
		new MyMiniGame();
	}

}
