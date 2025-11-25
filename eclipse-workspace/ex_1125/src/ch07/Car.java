package ch07;

public class Car {
	
	String model;
	boolean start;
	String color = "white";
	int speed;
	int maxSpeed = 300;
	
	public void startCar(boolean s) {
        start = s;        
    }
	
	public Car(String m, int s) {
		model = m;
		speed = s;
	}
	
}
