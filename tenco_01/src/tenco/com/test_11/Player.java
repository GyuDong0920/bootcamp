package tenco.com.test_11;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {

	// 위치 상태
	private int x;
	private int y;

	// 플레이어의 방향
	private PlayerWay playerWay;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 플레이어 속도 상태
	private final int SPEDD = 4;
	private final int JUMP = 2;

	// 벽에 충돌한 상태
	private boolean LeftWallcrash;
	private boolean RightWallcrash;
	private boolean LeftTopcrash;

	private boolean RightTopcrash;

	private ImageIcon playerR;
	private ImageIcon playerL;
	private ImageIcon playerL2;
	private ImageIcon jmplm;
	private ImageIcon chjm;

	public Player() {
		initObject();
		initsettting();
		initBackgroundPlayerService();
	}

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();

	}

	private void initObject() {
		playerR = new ImageIcon("jumpking/chR1.png");

		playerL = new ImageIcon("jumpking/chL1.png");
		playerL2 = new ImageIcon("jumpking/leftwalking.png");

	}

	private void initsettting() {
		x = 70;
		y = 790;

		left = false;
		right = false;
		up = false;
		down = false;

		LeftWallcrash = false;
		RightWallcrash = false;
		LeftTopcrash = false;
		RightTopcrash = false;

		playerWay = PlayerWay.RIGHT;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	// 이벤트 핸들러
	@Override
	public void left() {
		System.out.println("left");
		playerWay = PlayerWay.LEFT;
		left = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					setIcon(playerL2);
					x = x - SPEDD;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();

					}
					setIcon(playerL);
				}

			}
		}).start();

	}

	@Override
	public void right() {
		System.out.println("right");
		playerWay = PlayerWay.RIGHT;
		right = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					setIcon(playerR);
					x = x + SPEDD;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	// left + up , right + up
	@Override
	public void up() {
		System.out.println("up");
		up = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < (130 / JUMP); i++) {
					y = y - JUMP;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				up = false;
				down();
			}
		}).start();
	}

	@Override
	public void down() {
		System.out.println("down");
		down = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (down) {
					y = y + JUMP;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {

					}

				}
				down = false;
			}
		}).start();
	}
 
}
