package ex_try_catch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ex_try_catch02 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int [] cards = {4,5,1,2,7,8};
		
		/*
		System.out.print("정수를 입력하세요 : ");
		int score = sc.nextInt();
		
		if(score >= 65) {
			System.out.println("합격입니다.");
		} else {
			System.out.println("불합격입니다.");
		}
		*/
		
		/*
		try {
			System.out.print("정수를 입력하세요 : ");
			int score = sc.nextInt();
			
			if(score >= 65) {
				System.out.println("합격입니다.");
			} else {
				System.out.println("불합격입니다.");
			}
		} catch (InputMismatchException e) {	//정수외에 다른 값이 입력될경우 발생하는 오류
			System.out.println("키보드 입력이 잘못되었습니다.");
		}
		*/
		
		try {
			System.out.print("몇 번째 카드를 뽑으시겠습니까? >>");
			int cardIndex = sc.nextInt();
			System.out.println("뽑은 카드 번호는 : " + cards[cardIndex]);
		} catch (InputMismatchException e) {
			System.out.println("잘못 입력하셨습니다. 숫자만 가능합니다.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("해당 번호의 카드는 없습니다.");
		}
		
		System.out.println("프로그램 종료");
		
	}

}
