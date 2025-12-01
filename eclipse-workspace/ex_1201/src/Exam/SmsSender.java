package Exam;

public class SmsSender implements MessageSender {

	@Override
	public void send(String msg) {
		System.out.println("SMS 발송: " + msg);		
	}

}
