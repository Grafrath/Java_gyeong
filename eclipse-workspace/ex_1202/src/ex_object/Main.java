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
		
	}

}
