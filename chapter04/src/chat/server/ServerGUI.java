package chat.server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerGUI extends JFrame implements ActionListener, ServerInterFace {

	// GUI
	protected JTextField textField;
	protected JTextArea textArea;
	protected JButton startbutton;
	protected JButton stopButton;
	protected JButton savelogButton;
	protected JButton deletelogButton;
	protected JButton loadLogButton;
	protected JButton exitButton;
	private BufferedImage img = null;
	ScrollPane scrollPane2 = new ScrollPane();
	Vector v = new Vector();
	JList vList = new JList(v);

	ServerNetWork netWork;

	public ServerGUI() {

		this.netWork = new ServerNetWork(this);
		try {
			img = ImageIO.read(new File("images/kakao.png"));
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "이미지 불러오기 실패");

		}
		init();
		addListener();
//		startbutton.requestFocus();
//		stopButton.requestFocus();
//		savelogButton.requestFocus();
//		deletelogButton.requestFocus();
//		exitButton.requestFocus();
//		loadLogButton.requestFocus();
//		textField.requestFocus();
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
		scrollPane.setBounds(10, 170, 235, 350);
		textArea = new JTextArea();
		textArea.setBounds(11, 171, 221, 351);
		scrollPane.add(textArea);
		panel.add(scrollPane);
		textArea.setEditable(false);

		scrollPane2.setBounds(255, 370, 100, 150);
		add(scrollPane2);
		scrollPane2.add(vList);

		textField = new JTextField();
		textField.setBounds(10, 130, 235, 25);
		panel.add(textField);
		textField.setColumns(6);
		textField.setText("Port Number");

		startbutton = new JButton("Sever ON");
		startbutton.setBounds(255, 130, 100, 25);
		startbutton.setBackground(new Color(59, 30, 30));
		startbutton.setForeground(Color.white);
		panel.add(startbutton);

		stopButton = new JButton("Sever OFF");
		stopButton.setBounds(255, 170, 100, 25);
		stopButton.setBackground(new Color(59, 30, 30));
		stopButton.setForeground(Color.white);
		panel.add(stopButton);
		stopButton.setEnabled(false);

		savelogButton = new JButton("Save Log");
		savelogButton.setBounds(255, 210, 100, 25);
		savelogButton.setBackground(new Color(59, 30, 30));
		savelogButton.setForeground(Color.white);
		panel.add(savelogButton);

		deletelogButton = new JButton("Delete Log");
		deletelogButton.setBounds(255, 250, 100, 25);
		deletelogButton.setBackground(new Color(59, 30, 30));
		deletelogButton.setForeground(Color.white);
		panel.add(deletelogButton);

		loadLogButton = new JButton("Load Log");
		loadLogButton.setBounds(255, 290, 100, 25);
		loadLogButton.setBackground(new Color(59, 30, 30));
		loadLogButton.setForeground(Color.white);
		panel.add(loadLogButton);

		exitButton = new JButton("Exit Server");
		exitButton.setBounds(255, 330, 100, 25);
		exitButton.setBackground(new Color(59, 30, 30));
		exitButton.setForeground(Color.white);
		panel.add(exitButton);

		String DATADIRECTORY = "C:\\bootcamp\\chapter04";
		File dir = new File(DATADIRECTORY);

		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File f) {

				return f.getName().endsWith(".txt");

			}
		};
		File files[] = dir.listFiles(filter);
		for (File file : files) {
			v.addElement(file);
			vList.setListData(v);

		}

		setVisible(true);

	}

	class myFrame extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	private void addListener() {
		textField.addActionListener(this);
		savelogButton.addActionListener(this);
		startbutton.addActionListener(this);
		stopButton.addActionListener(this);
		deletelogButton.addActionListener(this);
		loadLogButton.addActionListener(this);
		exitButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String txt = ".txt";
		String text = textArea.getText();
		String fileName = textField.getText() + txt;

		// 종료 버튼
		if (e.getSource() == exitButton) {
			System.exit(0);
		}

		// 서버 ON 을 눌렀을 때
		if (e.getSource() == startbutton) {
			try {

				if (textField.getText().length() == 0 || Integer.parseInt(textField.getText()) >= 65536) {
					textArea.append("0 ~ 65535 사이의 값을 입력해주세요.\n");

				} else if (textField.getText().length() != 0) {
					netWork.setPort(Integer.parseInt(textField.getText()));
					netWork.setNetwork();
					textField.setEditable(false);
					startbutton.setEnabled(false);
					stopButton.setEnabled(true);

				}

			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "0 ~ 65536 사이의 값을 입력해주세요.\n");

			}

		} else if (e.getSource() == stopButton) {
			try {
				netWork.getServerSocket().close();
				vc.removeAllElements();
				chRoom.removeAllElements();
				textField.setEditable(true);
				stopButton.setEnabled(false);
				startbutton.setEnabled(true);

			} catch (IOException e1) {
				startbutton.setEnabled(true);

			}
		} else if (e.getSource() == savelogButton) {

			textArea.append(fileName + " 내용이 저장되었습니다.\n");
			v.add(fileName);
			vList.setListData(v);

			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
				bw.write(text);
				bw.flush();
				bw.close();
			} catch (IOException e1) {

			}
			// 파일 삭제 기능 추가해야함!
		} else if (e.getSource() == deletelogButton) {
			if (v.size() == 0) {
				textArea.append("파일이 없습니다.\n");
			}
			try {
				int index = vList.getSelectedIndex();
				v.remove(index);
				vList.ensureIndexIsVisible(index);
			} catch (Exception e2) {
				textArea.append("파일을 선택해주세요 \n");
			}
			// 파일을 선택해서 불러와야함
		} else if (e.getSource() == loadLogButton) {

			Scanner scanner;
			try {
				scanner = new Scanner(new File("C:\\bootcamp\\chapter04\\214.txt"));
				while (scanner.hasNext()) {
					String str = scanner.next();
					textArea.append(str + "\n");
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		new ServerGUI();
	}

}
