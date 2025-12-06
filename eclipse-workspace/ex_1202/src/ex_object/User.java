package ex_object;

public record User(String name, int age) {
	
	//레코드로 만들면 컴파일러가 자동으로 코드를 만들어준다.
	//매개변수에 있는것들을 private final 필드로 만들어준다.
	//매개변수를 받는 생성자와 getter를 만들어준다.
	//equals, hashcode, toString메서드를 오버라이딩 한다.
	//레코드는 불변객체라서 setter가 필요없고 필드도 final이라 변경이 불가하다.
	public void print() {
		System.out.println("이름: " + name + " 나이: " + age);
	}	
	
}
