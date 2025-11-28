package ex_1127;

public class CounterMain {

	public static void main(String[] args) {
		// 
		Counter c1 = Counter.getInstance();
		Counter c2 = Counter.getInstance();
		
		c1.increment();
		c2.increment();
		
		c1.getCount();
		c2.getCount();
		
		c1.increment();
		c2.increment();
		
		c1.getCount();
		c2.getCount();

	}

}
