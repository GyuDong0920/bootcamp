package thread_ex;

import javax.print.attribute.SetOfIntegerSyntax;

class BankAccount {

	private int money = 100_000;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	// 입금 기능
	public synchronized void saveMoney(int money) {
		// 10만원
		int currnetMoney = getMoney();
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMoney(currnetMoney + money);
		System.out.println("입금 후 계좌 잔액 : " + getMoney());

	}

	// 출금 기능
	public void withdraw(int money) {

		synchronized (this) {
			int currnetMoney = getMoney();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			setMoney(currnetMoney - money);
			System.out.println("출금 후 계좌 잔액 : " + getMoney());

		}

	}

}

// 아버지는 세종시에서 입금을 합니다. (네트워크가 느려서 시간이 조금 걸림)
class Father extends Thread {

	BankAccount account;

	public Father(BankAccount account) {
		this.account = account;
	}

	@Override
	public void run() {
		account.saveMoney(10_000);
	}

}

class mother extends Thread {

	BankAccount account;

	public mother(BankAccount account) {
		this.account = account;
	}

	@Override
	public void run() {
		account.withdraw(5_000);
	}

}

public class SharedResource {

	public static void main(String[] args) {
		// 현재 10만원이 저장 되어있음
		BankAccount account = new BankAccount();
		Father father = new Father(account);
		mother mother = new mother(account);

		// 아버지가 입금 합니다.
		father.start();
		// 어머니가 출금 합니다.
		mother.start();

		// 10만5000원
		// 정상 처리 금액 : 10 + 1 - 0.5 = 10.5

		// 공유되는 자원을 활용 할 때 의도치않은 결과를 생성할 수 있다.
		// 해결방법 --> 동기화 synchronization

	}

}
