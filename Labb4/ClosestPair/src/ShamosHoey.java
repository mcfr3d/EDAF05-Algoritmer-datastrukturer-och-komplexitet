import java.util.ArrayList;
import java.util.LinkedList;

public class ShamosHoey {
	private LinkedList<Point> points;

	public ShamosHoey(LinkedList<Point> points) {
		this.points = points;
	}

	public PointPair shamosHoeyAlgorithm() {
		
		ArrayList<Point> px = new ArrayList<Point>();
		ArrayList<Point> py = new ArrayList<Point>();
		
		for (Point p : points) {
			px.add(p);
			py.add(p);
		}
		
		px.sort((Point p1, Point p2) -> p1.compareX(p2));
		py.sort((Point p1, Point p2) -> p1.compareY(p2));

		return closestPairRec(px, py);
	}

	private PointPair closestPairRec(ArrayList<Point> px, ArrayList<Point> py) {

		if (px.size() <= 3) {
			double distance = Double.MAX_VALUE;
			PointPair minimumPointPair = null;
			for (Point p1 : px) {
				for (Point p2 : py) {
					if (p1.distanceTo(p2) < distance && p1 != p2) {
						distance = p1.distanceTo(p2);
						minimumPointPair = new PointPair(p1, p2);
					}
				}
			}
			return minimumPointPair;
		}

		ArrayList<Point> qx = new ArrayList<Point>();
		ArrayList<Point> qy = new ArrayList<Point>();

		ArrayList<Point> rx = new ArrayList<Point>();
		ArrayList<Point> ry = new ArrayList<Point>();

		for (int i = 0; i < px.size(); i++) {
			if (i < px.size() / 2) {
				qx.add(px.get(i));
			} else {
				rx.add(px.get(i));
			}
		}

		for (int i = 0; i < py.size(); i++) {
			if (i < py.size() / 2) {
				qy.add(py.get(i));
			} else {
				ry.add(py.get(i));
			}
		}

		PointPair minInQ = closestPairRec(qx, qy);
		PointPair minInR = closestPairRec(rx, ry);

		double delta = Math.min(minInQ.distanceBetweenPoints(), minInR.distanceBetweenPoints());
		double x = qx.get(qx.size() - 1).getX();

		ArrayList<Point> s = new ArrayList<Point>();
		for (Point p : px) {
			if (p.getX() >= x - delta && p.getX() <= x + delta) {
				s.add(p);
			}
			if (p.getX() > x + delta) {
				break;
			}
		}

		s.sort((Point p1, Point p2) -> p1.compareY(p2));

		double distanceInSy = Double.MAX_VALUE;
		PointPair minimumPointPairS = null;

		for (int i = 0; i < s.size(); i++) {
			for (int j = 1; j <= 15 && i + j < s.size(); j++) {
				double tempDist = s.get(i).distanceTo(s.get(i + j));
				if (tempDist < distanceInSy) {
					distanceInSy = tempDist;
					minimumPointPairS = new PointPair(s.get(i), s.get(i + j));
				}
			}
		}

		if (distanceInSy < delta) {
			return minimumPointPairS;
		} else if (minInQ.distanceBetweenPoints() < minInR.distanceBetweenPoints()) {
			return minInQ;
		} else
			return minInR;
	}
}
