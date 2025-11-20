package ch01;
import java.util.Scanner;


public class Scan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//데이터입력
		//키보드를 통해 다양한 데이터를 입력할 수있다.
		//java.util. 안의 Scanner클래스를 이용해야 한다.
		
		//Scanner클래스의 객체를 만들어야한다
		
		//객체 만드는 법
		//클래스명 변수명 = new 클래스명();
		
		// Scanner 클래스 메서드 종류
		
		// 1. 기본 입력 메서드 (문자열/숫자)
		// next();             // 공백 전까지 문자열 입력
		// nextLine();         // 한 줄 전체 입력
		// nextInt();          // int 입력
		// nextLong();         // long 입력
		// nextFloat();        // float 입력
		// nextDouble();       // double 입력
		// nextBoolean();      // boolean 입력
		// nextByte();         // byte 입력
		// nextShort();        // short 입력
		// nextBigInteger();   // BigInteger 입력
		// nextBigDecimal();   // BigDecimal 입력
		
		// 2. 입력 가능 여부 확인 (hasNext 계열)		
		// hasNext();          // 다음 입력 존재 여부
		// hasNextInt();       // int 읽을 수 있는지 확인
		// hasNextDouble();    // double 읽을 수 있는지 확인
		// hasNextBoolean();   // boolean 읽을 수 있는지 확인
		// hasNextLong();      // long 읽을 수 있는지 확인
		// hasNextLine();      // 다음 줄 존재 여부
		
		// 3. 구분자(Delimiter) 관련 메서드
		// useDelimiter("pattern");   // 구분자 변경
		// delimiter();               // 현재 구분자 반환
		
		// 4. 패턴/토큰 관련 메서드
		// skip("pattern");    // 특정 패턴 건너뛰기
		// tokens();           // Stream<String> 형태로 토큰 반환
		
		// 5. 기타 메서드
		// close();            // Scanner 닫기
		// reset();            // 초기 상태로 리셋
		// ioException();      // 입력 중 발생한 IOException 반환
		
		Scanner sc = new Scanner(System.in);
		String name,address;
		int age;
		double height;
		
		System.out.print("이름 : ");
		name = sc.next();
		System.out.print("나이 : ");
		age = sc.nextInt();
		System.out.print("주소 : ");
		address = sc.next();
		System.out.print("신장 : ");
		height = sc.nextDouble();
		
		System.out.println(name);

	}

}
