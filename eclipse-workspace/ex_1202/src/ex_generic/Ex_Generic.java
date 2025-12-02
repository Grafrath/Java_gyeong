package ex_generic;

public class Ex_Generic {
	public static void main(String[] args) {
		
		GenEx<String> v1 = new GenEx<String>(); 
		
		//T자리에 실제 타입을 지정한다.
		//제네릭의 타입 전파가 행해진다고 보면 된다.
		//<T>부분에서 실행부에서 타입을 받아와 내부에서 T타입으로 지정한 멤버들에게 전파하여
		//타입이 구체적으로 설정되는 것이다. 이를 구체화(Specialization)라고 한다.

		v1.setValue("100");
		System.out.println(v1.getValue());

		//타입 매개변수의 생략
		//jdk 1.7버전 이후부터, new 생성자 부분의 제네릭 타입을 생략할 수 있게 되었다.
		GenEx<Integer> v2 = new GenEx<>();
		v2.setValue(1000);
		System.out.println(v2.getValue()+10);

		GenEx<Character> v3 = new GenEx<Character>();
		v3.setValue('A');
		System.out.println(v3.getValue());

		GenEx<Double> v4 = new GenEx<Double>();
		v4.setValue(3.14);
		System.out.println(v4.getValue());
		
	}
}
