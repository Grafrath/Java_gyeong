package exam;

public class Rectangle extends Shape {
	double width;
	double height;
	
	public Rectangle (double w, double h) {
		this.width = w;
		this.height = h;
	}
	
	public double area() {
		return width*height;
	}	
}
