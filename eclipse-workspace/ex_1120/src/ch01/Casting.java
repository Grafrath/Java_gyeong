package ch01;

public class Casting {

	public static void main(String[] args) {
		// 
		
		byte bytevalue = 10;
		int intvalue = bytevalue;
		
		System.out.println("intvalue ="+intvalue);
		
		//문자형을 정수로
		char charvalue = '가';
		intvalue = charvalue; // 문자는 유니코드로
		System.out.println("charvalue =" + intvalue);
		
		char charvalue2 = 'a';
		intvalue = charvalue2; // 알파벳은 아스키코드로
		System.out.println("charvalue =" + intvalue);

		//강제형변환
		//큰자료형 -> 작은자로형으로
		int i1 = 10;
		byte b1 = (byte)i1;
		
		//정수 -> 문자
		int i2 = 65;
		char c1 = (char)i2;
		System.out.println(c1);
		
		Integer  n = 10;
		System.out.println(n.getClass().getSimpleName());
		
		// 문자열을 기본 타입으로 변환
		
		// Byte = Byte.parseByte();
		// int = integer.parseint();
		// short = short.parseshort();
	}

}
