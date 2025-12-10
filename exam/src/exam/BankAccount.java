package exam;

public class BankAccount {
	 String owner;
	 int balance;
	 
	 public BankAccount () {
		 
	 }
	 
	 public BankAccount(String owner, int balance) {
		 this.owner = owner;
		 this.balance = balance;
	 }
	 
	 public void deposit(int amount) {
		 this.balance += amount;
	 }
	 
	 public void withdraw(int amount) {
		 if ( this.balance > amount) {
			 System.out.println("잔액 감소");
		 } else {
			 System.out.println("잔액 부족");
		 }
	 }
}
