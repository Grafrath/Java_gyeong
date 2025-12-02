package ex_StringBuilder;

public class WrapperExample {

	public static void main(String[] args) {
		
		Integer obj = 100;
		int value = obj;
		
		System.out.println(obj);
		System.out.println(value);
		
		Integer obj1 = 300;
		Integer obj2 = 300;
		
		System.out.println("==: " + (obj1 == obj2));	//변수의 주소를 비교하는것이라 false이 나옴?
		System.out.println("equals(): " + obj1.equals(obj2));	//내부값을 비교하면 true가 나옴
		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode());
		
		System.out.println();
		
		Integer obj3 = 10;
		Integer obj4 = 10;
		System.out.println("==: " + (obj3 == obj4));
		System.out.println("equals: " + obj3.equals(obj4));
		System.out.println(obj3.hashCode());
		System.out.println(obj4.hashCode());
		
	}

}
