package ex_1127;

public class SingletonMain {

	public static void main(String[] args) {
		// 
		//Singleton sin = new Singleton();
		
		Singleton single01 = Singleton.getInstance();
		Singleton single02 = Singleton.getInstance();
		
		System.out.println(single01);
		System.out.println(single02);
		
		/*
		 * 여러곳에서 같이 써야하고, 여러개가 존재하면 오히려 문제일 경우 싱글톤 패턴으로 정의한다.
		 * 
		 */

	}

}
