package exam;

public class Main {

	public static void main(String[] args) {
		
		Student stu01 = new Student("홍길동", 20, "컴퓨터공학");
		stu01.printInfo();
		
		System.out.println("-----------");
		
		Calculator cal = new Calculator();
		System.out.println(cal.add(10, 5));
		System.out.println(cal.subtract(10, 5));
		System.out.println(cal.multiply(10, 5));
		System.out.println(cal.divide(10, 5));
		System.out.println(cal.divide(10, 0));
		
		System.out.println("-----------");
		
		Point po1 = new Point();
		Point po2 = new Point(3, 5);
		po1.printPoint();
		po2.printPoint();
		
		System.out.println("-----------");
		
		Printer print = new Printer();
		print.print("테스트 문구");
		print.print(10);
		print.print(3.14);
		
		System.out.println("-----------");
		
		stu01.introduce();
		
		System.out.println("-----------");
		
		Animal[] animals = new Animal[3];
		animals[0] = new Animal();
		animals[1] = new Dog();
		animals[2] = new Cat();
		
		for (Animal n : animals) {
			n.sound();
		}
		
		System.out.println("-----------");
		
		Shape [] shape  = new Shape[2];
		shape[0] = new Rectangle(4,5);
		shape[1] = new Circle(3);
		
		for (Shape s : shape) {
			System.out.println(s.area());
		}
		
		System.out.println("-----------");
		
		Movable mov = new Car(5,10);
		mov.move(10, 20);
		
		System.out.println("-----------");
		
		Printable prin[] = new Printable[2];
		prin[0] = new ConsolePrinter();
		prin[1] = new FilePrinter();
		
		for (Printable p : prin) {
			p.print("hello");
		}
		
		System.out.println("-----------");
		
		//SavingsAccount
		BankAccount draw = new SavingsAccount("홍길동", 100000);
		draw.withdraw(30000);
		draw.withdraw(80000);
		
	}

}