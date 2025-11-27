package Inherit;

public class Car {
	
	int gasolineGague;
	
	Car (int gas) {
		gasolineGague = gas;
	}
	
	void showCurrentGague() {
		System.out.println("잔여 가스량:" + gasolineGague);
	}

}
