package Inherit;

public class Dog extends Animal {
	
	Dog (String n) {
		this.name = n;
	}
	
	void bark () {
		System.out.println(name + "가(이) 멍멍 짖는다.");
	}

}
