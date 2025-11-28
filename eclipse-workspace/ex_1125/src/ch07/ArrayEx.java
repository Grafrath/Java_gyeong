package ch07;
import java.util.*;

public class ArrayEx {

	public static void main(String[] args) {
		
		
 		int[][] arr = {{25,38,96},{87,56}};
 		int sum = 0;
 		double avg = 0;
 		
 		//System.out.println(arr[0][2]);
 		
 		for (int i=0;i<arr[0].length;i++) {
 			//sum+=arr[0][i];
 		}
 		
 		//avg=sum/3;
 		//System.out.println(avg);
 		
 		int[][] math = new int[3][];
 		math[0] = new int[4];
 		math[1] = new int[2];
 		math[2] = new int[6];
 		
 		for (int i=0;i<math.length;i++) {
 			for (int j=0;j<math[i].length;j++) {
 				math[i][j] = new Random().nextInt(100)+1;
 				sum+=math[i][j];
 				System.out.println(math[i][j]);
 			}
 			
 			avg=(double)sum/math[i].length; 			
 			System.out.printf("%d반의 평균은 %.2f\n", (i+1) , avg);
 			sum=0;
 			
 		}
 		
 		System.out.println("------------------------");
 		
 		//Arrays 클래스
		//배열의 복사, 정렬, 항목 검색 등 배열을 다루기 위한 다양한 메서드를 제공한다.
			
		Integer[] ar = {1,6,3,2,10,7,5,9,4,8};
			
		System.out.println(Arrays.toString(ar));

		//정렬후
		Arrays.sort(ar);
		System.out.println(Arrays.toString(ar));
		
		System.out.println("------------------------");
		
		//얕은 복사
		int[] arr01 = {1,2,3,};
		int[] arr02 = arr01;
		
		System.out.println(Arrays.toString(arr02));
		
		arr01[1]=20;
		
		System.out.println(Arrays.toString(arr02));
		
		System.out.println("------------------------");
		
		//깊은 복사
		//반복문을 이용해거나 Arrays 클래스 또는 system 클래스가 가진 기능을 이용해 값을 직접 넣는다
		
		int[] card01 = {1,6,4,5,3,2};
		int[] newcard01 = new int[card01.length];
		
		//for문을 이용한 깊은 복사
		for (int i=0;i<card01.length;i++) {
			newcard01[i] = card01[i];
		}
		
		System.out.println(Arrays.toString(newcard01));
		
		card01[1]=20;
		
		System.out.println(Arrays.toString(newcard01));
		
		System.out.println("------------------------");
		
		//Arrays클래스를 이용한 깊은 복사
		int [] newCard02 = Arrays.copyOf(card01, card01.length);
		System.out.println(Arrays.toString(newCard02));
		
		System.out.println("------------------------");
		
		card01[1]=100;
		
		System.out.println(Arrays.toString(card01));
		System.out.println(Arrays.toString(newcard01));
		System.out.println(Arrays.toString(newCard02));
		
		
		
	}

}
