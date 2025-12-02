package ex_ArrayList;
import java.util.*;

public class Ex_ArrayList01 {
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();		
		
		list.add("딸기");
		list.add("사과");
		list.add("망고");
		
		System.out.println("리스트 내용1 : " + list);
		System.out.println();
		
		list.add(2,"수박");	//데이터를 순차적으로 삽입
		
		System.out.println("리스트 내용2 : " + list);
		System.out.println();
		
		list.set(1,"복숭아");	//해당위치 요소 변경
		
		System.out.println("리스트 내용3 : " + list);
		System.out.println();
		
		list.add("바나나");
		System.out.println("리스트 내용4 : " + list);
		System.out.println();
		
		list.remove("바나나");	//해당요소 삭제
		System.out.println("리스트 내용5 : " + list);
		System.out.println();
		
		
		System.out.println(list.get(3));	//리스트의 특정인덱스요소
		
		System.out.println(list.size());	//리스트의 사이즈
		
		System.out.println(list.contains("망고"));	//데이터 존재 여부 확인
		
		//list.clear() 모든요소 삭제
		
		System.out.println(list.indexOf("망고"));	//요소의 인덱스찾기 없으면 -1
		
	}

}
