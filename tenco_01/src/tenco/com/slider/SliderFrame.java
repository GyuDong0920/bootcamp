package tenco.com.slider;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SliderFrame extends JFrame{

	private JLabel bgMap;
	int pointX = 0;
	int pointY = 0;
	
	
	
	
	public SliderFrame() {
		initObject();
		initSetting();
		initListener();
	}


	private void initObject() {
		bgMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		
		setSize( 400, 400);
		setVisible(true);
		setResizable(true);
		setLocationRelativeTo(this);
		
		
	}

	private void initSetting() {
		setContentPane(bgMap);
		
	}


	private void initListener() {
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				int KeyCode = e.getKeyCode();
				switch (KeyCode) {
				case KeyEvent.VK_RIGHT:
					
					new Thread(new Runnable() {
						public void run() {
							
							for ( int i = 0; i < 100; i ++) {
								bgMap.setLocation(pointX, pointY);
								pointX++;
								Thread.sleep(3);
							}
							
							
						}
					}).start();
				}
				break;
				default:
					break;
			}
		});
		
	}




	


	
}
