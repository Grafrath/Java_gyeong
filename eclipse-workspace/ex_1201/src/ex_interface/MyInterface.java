package ex_interface;

public interface MyInterface {
	
	//인터페이스에 포함될 수 있는것, 상수. 상수명은 대문자로
	//인터페이스는 어짜피 상수밖에 못오기 때문에 public static final 생략이 가능하다
	int MAX_COUNT = 10;
	
	void doWolk();
	
	default void log(String msg) {
		System.out.println("Log: " + msg);
	}
	
	static void InterfaceInfo() {
		System.out.println("인터페이스 정보를 출력한다.");
	}
	
}
