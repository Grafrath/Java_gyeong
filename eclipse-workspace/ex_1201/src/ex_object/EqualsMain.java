package ex_object;

public class EqualsMain {

	public static void main(String[] args) {
		
		Member obj1 = new Member("blue");
		Member obj2 = new Member("blue");
		Member obj3 = new Member("red");
		
		if (obj1.equals(obj2)) {
			System.out.println("obj1, obj2는 동등하다");
		} else {
			System.out.println("obj1, obj2는 동등하지 않다.");
		}
		
		if (obj1.equals(obj3)) {
			System.out.println("obj1, obj3는 동등하다");
		} else {
			System.out.println("obj1, obj3는 동등하지 않다.");
		}
		
	}

}
