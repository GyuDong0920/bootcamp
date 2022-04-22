package file_io_ch04;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

//  콜러
public class Pen extends Diary{
	
	String fileName = "note.txt";
	
	
      
	InterFaceDiary interFaceDiary;
      
	public void setInterFaceDiary(InterFaceDiary interFaceDiary) {
		this.interFaceDiary = interFaceDiary;
	}
	
	public Pen(String name) {
		
		super(name);
		addEventListener();
		panel.add(textArea);
		textArea.setSize(100, 100);
	}

	
	private void addEventListener() {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
					bw.write(textArea.getText());
					bw.flush();
					
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
				
			}
		});
	}

	
	
	
//	private void addEventListener() {
//		this.addKeyListener(new KeyAdapter() {
//		
//			@Override
//			public void keyPressed(KeyEvent e) {
//				System.out.println("asdf");
//				switch(e.getKeyCode()) {
//				case KeyEvent.VK_ENTER:
//					System.out.println("엔터");
//				try {
//					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
//					bw.write(textArea.getText());
//					bw.flush();
//					
//					bw.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				} break;
//				}
//			}
//		
//		});
//	}

	
}
