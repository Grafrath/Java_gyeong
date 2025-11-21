package ch03;

public class ch04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int x;
		
		for (int i = 0; i < 10;i++) {
			x = ((int)(Math.random() * 20) + 1);
			if ( x > 10 && 20 > x ) {
				System.out.println(true);
			} else {
				System.out.println(false);
			}
		}
		
		//65~92 97~122
		
		char y;
		
		for (int i = 0; i < 10;i++) {
			y = (char)((Math.random() * 52) + (Math.random() < 0.5 ? 'A' : 'a'));
			if (y == 'x' && y == 'X') {
				System.out.println(true);
			} else {
				System.out.println(false);
			}
		}
		
		for (int i = 0; i < 10;i++) {
			y = (char)((Math.random() * 52) + (Math.random() < 0.5 ? 'A' : 'a'));
			if ((y >= 'A' && y <= 'Z') || (y >= 'a' && y <= 'z')) {
				System.out.println(true);
			} else {
				System.out.println(false);
			}
		}
		
		int a = 3;
		int b = 4;
		int c = 5;
		
		if (a > b && a > c) {
			System.out.println(a);
		} else if (b>c) {
			System.out.println(b);
		} else {
			System.out.println(c);
		}
		
		String id = "admin";
		String pw = "1234";
		
		if ( id.equals("admin") && pw.equals("1234") ) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
		
	}

}
