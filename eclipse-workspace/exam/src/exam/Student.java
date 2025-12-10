package exam;

public class Student extends Person {
	String name;
	int age;
	String major;
	
	public Student(String name, int age, String major) {
		this.name = name;
		this.age = age;
		this.major = major;
	}
	
	public void printInfo () {
		System.out.println("이름: " + name + ", 나이: " + age + ", 전공: " + major);
	}
	
	public void introduce() {
		System.out.println("안녕하세요, 저는 " + name + "이고 " + age + "살, 전공은 " + major + "입니다");
	}
}
