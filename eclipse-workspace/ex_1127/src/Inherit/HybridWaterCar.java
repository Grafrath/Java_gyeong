package Inherit;

public class HybridWaterCar extends HybridCar {
	
	int waterGague;

	HybridWaterCar(int gas, int eletric, int water) {
		super(gas, eletric);
		waterGague = water;
	}
	
	void showCurrentGague() {
		super.showCurrentGague();
		System.out.println("잔여 수량:" + waterGague);
	}

}
