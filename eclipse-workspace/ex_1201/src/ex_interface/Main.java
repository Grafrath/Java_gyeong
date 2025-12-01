package ex_interface;

public class Main {

	public static void main(String[] args) {
		
		Dog d = new Dog();
		
		d.eat();
		d.sleep();
		
		//타입 변환은 가능하고 오버라이딩된 메소드는 사용 가능하다.
		Animal a = d;
		
		a.eat();
		a.sleep();
		
		System.out.println();
		
		//상수의 사용
		System.out.println("MyInterface의 상수 max_count: " + MyInterface.MAX_COUNT);
		//static 메서드 사용
		MyInterface.InterfaceInfo();
		
		MyInterface My = new MyService();
		//추상메서드 구현체의 사용
		My.doWolk();
		//디폴트 메서드의 사용
		My.log("사랑해요");
		
		OrderService se = new OrderService(new OracleOrderRepository());
		
		se.createOrder();
		
	}

}
