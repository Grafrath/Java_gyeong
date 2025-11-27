package Inherit;

public class Child extends Parent {
	
	int c = 20;
	
	public Child (int p) {
		super(p);
	}
	
	public void ChildMethod() {
		super.ParentMethod();
		System.out.println("자식 메서드");
	}

}
