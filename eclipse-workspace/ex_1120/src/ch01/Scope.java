package ch01;

public class Scope {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//변수의 사용범위
		int var1; //main()메소드 영역에서 변수의 선언

		if (true) {
			
			int var2; //if 블록에서 선언
			//현재 위치에서는 var1, var2 사용이 가능하다.
			
		}

		for (;true;) {
			
			int var3; // for 블록에서 선언
			//현재 위치에서는 var1,var3는 사용할 수 있다.
			//var2는 사용할 수 없다.
			
		}

		//var1 사용 가능
		//var2,var3는 사용할 수 없다.

	}

}
