package lambda;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex_stream1 {
	
	public static void main(String[] args) {
		
		List<String> names = List.of("철수","영희","민수");
		
		//스트림 생성
		Stream<String> s_names = names.stream();
		
		//중간연산
		//filter - 조건에 맞는것만
		List<Integer> nums = List.of(1,3,5,4,7,6,8,9,2,1,1,1);
		nums.stream().filter(x -> x%2==0).forEach(System.out::println);
		System.out.println("------------------");
		
		//map - 요소에 처리를 해주는 중간연산
		names.stream().map(x -> x + "님").forEach(System.out::println);
		System.out.println("------------------");
		
		//sorted - 정렬, 오름차순
		nums.stream().sorted().forEach(System.out::println);
		System.out.println("------------------");
		
		nums.stream().sorted((a,b) -> b-a).forEach(System.out::println); //내림차순
		System.out.println("------------------");
		
		//distinct - 중복제거
		nums.stream().distinct().forEach(System.out::println);
		System.out.println("------------------");
		
		//limit or skip - 일부만 잘라쓰는 중간연산 
		nums.stream().limit(3).forEach(System.out::println); //앞에서부터 3개
		System.out.println("------------------");
		
		nums.stream().skip(3).forEach(System.out::println); //3개 제외
		System.out.println("------------------");
		
		//스트림으로 가공한 결과를 list, set같은 컬렉션으로 다시 만들경우 Collection(Collectors...)를 사용
		List<Integer> evenlist = nums.stream()
				.filter(x -> x%2==0)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		
		//Collectors에 들어있는 메소드
		//toList() 리스트로 반환
		//toSet() set으로 반환
		//joining(", ") 문자열 공백제거
		//groupingBy(...) 특정기준으로 그룹 나누기
		
		List<String> item = List.of("A","B","A","C");
		Set<String> seT = item.stream().collect(Collectors.toSet());
		System.out.println(seT);
		System.out.println("------------------");
		
		List<String> words = List.of("Java","Spring","React");
		String join = words.stream().collect(Collectors.joining(", "));
		System.out.println(join);
		System.out.println("------------------");
		
		List<String> names2 = List.of("kim","lee","park","choi","jin");
		Map<Integer, List<String>> grouped = names2.stream().collect(Collectors.groupingBy(name -> name.length()));
		System.out.println(grouped);
		System.out.println("------------------");
		
		//count 갯수반환
		long count = nums.stream().filter(x -> x%2==0).count();
		System.out.println(count);
		System.out.println("------------------");
		
		//조건 만족여부 anyMatch 하나라도 만족하면 참
		boolean any = nums.stream().anyMatch(x -> x%2==0);
		System.out.println(any);
		System.out.println("------------------");
		
		//allMatch 모두 만족해야 참
		boolean all = nums.stream().allMatch(x -> x%2==0);
		System.out.println(all);
		System.out.println("------------------");
		
		//noneMatch 조건과 불일치해야 참
		boolean 논 = nums.stream().noneMatch(x -> x==0);
		System.out.println(논);
		System.out.println("------------------");
		
		//하나만 찾기 findFirst
		Optional<String> res1 = names.stream().filter(s-> s.startsWith("영")).findFirst();
		System.out.println(res1);
		System.out.println("------------------");
		
		//아무거나 찾기 findAny
		Optional<String> res2 = names.stream().findAny();
		System.out.println(res2);
		System.out.println("------------------");
		
		//reduce - 값을 누적시킨다
		int sum = nums.stream().reduce(0, (i,n) -> i+n);
		System.out.println(sum);
		System.out.println("------------------");
		
	}

}
