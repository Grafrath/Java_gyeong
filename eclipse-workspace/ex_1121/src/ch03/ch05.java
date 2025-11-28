package ch03;
import java.util.*;

public class ch05 {

	public static void main(String[] args) {
		// 
		
		//switch
		/*
		switch (비교값) {
			case 조건값 1: 
				실행할 문장
			case 조건값2 :
				실행할 문장
				braek
			default :
				실행할 문장
		} 
		*/
		
		Scanner sc = new Scanner(System.in);
		int month;		
		
		System.out.print("1 ~ 12 사이로 입력 : ");
		month = sc.nextInt();
		
		switch (month) {
			case 2:
				System.out.println("2월은 28일 혹은 29일까지 있습니다.");
				break;
			case 4: case 6: case 9: case 11:
				System.out.printf("%d월은 30일까지 있습니다.\n",month);
				break;
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				System.out.printf("%d월은 31일까지 있습니다.\n",month);
				break;
			default :
				System.out.println("잘못된 입력.");
				break;
		}
		
		int x, y;
		String op;
		
		System.out.print("X : ");
		x = sc.nextInt();
		
		System.out.print("y : ");
		y = sc.nextInt();
		
		System.out.print("연산자 : ");
		op = sc.next();
		
		double result;
		
		switch (op) {
			case "+":
				result = x + y;
		        break;
			case "-":
		    	result = x - y;
		        break;
			case "*":
		    	result = x * y;
		        break;
			case "/":
		        if (y == 0) {
		            System.out.println("0으로 나눌 수 없습니다.");
		            return;
		        }
		        result = x / y;
		        break;

		    default:
		        System.out.println("잘못된 연산자입니다.");
		        return;
		}

		System.out.printf("%f %s %f = %f", x, op, y, result);
		
	}

}
