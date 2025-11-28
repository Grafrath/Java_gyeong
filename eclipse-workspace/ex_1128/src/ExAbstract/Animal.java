package ExAbstract;

/*

추상 클래스와 추상메서드
추상클래스
공통의 뼈대만 가진 미완성 설계도
abstract 키워드로 선언되고, 하나 이상의 추상 메서드를 포함하고 있는 클래스이다.
직접 객체를 만들수 없고, 반드시 상속을 통해 완성해서 사용하는 클래스

 */
public abstract class Animal {
	
	public void sound() {
		System.out.println("동물이 소리를 낸다.");
	}
	
}

class Dog extends Animal {
	
	public void sound() {
		System.out.println("멍멍");
	}

}

class Cat extends Animal {
	
	public void sound() {
		System.out.println("냐옹");
	}

}