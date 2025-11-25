package ch07;
import java.util.*;

public class Exam01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//단일 문자 배열에 단어와 숫자를 섞어 넣는다.
		char[] cards = {'1','L','O','2','V','3','E'};
		String myWord = "";
		Scanner sc = new Scanner(System.in);
		char[] arr1 = new char[sc.nextInt()];
		char[] arr2 = new char[arr1.length];
		int i,j,tmp;
		
		for (char c : cards) {
		    /*if (Character.isLetter(c)) {   
		        myWord += c;
		    }*/
			
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {   
				myWord += c;
			}
			
		}
		
		System.out.println(myWord);
		
		for(i=0; i<arr1.length; i++) {
			arr1[i] = (char)('A'+i);
			arr2[i] = (char)('a'+i);
		}
		
		System.out.println(arr1);
		System.out.println(arr2);
		
		//변수 money에 10 ~ 5000사이의 난수를 발생시켜 넣는다.
		//단 1의자리는 반드시 0이 되도록 한다.
		//ex) 3450, 2100, 60
		//발생된 난수 money를 동전으로 바꾸면 각 동전이 몇개씩 필요한지 출력
		//가능한 적은 수의 동전을 사용한다.
		int[] coin = {500,100,50,10};
		
		int money = ((int)(Math.random() * 500) + 1) * 10;

		System.out.println(money);
		
		for (i=0;i<coin.length;i++) {
			tmp = money / coin[i];
			money %= coin[i];
			coin[i] = tmp;
		}
		
		tmp = 0;
		
		System.out.printf("500원 동전 %d개\n",coin[0],coin[1],coin[2],coin[3]);
		System.out.printf("100원 동전 %d개\n",coin[1]);
		System.out.printf("50원 동전 %d개\n",coin[2]);
		System.out.printf("10원 동전 %d개\n",coin[3]);
		
		//배열의 모든 요소의 합
		int arr[][] = {
				{1,2,3},
				{4,5,6,7,8},
				{9},
				{10,11,12,13},
				{14,15},
				{16,17,18,19,20}
		};
		
		for (i=0;i<arr.length;i++) {
			for (j=0;j<arr[i].length;j++) {
				tmp += arr[i][j];
			}
		}
		
		System.out.println(tmp);
		tmp = 0;
		
		//(int)(Math.random() * 45) + 1
		
		int[] lotto = new int[6];
		
		for (i=0;i<lotto.length;i++) {
			
			lotto[i]=(int)(Math.random() * 45) + 1;
			for (j = 0; j < i; j++) {
				if (lotto[i] == lotto[j]) {
					i--;
					break;
				}
		    }
			
		}
		
		Arrays.sort(lotto);
		System.out.println(Arrays.toString(lotto));
		
	}

}
