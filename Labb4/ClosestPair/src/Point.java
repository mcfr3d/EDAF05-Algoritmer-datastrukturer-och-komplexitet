
public class Point {
	private double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanceTo(Point p) {
		return Math.hypot(x - p.x, y - p.y);
	}

	public int compareX(Point p) {
		return x < p.x ? -1 : x == p.x ? 0 : 1;
	}

	public int compareY(Point p) {
		return y < p.y ? -1 : y == p.y ? 0 : 1;
	}

	public double getX() {
		return x;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
