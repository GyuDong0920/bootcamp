package thread_ex;

class PriorityThread extends Thread {

	@Override
	public void run() {

		int sum = 0;

		Thread t = Thread.currentThread();
		System.out.println(t + "start");

		for (int i = 0; i < 100000; i++) {
			sum += i;
		}

		System.out.println(t.getPriority() + "end");

	}
}

public class ThreadTest4 {

	public static void main(String[] args) {
		
		int i; 
		
		for ( i = Thread.MIN_PRIORITY; i <= Thread.MAX_PRIORITY ; i++) {
		
			PriorityThread pt = new PriorityThread();
			pt.setPriority(i);
			pt.start();
			
		}
		
	}
}
