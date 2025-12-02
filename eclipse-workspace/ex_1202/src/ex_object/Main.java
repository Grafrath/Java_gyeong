package ex_object;

public class Main {

	public static void main(String[] args) {
		
		Student s1 = new Student(1,"홍길동");
		Student s2 = new Student(1,"홍길동");
		
		if (s1.hashCode() == s2.hashCode()) {
			if (s1.equals(s2)) {
				System.out.println("동등객체이다.");
			} else {
				System.out.println("데이터가 다르므로 동등객체가 아니다.");
			}
		} else {
			System.out.println("해시코드가 다르므로 동등객체가 아니다.");
		}
		
		System.out.println();
		
		SmartPhone phone = new SmartPhone("삼성전자", "안드로이드");
		
		System.out.println(phone);
		System.out.println(s1);
		
		System.out.println();
		
		User u1 = new User("김철수", 29);
		User u2 = new User("김철수", 29);
		
		System.out.println(u1.name());
		System.out.println(u1.age());
		u1.print();
		System.out.println(u1.hashCode());
		System.out.println(u1.equals(u2));
		System.out.println(u1.toString());
		
		System.out.println();
		
	}

}
