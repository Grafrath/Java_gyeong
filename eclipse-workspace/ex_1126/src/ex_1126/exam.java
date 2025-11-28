package ex_1126;
import java.util.*;

class Calculator {    
    double fToc () {
    	Scanner sc = new Scanner(System.in);
    	double cel = sc.nextDouble();
		return (cel-32)/1.8;
    }
    
    void circleRound(int r) {
		int radius = r;
		System.out.printf("원의 둘레: %.1f", (2*3.14*radius));
	}
    
    void cicleExtent() {
    	System.out.println("반지름을 입력하시오: ");
    	Scanner sc = new Scanner(System.in);
    	int r = sc.nextInt();
    	
    	System.out.println("원의 넓이: " + (r*r*3.14));
    	
    }
    
    double calc(int a, int b, String op) {
    	switch(op) {
    		case "+": return a + b;
    		
    		case "-": return a - b;
    		
    		case "*": return a * b;
    		
    		case "/":
    			if (b == 0) {
    				System.out.println("0으로 나눌 수 없습니다!");
    				return 0;
    			}
    		return (double)a / b;
    		
    		default:
    			System.out.println("지원하지 않는 연산자입니다.");
    			return 0;
        }
    }
    
}


public class exam {	

	public static void main(String[] args) {
		// 
		
		Scanner sc = new Scanner(System.in);
		Calculator cal = new Calculator();
		TimesTable time = new TimesTable();
		int r;
		
		
		
		cal.cicleExtent();
		
		System.out.println("원의 반지름을 입력하시오:" );
		r = sc.nextInt();
		
		cal.circleRound(r);
		
		System.out.println();
		
		System.out.println("화씨를 입력: ");
		System.out.printf("섭씨온드는 %.2f 입니다.\n", cal.fToc());
		
		System.out.println("원하는 숫자를 입력하세요: ");
		r = sc.nextInt();
		
		time.showTable(r);

	}
	
}
