package tenco.com.test_10;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// 메인 쓰레드는 바쁨 -> 키보드 이벤트 처리를 해야하기 때문
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("image/BackgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		while (true) {
			Color leftcolor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
			Color rightcolor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));
//		Color bottoColor = new Color(image.getRGB(player.getX() +25 , player.getY() + 50 + 10));
			int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5)
					+ image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5);
			// -1 이 아니라는 것은 빨간색이거나 파란색이다.
			// -2가 아니라면 ! ! !
			if (bottomColor != -2) {
//					System.out.println("바닥 컬러 : " + bottomColor);
				player.setDown(false);
			} else {
				// 점프 하는 순간 down메서드가 호출
				if (!player.isUp() && !player.isDown()) {
					player.down();
				}

			}

			if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
				System.out.println(" 왼 쪽 벽 에 충 돌 했 어 ! ! !");
				player.setLeftWallcrash(true);
				player.setLeft(false);
			} else if (rightcolor.getRed() == 255 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 0) {
				System.out.println(" 오 른 쪽 벽 에 충 돌 했 어 ! ! !");
				player.setRightWallcrash(true);
				player.setRight(false);
			} else {
				player.setLeftWallcrash(false);
				player.setRightWallcrash(false);
			}

//		System.out.println("==============================");
//		System.out.println("왼쪽 색상 : " + leftcolor);
//		System.out.println("오른쪽 색상 : " + rightcolor);
//		System.out.println("==============================");
			try {
				// 캐릭터가 이동 될 때 값을 못 찾는 경우가 있다.
				// 작업자를 너무 재워서 그런거임
				Thread.sleep(3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
