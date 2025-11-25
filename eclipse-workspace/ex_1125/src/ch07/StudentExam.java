package ch07;

public class StudentExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student();
		Car c1 = new Car("쏘나타",0);
		Car c2 = new Car("그랜저",0);
		
		c2.speed = 100;
		c2.color = "Black";
		c2.startCar(true);
		
		System.out.println(c1.model + c1.start + c1.speed + c1.color);
		System.out.println(c2.model + c2.start + c2.speed + c2.color);
		

	}

}
