package Exam;

public class NotificationService {
	
	private MessageSender sender;
	
	NotificationService(MessageSender sender) {
		this.sender = sender;
	}
	
	static void notifyUser(MessageSender sender, String message) {
		sender.send(message);
	}

}
