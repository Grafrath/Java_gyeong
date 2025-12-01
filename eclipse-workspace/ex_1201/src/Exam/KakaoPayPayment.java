package Exam;

public class KakaoPayPayment implements Payment {
	
	public void pay(int amount) {
		System.out.println("페이 결제 금액: " + amount);
	}

}
