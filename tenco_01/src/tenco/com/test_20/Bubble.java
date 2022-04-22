package tenco.com.test_20;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	private BubbleFrame mContext;
	// 2단계
	private Bubble bubbleContext = this;
	// 포함관계
	// 의존성 컴포지션
	private Player player;
	// ----------------------
	private BackgroundBubbleObserver backgroundBubbleObserver;
	// Array
	private Enemy enemy;

	// 위치 상태
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태
	private int state; // 0. 기본, 1. 적군 가둠

	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태

//의존 주입 --> 생성자의 주입을 받는다.
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
		initThread();
	}

	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		backgroundBubbleObserver = new BackgroundBubbleObserver(this);
	}

	private void initSetting() {
		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);

		state = 0;
	}

	private void initThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (player.getPlayerWay() == PlayerWay.LEFT) {
					left();
				} else {
					right();
				}
			}
		}).start();
	}

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 250; i++) {
			x--;
			setLocation(x, y);
			// 현재 색상 체크 (메서드 호출)
			if (backgroundBubbleObserver.checkLeftWall()) {
				left = false;
				break;
			}
			if ((Math.abs(x - enemy.getX()) < 10) && (Math.abs(y - enemy.getY()) > 0)
					&& (Math.abs(y - enemy.getY()) < 50)) {
				System.out.println("물방울이 적군과 충돌");
				if (enemy.isLive()) {
					attack();
					break;
				}
			}
			threadSleep(1);

		}
		left = false;
		up();

	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 250; i++) {
			x++;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkRightWall()) {
				right = false;
				break;
			}
			if ((Math.abs(x - enemy.getX()) < 10) && (Math.abs(y - enemy.getY()) > 0)
					&& (Math.abs(y - enemy.getY()) < 50)) {
				System.out.println("물방울이 적군과 충돌");
				if (enemy.isLive()) {
					attack();
					break;
				}
			}
			threadSleep(1);
		}
		right = false;
		up();
	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkTopWall()) {
				up = false;
				break;
			}

			if (state == 0) {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
		up = false;
		if (state == 0) {
			removeBubble();
		}
	}

	public void removeBubble() {
		try {
			Thread.sleep(2000);
			setIcon(bomb);
			Thread.sleep(1000);
			bubbleContext = null;
			setIcon(null);

//			mContext.remove(this); // 메모리에서 제거
//			mContext.repaint(); // 도화지에서 그림을 다시 그려라
//			

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	public void removenowBubble() {
		try {
			Thread.sleep(1);
			setIcon(bomb);
			Thread.sleep(1);
			bubbleContext = null;
			setIcon(null);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void attack() {
		state = 1;
		setIcon(bubbled);

		// 적군에 살아 있다, 죽었다 상태값 부여

		if ((Math.abs(x - enemy.getX()) < 10) && (Math.abs(y - enemy.getY()) > 0)
				&& (Math.abs(y - enemy.getY()) < 50)) {
			enemy.setLive(false);
			mContext.remove(enemy);
			mContext.repaint();

		}

		// 메모리에 지우기

		// 가비지 컬렉터가 즉시 발동되지 않음!!!

	}

	private void threadSleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}