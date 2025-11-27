package Inherit;

public class Dog extends Animal {
	int age;
	
	Dog (String n, int age) {
		this.name = n;
		this.age = age;
	}
	
	void bark () {
		System.out.println(name + "가(이) 멍멍 짖는다.");
	}
	
	public void info() {
		System.out.println("이름: " + name);
		System.out.println("나이: " + age);
	}

}
