package eaxm;

public class Point {
	int x;
	int y;
	
	public Point () {
		x = 0;
		y = 0;
	}
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void printPoint() {
		System.out.println("x= " + x + ", y= " + y);
	}
}
