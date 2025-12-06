package ex_map;

import java.util.*;

public class ex_hashMap {
	
	public static void main(String[] args) {
		
		HashMap<Integer , Character> map = new HashMap<Integer , Character>();
		map.put(1, 'A');
		map.put(2, 'B');
		map.put(3, 'C');
		map.put(4, 'D');
		
		System.out.println(map);
		
		map.put(5, 'A');	// map에 저장되는 value는 중복될 수 있다.
		System.out.println(map);
		
		map.put(1, 'F');	//기존에 같은 이름을 가진 key가 있다면 value를 갱신한다.
		System.out.println(map);
		
		map.remove(3);	//key값을 통해 데이터(value)를 삭제하는 방법
		System.out.println(map);
		System.out.println("map의 사이즈:"+map.size());
		
		char res = map.get(1);	//인덱스가 아닌 킷값으로 벨류를 찾는다.
		System.out.println(res);
		
		if(!map.isEmpty()) {	//.isEmpty() 비어있는지 검증
			System.out.println("비어있지 않습니다.");
		}
		
		//get()은 값이 비어있으면 Null 반환 .getOrDefault()은 비어있으면 기본값 반환
		char v1 = map.getOrDefault(2, 'A');
		System.out.println(v1);
		
		//map에 특정 key가 들어있는지 검증
		System.out.println(map.containsKey(9));
		
		//map에 특정 value가 들어있는지 검증
		System.out.println(map.containsValue('F'));
		
		HashMap<String , Integer> users = new HashMap<String , Integer>();
		users.put("kim", 11111);
		users.put("lee", 2222);
		users.put("park", 3333);
		
		Scanner sc = new Scanner(System.in);
		String id;
		int pw;
		
		while (true) {
			System.out.print("id : ");
			id = sc.next();
			
			if (users.containsKey(id)) {
				System.out.print("pw: ");
				pw = sc.nextInt();
				
				if (users.get(id) == pw) {
					System.out.println("로그인 성공");
					break;
				} else {
					System.out.println("비밀번호 불일치");
				}
			} else {
				System.out.println("아이디 불일치");
			}
		}
		
	}
	
}
