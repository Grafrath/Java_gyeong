package package2;
import ex_1128.A;

public class D extends A{
	
	D() {
		super(); //a의 생성자 호출가능
	}
	
	public void method1() {
		field = "value";
		method();
	}
	
	public void method2() {
		//A a = new A(); super를 통한 호출만 가능
	}
	
}
