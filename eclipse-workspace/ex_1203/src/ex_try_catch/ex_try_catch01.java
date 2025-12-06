package ex_try_catch;

public class ex_try_catch01 {

	public static void main(String[] args) {
		
		//NullPointerException 주소오류
		//배열을 변수를 만들기만 하고 선언하지 않음
		//String[] strArray = null;
		
		//생성되지 않은 배열을 출력하려함
		//System.out.println("strArray[0] = " + strArray[0]);
		
		//NumberForamtException 형식 오류
		//잘못된 형태로 변환하려고 할때 발생하는 예외
		String str01 = "11";
		String str02 = "11.1";
		int num01 = Integer.parseInt(str01);	//문자열을 정수로 변환
		//System.out.println("String to int : " + num01);
		
		//int num02 = Integer.parseInt(str02);	//여기서 예외 발생
		//System.out.println("String to int : " + num02);
		
		//ArrayIndexOutOfBoundsException
		//배열의 범위밖을 넘어갔을때 발생하는 예외
		
		int [] arr = {1,6,7,8,10};
		//System.out.println(arr[6]);	//배열의 범위는 5까지인데 6을 요청함
		
		//ArithmeticException
		//정수를 0으로 나눌경우 발생함
		int result = 10/0;
		
		
	}

}
