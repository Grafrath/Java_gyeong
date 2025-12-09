package lambda;

//@FunctionalInterface
@FunctionalInterface
public interface MyCalculator {
	//추상메서드1개만 있으면 함수형인터페이스로 사용가능
	abstract int calc (int i, int n);
	
}
