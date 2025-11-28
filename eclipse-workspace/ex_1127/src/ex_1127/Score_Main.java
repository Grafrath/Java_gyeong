package ex_1127;
import java.util.*;

public class Score_Main {

	public static void main(String[] args) {
		// 
		
		Score sco = new Score();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("국어 점수를 입력하세요.");
		sco.setKor(sc.nextInt());
		
		System.out.println("영어 점수를 입력하세요.");
		sco.setEng(sc.nextInt());
		
		System.out.println("수학 점수를 입력하세요.");
		sco.setMath(sc.nextInt());
		
		System.out.printf("\n----------------------------- \n\n");
		
		System.out.println("국어 점수: " + sco.getKor());
		System.out.println("영어 점수: " + sco.getEng());
		System.out.println("수학 점수: " + sco.getMath());
		
		System.out.println("총점: " + sco.getTotal());
		System.out.println("평균: " + sco.getAverage());
		
	}

}
