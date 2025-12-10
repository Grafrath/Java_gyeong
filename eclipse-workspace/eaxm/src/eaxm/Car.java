package eaxm;

public class Car implements Movable {
	int x;
	int y;
	
	public Car (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(int x, int y) {
		System.out.printf("자동차가 (%d, %d)에서, (%d, %d) 위치로 이동했습니다\n",this.x,this.y,x,y);
	}
}
