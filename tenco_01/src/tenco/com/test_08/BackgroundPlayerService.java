package tenco.com.test_08;

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
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		
		while(true) {
		Color leftcolor = new Color(image.getRGB(player.getX() - 10 ,player.getY()));
		Color rightcolor = new Color(image.getRGB(player.getX() + 50 + 15,player.getY()));
		System.out.println("==============================");
		System.out.println("왼쪽 색상 : " + leftcolor);
		System.out.println("오른쪽 색상 : " + rightcolor);
		System.out.println("==============================");
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	}
}
