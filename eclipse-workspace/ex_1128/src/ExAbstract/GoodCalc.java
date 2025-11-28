package ExAbstract;

public class GoodCalc extends Calculator {

	@Override
	public int add(int a, int b) {
		return a+b;
	}

	@Override
	public int sub(int a, int b) {
		return a-b;
	}

	@Override
	public int average(int a, int b) {
		return add(a, b)/2;
	}

}
