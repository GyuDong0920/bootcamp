package chatting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Socket_ex_ch04.jFrame;


public class serverView extends jFrame{

		// GUI
		protected JTextField textField;
		protected JTextArea textArea;
		protected JButton startbutton;
		protected JButton stopButton;
		protected JButton savelogButton;
		protected JButton deletelogButton;
		private BufferedImage img = null;
	
		
		public serverView() {
			try {
				img = ImageIO.read(new File("images/kakao.png"));
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(null, "이미지 불러오기 실패");
				System.exit(0);
			}
			init();
			startbutton.requestFocus();
			stopButton.requestFocus();
			savelogButton.requestFocus();
			deletelogButton.requestFocus();
			textField.requestFocus();
			
		
		}

		private void init() {
			setTitle("KakaoNetService");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(380, 590);
			setResizable(false);

			myFrame panel = new myFrame();
			setContentPane(panel);
			panel.setLayout(null);
			panel.setBounds(0, 0, 370, 590);
			

			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setBounds(10, 170, 200, 350);
			textArea = new JTextArea();
			panel.add(scrollPane);
			textArea.setEditable(false);

			textField = new JTextField();
			textField.setBounds(10, 130, 200, 25);
			panel.add(textField);
			textField.setColumns(10);
			textField.setText("Port Number");

			startbutton = new JButton("Sever ON");
			startbutton.setBounds(235, 130, 100, 25);
			startbutton.setBackground(new Color(59, 30, 30));
			startbutton.setForeground(Color.white);
			panel.add(startbutton);

			stopButton = new JButton("Sever OFF");
			stopButton.setBounds(235, 170, 100, 25);
			stopButton.setBackground(new Color(59, 30, 30));
			stopButton.setForeground(Color.white);
			panel.add(stopButton);

			savelogButton = new JButton("Save Log");
			savelogButton.setBounds(235, 210, 100, 25);
			savelogButton.setBackground(new Color(59, 30, 30));
			savelogButton.setForeground(Color.white);
			panel.add(savelogButton);

			deletelogButton = new JButton("Delete Log");
			deletelogButton.setBounds(235, 250, 100, 25);
			deletelogButton.setBackground(new Color(59, 30, 30));
			deletelogButton.setForeground(Color.white);
			panel.add(deletelogButton);

			setVisible(true);
			
		}
		class myFrame extends JPanel {
			public void paint(Graphics g) {
					g.drawImage(img, 0, 0, null);
				}
			}

		
}
