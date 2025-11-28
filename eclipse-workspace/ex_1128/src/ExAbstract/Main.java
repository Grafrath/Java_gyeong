package ExAbstract;

public class Main {		

	public static void main(String[] args) {
		
		Animal dog = new Dog();
		Animal cat = new Cat();
		
		dog.sound();
		cat.sound();
		
		Calculator goodCal = new GoodCalc();
		
		System.out.println(goodCal.add(5, 10));
		System.out.println(goodCal.sub(10, 5));
		System.out.println(goodCal.average(5, 4));
		
		System.out.println();
		
		Person p1 = new Person("홍길동");
		Student s = new Student("김길동", 10);
		
		if(s instanceof Student) {
			System.out.println("s는 Student타입입니다");
		} else {
			System.out.println("s는 Student타입이 아닙니다.");
		}
		
	}

}