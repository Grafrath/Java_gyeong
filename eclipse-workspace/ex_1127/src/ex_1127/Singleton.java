package ex_1127;

public class Singleton {
	
	//private 접근 권한을 갖는 정적 필드 선언과 초기화
	//Singleton객체 생성(static으로 한번만 메모리에 생성됨
	private static Singleton singleton = new Singleton();
	
	//private 접근 권한을 갖는 생성자
	private Singleton () {
		System.out.println("싱글톤 객체 생성!");		
	}
	
	public static Singleton getInstance() {
		return singleton;
	}

}
