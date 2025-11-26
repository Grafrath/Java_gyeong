package ex_1126;

public class StudentMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student stu = new Student("홍길동",90,95,85);
		
		System.out.println(stu.name);
		System.out.println(stu.getTotal());
		System.out.println(stu.getAverage());
		System.out.println(stu.getGrade());

	}

}