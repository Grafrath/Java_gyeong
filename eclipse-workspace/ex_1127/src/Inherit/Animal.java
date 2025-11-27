package Inherit;

public class Animal {
	String name;
	
	public void info() {
		System.out.println("이름: " + name);
	}
	
	public void eat () {
		System.out.println(name + "가(이) 밥을 먹는다.");
	}
	
	public void sleep () {
		System.out.println(name + "가(이) 잠을 잔다.");
	}

}