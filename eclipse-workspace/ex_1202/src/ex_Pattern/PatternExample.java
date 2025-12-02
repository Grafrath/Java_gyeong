package ex_Pattern;

import java.util.regex.Pattern;

public class PatternExample {

	public static void main(String[] args) {
		
		//정규표현식
		//문자열이 내가 정한 형식에 맞는지 검증하는 표현식
		//[] -> 문자, 일치하는 문자가 1개만 있는지 검증
		
		String reg = "^(?.*[a-z])[a-zA-Z]+$";
		String data = "aHKJH";
		
		//Pattern클래스
		//matches(정규표현식, 데이터)
		boolean resule = Pattern.matches(reg, data);
		System.out.println(resule);
		
		System.out.println("----------");
		
		//영어 (소문자, 대문자 상관없음)로 시작하고, 그 뒤는 영어+숫자 섞어서 4~12자리
		
		String regex = "^[A-Za-z][A-Za-z0-9]{3,11}$";
		System.out.println("Abc1".matches(regex));     // true (총4자)
		System.out.println("a123b".matches(regex));    // true
		System.out.println("1abc".matches(regex));     // false (첫글자 숫자)
		System.out.println("Ab".matches(regex));       // false (너무 짧음)
		System.out.println("AveryLongUsername123".matches(regex)); // false (너무 김)
		
	}

}
