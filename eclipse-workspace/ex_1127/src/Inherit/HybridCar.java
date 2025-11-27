package Inherit;

public class HybridCar extends Car {
	
	int eletricGague;

	HybridCar(int gas, int eletric) {
		super(gas);
		eletricGague = eletric;
	}
	
	void showCurrentGague() {
		super.showCurrentGague();
		System.out.println("잔여 전기량:" + eletricGague);
	}

}
