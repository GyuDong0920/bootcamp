package thread_ex;

class CorporateCard {

	private int money = 1_000_000_000;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public synchronized void DepositMoney(int money) {
		int cardmoney = getMoney();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("거래처에서 입금 중");
		}
		setMoney(cardmoney + money);
		System.out.println("입금 후 계좌 잔액 : " + getMoney());
	}

	public synchronized void WithdrawalMoney(int money) {
		int cardmoney = getMoney();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("거래처 접대비");
		}
		setMoney(cardmoney - money);
		System.out.println("출금 후 계좌 잔액 : " + getMoney());
	}
}

class Samsungclient extends Thread {

	CorporateCard card;

	public Samsungclient(CorporateCard card) {
		this.card = card;
	}

	@Override
	public void run() {
		card.DepositMoney(100_000_000);

	}
}

class HyundaiGroup extends Thread {

	CorporateCard card;

	public HyundaiGroup(CorporateCard card) {
		this.card = card;
	}

	@Override
	public void run() {
		card.WithdrawalMoney(20_000_000);

	}
}

public class SharedResouce2 {

	public static void main(String[] args) {

		CorporateCard card = new CorporateCard();
		Samsungclient client = new Samsungclient(card);
		HyundaiGroup group = new HyundaiGroup(card);

		client.start();
		group.start();

	}

}
