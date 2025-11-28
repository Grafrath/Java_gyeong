package ex_1128;

public class Car {
	
	int speed;
	
	void speedup() {
		speed++;
	}
	
	final void stop() {
		System.out.println("차를 멈춤");
		speed = 0;		 
	}

}
