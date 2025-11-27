package Inherit;

public class Bird extends Animal {
	
	Bird (String n) {
		this.name = n;
	}
	
	void fly () {
		System.out.println(name + "가(이) 난다.");
	}

}
