package ex_Math;

public class MathExample {
	public static void main(String[] args) {
		
		//Math 클래스에 있는 내용은 모두 static이기에 객체를 만들 필요가없다.	
		//ceil 올림
		double v1 = Math.ceil(5.3);
		System.out.println(v1);
		
		//floor 내림
		double v2 = Math.floor(5.3);
		System.out.println(v2);
		
		//max 큰값 반환
		long v3 = Math.max(3, 7);
		System.out.println(v3);
		
		//min 작은값 반환
		long v4 = Math.min(3, 7);
		System.out.println(v4);
		
		//반올림, 소숫점 2번째자리까지 얻기
		double value = 12.3456;
		double temp1 = value * 100;
		long temp2 = Math.round(temp1);
		double v5 = temp2 / 100.0;
		System.out.println(v5);
		
		//random() 0~1사이의 실수를 double타입으로 반환
		double ran = Math.random();
		System.out.println(ran);
		int num = (int)(Math.random()*10)+1;	//1~10
		System.out.println(num);
				
	}
}
