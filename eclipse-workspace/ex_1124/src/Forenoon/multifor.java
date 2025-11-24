package Forenoon;
import java.util.*;

public class multifor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i, j;
		Scanner sc = new Scanner(System.in);
		int ran = new Random().nextInt(50)+1;
		int ans=0;
		int kor, eng, mat;
		int cnt=1;
		
		
		
		for (i=1;i<=6;i++) {
			for (j=1;j<=6;j++) {
				if (i+j==6) {
					System.out.print(i+",");
					System.out.println(j);
				}
			}
			
		}
		
		System.out.println();
		
		for (i=2;i<=9;i++) {
			for (j=1;j<=9;j++) {
				System.out.println(i + " x " + j + " = " + (i*j));
			}
			
			System.out.println();
		}
		
		System.out.println();
		
		while (ans != ran) {
			System.out.println("1~50 중 정답은? ");
			ans=sc.nextInt();
			
			if (ans > ran) {
				System.out.println("down");
			} else if (ans < ran) {
				System.out.println("up");
			} else {
				System.out.println("정답");
			}
			
			System.out.println();
			
		}
		
		System.out.println();
		
		do {
			
			System.out.print("국어점수 : ");
			kor = sc.nextInt();
			System.out.print("영어점수 : ");
			eng = sc.nextInt();
			System.out.print("수학점수 : ");
			mat = sc.nextInt();
			
			System.out.println();
			
			float average = (float)(kor + eng + mat)/3;
			System.out.printf("세 과목의 평균은 %.1f점 입니다.",average);
			
			if (average >= 60 && kor >= 40 && eng >= 40 && mat >= 40) {
				System.out.println("합격입니다.");
				break;
			} else {
				System.out.println("불합격입니다.");
			}
			
			System.out.println();
			
		} while (true);	
		
		System.out.println();
		
		for (i=1;i<=10;i++) {
			System.out.println("1~50 중 정답은? ");
			ans=sc.nextInt();
			
			if (ans > ran) {
				System.out.println("down");
			} else if (ans < ran) {
				System.out.println("up");
			} else {
				System.out.println("정답");
			}
			
			System.out.println();
			
		}
		
		System.out.println();
		
		while (cnt < 11) {
			System.out.println("1~50 중 정답은? ");
			ans=sc.nextInt();
			
			if (ans > ran) {
				System.out.println("down");
			} else if (ans < ran) {
				System.out.println("up");
			} else {
				System.out.println("정답");
			}
			
			System.out.println();
			cnt++;
			
		}
		
		//Continue 조건 건너뛰기

	}

}