
public class PointPair {
	private Point p1, p2;

	public PointPair(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public double distanceBetweenPoints() {
		return p1.distanceTo(p2);
	}

	public String toString() {
		return p1.toString() + ", " + p2.toString();
	}
}
