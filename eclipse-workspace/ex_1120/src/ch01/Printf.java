package ch01;

public class Printf {

	public static void main(String[] args) {
		// 
		
		String name = "최가";
		String address = "부평구";
		int age = 20;
		double height = 184.8;
		
		System.out.printf("제 이름은 %s입니다.\n",name);
		System.out.printf("제 나이는 %d살입니다.\n",age);
		System.out.printf("제 주소는 %s입니다.\n",address);
		System.out.printf("제 키는 %.1f cm입니다.\n",height);
		
		int x, y;
		x = 10;
		y = 5;
		
		System.out.printf("%d 와 %d의 합은 %d 이다. \n", x, y, x + y);

	}

}
