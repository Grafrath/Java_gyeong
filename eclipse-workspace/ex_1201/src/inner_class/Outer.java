package inner_class;

public class Outer {
	
	private int value = 10;
	
	//내부 클래스
	public class Inner {
		
		void print() {
			System.out.println("value= " + value);
		}
		
	}

}
