package Forenoon;
import java.util.*;

public class ch06 {

	public static void main(String[] args) {
		// 
		
		Scanner sc = new Scanner(System.in);
		//String wold = sc.nextLine();
		int cnt = 0;
		String rev;
		
		/*
		for (char ch : wold.toCharArray())	{
			if (ch == 'a') {
				cnt++;
			}
		}
		
		System.out.println(cnt);
		*/
		
		/*
		cnt = wold.length();
		
		for (int i = cnt-1;i >= 0 ; i--) {
			String rev = wold.substring(i,i+1);
			System.out.print(rev);
		}
		*/
		
		/*
		int index = wold.indexOf("@");
		
		rev = wold.substring(0,index);
		System.out.println(rev);
		*/
		
		String[] season = {"spring","summer","fall","winter"};
		
		season[1] = "여름";
		
		System.out.println(season[1]);
		
		int[] scores = { 83, 90, 87 };
		int sum=0;
		float ave=0;
		
		for (int i : scores) {
			sum += i;
		}
		
		ave = (float)sum/3;
		
		System.out.println("총합은 " + sum);
		System.out.println("평균은 " + ave);
		
		int[] arr1 = { 7, 14, 23, 38, 41, 56, 63, 72, 89, 100 };
		
		for (int i : arr1) {
			if (i%2 == 0) cnt++;
		}
		
		System.out.println("짝수의 갯수는 " + cnt);
		System.out.println("홀수의 갯수는 " + (arr1.length-cnt));
		
		/*
		cnt = sc.nextInt();
		
		int[] arr2 = new int[cnt];
		//배열 항목의 초기값 출력
		for(int i=0; i<cnt; i++) {
			arr2[i] = i+1;
		}
		
		cnt=0;
		
		for (int i : arr2) {
			if (i%2 == 0) cnt++;
		}
		
		System.out.println("짝수의 갯수는 " + cnt);
		System.out.println("홀수의 갯수는 " + (arr2.length-cnt));
		
		int[] number = new int[10];
		sum=0;
		
		for(int i=0; i<number.length; i++) {
			number[i] = new Random().nextInt(30)+1;
			if (number[i]%2 == 0) sum+=number[i];
		}
		
		for(int i=0; i<number.length; i++) {
			System.out.println(number[i]);
		}
		
		System.out.println("짝수의 합 " + sum);

		*/
		
		int tmp;
		int i,j;
		int[] r_num = { 6,2,1,10,5,8,4,3,9,7 };
		
		/*
		for (i=0;i<r_num.length-1;i++) {
			for (j=i+1;j<r_num.length;j++) {
				if (r_num[i] > r_num[j]) {
					tmp = r_num[i];
					r_num[i] = r_num[j];
					r_num[j] = tmp;
				}
			}
		}
		*/

		
		for (i=1;i<r_num.length;i++) {
			for (j=i;j>0;j--) {
				if (r_num[j-1] < r_num[j]) {
					tmp = r_num[j-1];
					r_num[j-1] = r_num[j];
					r_num[j] = tmp;
				}
			}
		}
		
		
		for(i=0; i<r_num.length; i++) {
			System.out.println(r_num[i]);
		}
		
	}

}
