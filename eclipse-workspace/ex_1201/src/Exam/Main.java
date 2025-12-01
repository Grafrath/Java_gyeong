package Exam;

public class Main {

	public static void main(String[] args) {
		
		MusicPlayer mp = new MusicPlayer();
		
		mp.play();
		
		System.out.println();
		
		DocumentProcessor document = new DocumentProcessor();
		
		document.print();
		document.sort();
		
		System.out.println();
		
		EmailSender email = new EmailSender();
		email.send("안녕하세요");
		
		SmsSender sms = new SmsSender();
		sms.send("반갑습니다.");
				
		NotificationService.notifyUser(sms, "뭐라고 하지");
		
		System.out.println();
		
		PayService pay = new PayService();
		pay.processPayment(new CardPayment(), 10000);
		pay.processPayment(new KakaoPayPayment(), 20000);
		
		System.out.println();
		
	}

}

/*

인터페이스 : Payment
- void pay(int amount);

클래스 : CardPayment, KakaoPayPayment (Payment 구현하기)
Card : "카드로 xx원 결제"
Kakao : "카카오페이로 xx원 결제"

결제기능을 가지고 있는 PayService 클래스 
public void processPayment(Payment payment, int amount)

Main에서 다음의 결과가 나오도록 작성하기
CardPayment로 10000원 결제
KakaoPayPayment로 20000원 결제

*/