package class_casting;

public class Main {

	public static void main(String[] args) {
		
		Dog dog = new Dog();
		Cat cat = new Cat();
		
		dog.sound();
		cat.sound();
		
		//자동 형변환
		
		long l = 100; //int가 long보다 작아서 자동 형변환된다.
		double d = 100;//double다 작아서 자동형변환
		
		Animal a1 = new Dog(); //dog타입 객체를 Animal타입의 변수에 대입가능
		Animal a2 = new Cat();
		
		new String("문자열");
		
		a1.sound();
		a2.sound();
		
		/*
		
		부모타입으로 타입변환 하는 이유
		
		1. 자식 객체를 부모타입의 변수에 넣어서 다루면, 코드를 더 통일성 있게 관리할수 있다.
		2. 상속만 하면 자식 클래스마다 변수를 따로 만들어 줘야한다.
		부모타입으로 관리하면 하나의 부모타입 배열이나 리스트에 담아 보관 가능
		
		*/
		
		Child ch = new Child();
		
		Parent pa = ch;
		
		pa.field01 = "data1";
		pa.method01();
		pa.method02();
		//pa.method03(); 호출 불가
		//pa.field02 = "data1"; x
		
		// 부모타입으로 변환하면 자식클래스에서 정의된 내용은 쓸수 없다.
		//메서드는 실행시점에 성격이 결정되는 동적바인딩
		//컴파일 시점에는 부모, 자식클래스중 어느것인지 알기 어려움
		//실행시점에 동적바인딩이 일어나 부모가 자식의 멤버함수에 접근해 실행할 수 있다.
		
		Child ch2 = (Child)pa;
		
		ch2.field02 = "data2";
		ch2.method03();
		
	}

}
