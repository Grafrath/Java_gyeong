package Inherit;

public class Main {

	public static void main(String[] args) {
		// 
		
		Dog dog = new Dog("흰둥이",7);
		
		dog.info();
		dog.eat();
		dog.sleep();
		dog.bark();
		
		System.out.println();
		
		Bird fly = new Bird("플라이");
		
		fly.info();
		fly.eat();
		fly.sleep();
		fly.fly();
		
		System.out.println();
		
		Child ch = new Child(10);
		ch.ChildMethod();
		
		System.out.println();
		
		Student stu = new Student();
		stu.introduce();
		
		System.out.println();
		
		CalPlus p = new CalPlus();
		System.out.println(p.getResult(15, 15));
		
		CalMinus m = new CalMinus();
		System.out.println(m.getResult(30, 15));
		
		System.out.println();
		
		HybridWaterCar wCar = new HybridWaterCar(15, 30, 25);
		wCar.showCurrentGague();
		
	}

}