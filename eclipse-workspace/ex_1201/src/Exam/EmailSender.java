package Exam;

public class EmailSender implements MessageSender {

	@Override
	public void send(String msg) {
		System.out.println("이메일 발송: " + msg);		
	}

}
