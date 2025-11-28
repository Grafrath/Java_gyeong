package Polymorphism;

public class Main {

	public static void main(String[] args) {
		
		Car myCar = new Car();
		
		myCar.tire = new Tire();
		myCar.run();
		
		System.out.println();
		
		myCar.tire = new HankookTire();
		myCar.run();
		
		System.out.println();
		
		myCar.tire = new KumhoTire();
		myCar.run();
		
		System.out.println();
		
		Driver driver = new Driver();
		
		driver.drive(new Vehicle());
		
		System.out.println();
		
		driver.drive(new Taxi());
		
		System.out.println();
		
		driver.drive(new Bus());
		
	}

}