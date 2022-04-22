package tenco.com.test_20;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BubbleFrame extends JFrame {

	// 1단계
	// 주소를 담는 변수의 크기는 4byte이다.
	private JLabel backgroundMap; 
	private Player player;
//	private ArrayList<Enemy> enemyies = new ArrayList<Enemy>();
	private Enemy enemy;
	
	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
		new BGM();

	}

	private void initObject() {
		// 예외처리를 하지 않아도 되지만 디버깅하기 어려움
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		this.setContentPane(backgroundMap);

		player = new Player(this);
		add(player);
		enemy = new Enemy(this);
		add(enemy);
	}

	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); // absolute (좌표값으로 자유롭게 그림을 그릴 수 있다)

		setLocationRelativeTo(null); // JFrame 가운데 배치하기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					// true, false
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isDown()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
//					Bubble bubble = new Bubble(player);
//					Bubble bubble = player.attackBubble();
				
					player.attackBubble();
					
					
					break;
				}

			} // end of keyPressed

			// 키보드 해제 이벤트 처리
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				}
			}

		});

	}

	public static void main(String[] args) {
		new BubbleFrame(); // 메인함수에서 객체 생성 -> BubbleF생성자 호출
	}

}