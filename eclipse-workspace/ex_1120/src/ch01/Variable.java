package ch01;

public class Variable {

	public static void main(String[] args) {
		// 
		// 변수형 선언
		int age;
		
		// 값의 대입
		age = 30;
		
		// 헝가리안 표기법
		// 변수나 함수의 이름에 그 종류, 곧 흔히 데이터 타입 따위를 명시하는 표기법
		//string -> str

		//대입에는 제한이 없고 대입을 새로하면 이전에 저장된 값은 없어진다.
		
		int value = 0;
		
		int result = value + 10;
		System.out.println(result);
		
		int a = 5;
		int b = 3;
		int tmp;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		
		tmp=a;
		a=b;
		b=tmp;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		
		int wallet = 20000;
		int bank = 10000;
		
		System.out.println("wallet = " + wallet);
		System.out.println("bank = " + bank);
		
		wallet -=5000;
		bank +=5000;
		
		System.out.println("wallet = " + wallet);
		System.out.println("bank = " + bank);
		
		//byte b1 = (byte) 139;
		//byte -128~127까지 범위를 벗어나면 에러로 처리됨
		
		//정수형(long) 표현 범위: -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807 뒤에 L 또는 l을 붙여야 한다.
		
		long var1 = 10;
		//long var3 = 1000000000000; //컴파일러는 int로 간주하기 때문에 에러 발생
		long var2 = 1000000000000L;

		System.out.println(var1);
		System.out.println(var2);
		
		//java13 버전 이후로는 텍스트 블록 문법도 제공
		String str = 
				"""
				여기에
				문자열을
				저장합니다.
				""";
		
		System.out.println(str);
		
	}

}
