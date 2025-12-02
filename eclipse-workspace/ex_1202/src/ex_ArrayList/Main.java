package ex_ArrayList;

import java.util.*;
import ex_object.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		List<Integer> list = new ArrayList<>();	
		List<User> userlist = new ArrayList<>();	
		
		userlist.add(new User("홍길동", 90));
		userlist.add(new User("김철수", 80));
		userlist.add(new User("이형희", 70));
		
		for (User s : userlist) {
			s.print();
		}
		
		System.out.println("---------------------------");
		
		for (int i=0;i<10;i++) {
			list.add((int)(Math.random()*30)+1);
		}
		
		for(int value : list){
			if (value%2 == 0) {
				System.out.println(value);
			}
		}
		
		System.out.println("---------------------------");
		
		/*
		List<UserInfo> users = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			UserInfo user = new UserInfo();
			
			System.out.print("아이디를 입력하세요: ");
			String id = sc.nextLine();
			
			boolean exists = false;
			
			for (UserInfo u : users) {
				if (u.getId().equals(id)) {
					exists = true;
					break;
				}
			}
			
			if (exists) {
				System.out.println("아이디가 중복됩니다. 다시 입력하세요.\n");
				continue;
			}
			
			System.out.print("비밀번호를 입력하세요: ");
			user.setPwd(sc.nextLine());
			user.setId(id);
			
			users.add(user);
			
			if (users.size()>2) { break; }
			
        }
		
		System.out.println("입력한 유저 목록:");
		for (UserInfo user : users) {
			System.out.println("ID: " + user.getId() + ", PWD: " + user.getPwd());
		}
		
		sc.close();
		
		System.out.println("---------------------------");
		*/
		
		List<Integer> arr = new ArrayList<>();
		arr.add(10);
		arr.add(20);
		arr.add(30);
		arr.add(40);
		arr.add(50);
		
		int sum = 0;
		double avg = 0;
		
		for (int a : arr) {
			sum+=a;
		}
		
		avg = (double)sum/arr.size();		
		
		System.out.println("합: " + sum);
		System.out.println("평균: " + avg);
		
		List<String> words = new ArrayList<>();
		List<String> longwords = new ArrayList<>();	
		
		words.add("자바");
		words.add("파이썬");
		words.add("C");
		words.add("C++");
		words.add("Go");
		words.add("Oracle");
		
		for (String s : words) {
			if (s.length()>=3) {
				longwords.add(s);
			}
		}
		
		System.out.println(longwords);
		
		Map<String, Integer> Student = new HashMap<>();
		List<Student> stu = new ArrayList<>();		
		
		userlist.sort(Comparator.comparingInt(u -> u.score()));

		User maxUser = userlist.get(userlist.size() - 1);
		System.out.println("최댓값: " + maxUser);
		
	}

}
