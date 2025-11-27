package ex_1127;

public class Counter {
	private static Counter counter;
	private int count;
	
	private Counter () {
		this.count=0;		
	}
	
	static Counter getInstance() {
		if (counter == null) {
			counter = new Counter();  // 최초 1회만 생성
		}
		return counter;
	}
	
	void increment() {
		count++;		
	}
	
	void getCount() {
		System.out.println(count);
	}
	
}