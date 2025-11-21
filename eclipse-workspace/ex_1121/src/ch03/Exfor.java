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
		
		Scanner sc1 = new Scanner(System.in);
		int x,y,total;
		total = 0;
		x = sc1.nextInt();
		y = sc1.nextInt();
		
		if (x > y) {
			int temp = x;
            x = y;
            y = temp;
		}
		
		for (int i=x;i < y+1;i++) {
			total += i;
		}
		
		System.out.println(total);
		
		sum=0;
		for (int i = 1;i <= 20;i++) {
			if(i%2!= 0 && i%3 != 0) {
				sum += i;
			}
		}
		
		System.out.println(sum);
		
		int num = 12345;
		total = 0;
		
		for (int i = 0;true;i++) {
			if(num/10 == 0) {
				total += num%10;
				break;
			}
			total += num%10;
			num = num/10;
		}
		
		System.out.println(total);
		
		sum = 0;
		for (int i = 1;i <= 10;i++) {
			for (int n = 1;n <= i;n++) {
				sum+=n;
			}
			System.out.println();
		}
		
		System.out.println(sum);
		
	}

}
