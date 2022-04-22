package lotto_game;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LottoFrame extends JFrame implements ActionListener {

	private JButton startButton;
	private int[] lottoNumbers = new int[6];
	private int x = 40;
	private int y = 200;

	public LottoFrame() {
		initData();
		setInitLayout();
		addEventListener();

	}

	private void initData() {
		setSize(600, 400);
		startButton = new JButton("번호 생성");

		add(startButton, BorderLayout.SOUTH);

	}

	private void setInitLayout() {
		setBackground(Color.yellow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void addEventListener() {
		startButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lottoNumbers = getLotto();
		// 그림을 다시 그려라
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		Font font = new Font("CookieRun", Font.BOLD, 20);
		g.setFont(font);
		g.drawOval(x, y, y, x);

		if (lottoNumbers[0] == 0) {
			g.drawString("로또 버튼을 클릭 하세요", 200, 350);
			return;
		}

		// lottoNumber[0] 값이 있다면 (6개 번호를 생성 한 후)

		// 여기서 번호를 그려 봅니다.
		g.drawString(lottoNumbers[0] + "", x * 2, y);
		g.drawString(lottoNumbers[1] + "", x * 4, y);
		g.drawString(lottoNumbers[2] + "", x * 6, y);
		g.drawString(lottoNumbers[3] + "", x * 8, y);
		g.drawString(lottoNumbers[4] + "", x * 10, y);
		g.drawString(lottoNumbers[5] + "", x * 12, y);
	}

	public static void main(String[] args) {
		new LottoFrame();
	}

	public int[] getLotto() {

		int[] lotto = new int[6];
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int j = random.nextInt(45) + 1;
			lotto[i] = j;
			for (int e = 0; e < i; e++) {
				if (lotto[i] == lotto[e]) {
					i = i - 1;
					break;
				}
			}
		}
		Arrays.sort(lotto);
		for (int i : lotto) {
			System.out.println(i);
		}

		return lotto;
	}

}
