package lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {
		
		MyCalculator add = (x, y) -> x + y;
		MyCalculator sub = (x, y) -> x - y;
		MyCalculator mul = (x, y) -> x * y;
		
		System.out.println(add.calc(10, 5));
		System.out.println(sub.calc(10, 5));
		System.out.println(mul.calc(10, 5));
		
		User user1 = new User();
		
	}

}
