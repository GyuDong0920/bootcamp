package thread_ex;

import javax.swing.JFrame;

// Runnable 인터페이스를 구현해서 만들기 - 2번째
// 자바는 다중 상속을 허용하지 않음으로 다른 클래스를 상속한 경우
// thread 를 만들기 위해 Runnable interface 를 구현하도록 합니다.

class MyRunnable1 extends JFrame implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("↗");
		}

	}

}

public class RunableTest1 {
	public static void main(String[] args) {

		// 사용하는 방법
		MyRunnable1 myRunnable1 = new MyRunnable1();
		// Runnable 을 구현한 객체는 Thread 를 생성해서 매개 변수에 담고
		// thread를 시작하면 된다.

		Thread thread1 = new Thread(myRunnable1);
		thread1.start();
		Thread thread2 = new Thread(myRunnable1);
		thread2.start();

	}
}
