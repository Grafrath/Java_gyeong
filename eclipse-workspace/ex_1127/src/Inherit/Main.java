package Inherit;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dog dog = new Dog("흰둥이");
		
		dog.eat();
		dog.sleep();
		dog.bark();
		
		System.out.println();
		
		Bird fly = new Bird("플라이");
		
		fly.eat();
		fly.sleep();
		fly.fly();
		
		Child ch = new Child(10);
				
	}

}
