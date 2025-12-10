package eaxm;

public class SavingsAccount extends BankAccount {
	String owner;
	int balance;
	
	public SavingsAccount(String owner, int balance) {
		 this.owner = owner;
		 this.balance = balance;
	 }

	public void withdraw(int amount) {
		 if ( this.balance/2 > amount) {
			 System.out.println("잔액 감소");
		 } else {
			 System.out.println("적금 계좌는 한번에 잔액의 50%만 출금 가능합니다");
		 }
	 }
}
