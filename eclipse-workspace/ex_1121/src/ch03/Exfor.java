package ch03;
import java.util.*;

public class Exfor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for (int i = 1;i <= 10;i++) {System.out.print(" "+i);}
		System.out.println();
		for (int i = 10;i > 0;i--) {System.out.print(" "+i);}
		
		for (int i = 1;i <= 10;i++) {
			if(i%3==0) System.out.println(i);
		}
		
		int sum=0;
		
		for (int i = 1;i <= 10;i++) {
			sum += i;
		}
		
		System.out.println(sum);
		
		for (int i = 1;i <= 10;i++) {
			sum += i;
		}
		
		Scanner sc = new Scanner(System.in);
		int m;
		m = sc.nextInt();
		
		for (int i = 1;i < 10;i++) {
			System.out.printf("%d x %d = %d\n",m,i,m*i);
		}

	}

}
