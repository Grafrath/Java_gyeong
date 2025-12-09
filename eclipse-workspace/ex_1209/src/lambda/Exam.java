package lambda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exam {

	public static void main(String[] args) {
		
		List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9);
		
		//3의 배수의 합
		int sum = nums.stream().filter( x -> x%3==0 ).reduce(0, (a,b) -> a+b );
		System.out.println(sum);
		System.out.println("------------------------");
		
		List<String> words = List.of("apple","banana","avocado","cherry","apricot");
		
		//a로 시작하는 과일만 대문자로 바꾼 리스트
		List<String> list = words.stream()
				.filter(s-> s.startsWith("a"))
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.println(list);
		System.out.println("------------------------");
		
		List<Student> stu = List.of(
				new Student("kim",75),
				new Student("lee",80),
				new Student("park",85),
				new Student("choi",77),
				new Student("jung",90), 
				new Student("jo",83),
				new Student("jun",87),
				new Student("lim",95),
				new Student("jin",60)
				);
		
		//80점 이상만 내림차순으로 리스트로 다시 만들기
		List<Student> stu2 = stu.stream().filter( s -> s.getScore()>=80)
				.sorted((a,b) -> b.getScore()-a.getScore()).collect(Collectors.toList());
		System.out.println(stu2);
		System.out.println("------------------------");
		
		//성이 4인 학생만 리스트로
		List<Student> stu3 = stu.stream().filter( name -> name.getName().length() ==4 ).collect(Collectors.toList());
		System.out.println(stu3);
		System.out.println("------------------------");
		
		//점수가 80점 이상인 학생 수
		Long count = stu.stream().filter( s -> s.getScore()>=80).count();
		System.out.println(count);
		System.out.println("------------------------");
		
		//점수 구간별 그룹핑
		//90이상 A
		//80이상 B
		//70이상 C
		Map<String, List<Student>> grouped = stu.stream().collect(Collectors.groupingBy(s -> {
			int score = s.getScore();
			if (score >= 90) return "A";
			else if (score >= 80) return "B";
			else if (score >= 70) return "C";
			return "F";
			
		}));
		System.out.println(grouped);
		System.out.println("------------------");
		
	}

}
