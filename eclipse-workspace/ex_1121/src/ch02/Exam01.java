package ch02;
import java.util.*;

public class Exam01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//      과수원이 있다.
//
//      배, 사과, 오렌지를 키우고 있는데 하루에 생산되는 양은 각각 5,  7,   5개이다.
//      과수원에서 하루에 생산되는 총 개수를 출력하고, 시간당 전체 과일의 평균 생산 갯수를 출력하시오. 
//      평균값을 담는 변수는 float으로 할 것.
      
      
//      상자 하나에는 농구공이 5개가 들어갈 수 있다.
//      만일 농구공이 23개라면 몇개의 상자가 필요한가?
      
//      국어,영어,수학에 대한 점수를 키보드를 이용해 정수로 입력받고
//      1. 세 과목에 대한 합계 출력하기
//      2. 평균 출력하기 (합계/3.0)
//
//      세 과목의 점수와 평균을 가지고 합격 여부를 처리하는데
//      세 과목의 점수가 각각 40점 이상이면서 평균이 60점 이상일때 합격
//      아니면 불합격
		
		int pear, apple, orange;
		pear = 5;
		apple = 7;
		orange = 5;
		float avg = (float)(pear + apple + orange) / 3;
		
		System.out.printf("배, 사과, 오렌지의 하루 생산량은 각각 %d, %d, %d개, 총 갯수는 %d개입니다.%n", pear, apple, orange, pear + apple + orange );
		System.out.printf("평균 시간당 생산 갯수는 %.1f개 입니다.\n", avg/24);
		
		int ball = 23;
		int basket = (ball % 5 == 0) ? ball / 5 : (ball / 5)+1 ;
		
		System.out.printf("바구니는 총 %d개 필요합니다.\n", basket);
		
		Scanner sc = new Scanner(System.in);
		int kor, eng, mat;		
		
		System.out.print("국어점수 : ");
		kor = sc.nextInt();
		System.out.print("영어점수 : ");
		eng = sc.nextInt();
		System.out.print("수학점수 : ");
		mat = sc.nextInt();
		
		float average = (float)(kor + eng + mat)/3;
		
		System.out.printf("세 과목의 평균은 %.1f점 입니다.",average);
		
		if (average >= 60 && kor >= 40 && eng >= 40 && mat >= 40) {
			System.out.println("합격입니다.");
		} else {
			System.out.println("불합격입니다.");
		}
		
	}

}
