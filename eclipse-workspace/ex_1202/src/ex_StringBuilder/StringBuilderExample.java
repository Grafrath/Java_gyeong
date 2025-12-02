package ex_StringBuilder;

public class StringBuilderExample {

	public static void main(String[] args) {
		
		String data = new StringBuilder()
				.append("DEF") //추가
				.insert(0, "ABC") //문자열을 지정위치에 추가
				.delete(3, 4) //삭제 시작, 끝 문자열
				.toString(); // 완성된 문자열을 반환
		
		System.out.println(data);
		
	}

}
