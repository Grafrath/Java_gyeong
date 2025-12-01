package interface_casting;

public class Main {

	public static void main(String[] args) {
		
		Vehicle vehicle = new Bus();
		vehicle.run(); //부모타입으로 바뀌었어도 오버라이딩된 메소드 호출
		//자식클래스에서만 정의된건 사용 불가능 vehicle.checkFare();
		
		System.out.println();
		
		Bus bus = (Bus) vehicle;
		bus.run();
		bus.checkFare();
		
	}

}