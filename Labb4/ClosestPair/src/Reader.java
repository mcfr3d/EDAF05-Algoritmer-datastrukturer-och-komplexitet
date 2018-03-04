import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
	private LinkedList<Point> points;
	String number = "([-+]?\\d*\\.?\\d+(?:[eE][-+]?\\d+)?)";
	String wholePattern = "(\\d+)\\s+(" + number + ")\\s+(" + number + ")";
	Pattern pattern = Pattern.compile(wholePattern);
	// String path = "/h/d6/z/tpi13hda/git/algdes-labs-master/closest-points/data/a280-tsp.txt";

	public Reader() {
		points = new LinkedList<Point>();
	}

	public void read() throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			// try (BufferedReader br = new BufferedReader(new
			// FileReader(path))) {
			String line = br.readLine();
			while (line != null && !line.isEmpty()) {
				addPoint(line);
				line = br.readLine();
			}

		}
	}

	private void addPoint(String line) {
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			double x = Double.parseDouble(matcher.group(2));
			double y = Double.parseDouble(matcher.group(4));
			points.add(new Point(x, y));

		}
	}

	public int getSize() {
		return points.size();
	}

	public LinkedList<Point> getPoints() {
		return points;
	}
}