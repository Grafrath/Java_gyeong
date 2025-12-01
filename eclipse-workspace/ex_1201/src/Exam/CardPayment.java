package Exam;

public class CardPayment implements Payment {
	
	public void pay(int amount) {
		System.out.println("카드 결제 금액: " + amount);
	}

}
