package ex_try_catch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ex_try_catch03 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("정수를 입력하세요 : ");
			int score = sc.nextInt();
			
			if(score >= 60) {
				System.out.println("합격입니다.");
			} else {
				System.out.println("불합격입니다.");
			}
		} catch (InputMismatchException e) {	//정수외에 다른 값이 입력될경우 발생하는 오류
			System.out.println("키보드 입력이 잘못되었습니다.");
		} finally {
			System.out.println("프로그램 종료");
			//finally블록은 예외 발생 유무와 상관없이 실행되는 구문이며 생략할 수 있다.
			//예외 처리를 할 때, 예외와 상관없이 반드시 처리해야 하는 구문들을 작성할 때 사용된다.
			//보통 외부 연동이나 예외가 발생해도 정상 종료되어야 할 구문들에서 사용한다.
			//데이터베이스처럼 중간에 연결이 끊기더라도 무조건 연결 종료를 해주고 나옴
		}
		
	}

}
