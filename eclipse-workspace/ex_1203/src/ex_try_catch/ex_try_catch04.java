package ex_try_catch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ex_try_catch04 {
	
	//main을 처리하는 JVM이 예외처리를 한다.
	public static void main(String[] args) {
		
		throwsExample t = new throwsExample();
		Scanner sc = new Scanner(System.in);
		int val = 0;
		
		try {
			int result = t.divide(10, 0);
			System.out.println("결과: " + result);
		} catch (Exception e) {
			System.out.println("0으로 나눌 수 없습니다.");
			System.out.println("에러 메세지 : " +e.getMessage());
		}
		
		while(true) {
			try {
				System.out.print("숫자를 입력하세요(0~50) : ");
				val = sc.nextInt();
				
				if(val == -1) {
					break;
				}
				
				if(val < 0 || val > 50) {
					throw new Exception("숫자의 허용범위가 아닙니다.");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("에러 메세지 : " +e.getMessage());
			}
		}
		
	}

}
