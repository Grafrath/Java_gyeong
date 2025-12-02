package ex_object;

public class SmartPhone {
	
	private String company;
	private String os;
	
	SmartPhone(String company, String os) {
		this.company = company;
		this.os = os;
	}
	
	public String toString() {
		return company + ", " + os;
	}
	
}
