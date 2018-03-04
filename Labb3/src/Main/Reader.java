package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
	// String path = "/h/d6/z/tpi13hda/git/algdes-labs-master/spanning-usa/data/USA-highway-miles.txt";
	private LinkedList<String> citiesNotInMst;
	private HashMap<String, LinkedList<Edge>> allEdges;

	public Reader() {
		citiesNotInMst = new LinkedList<String>();
		allEdges = new HashMap<String, LinkedList<Edge>>();
	}

	public LinkedList<String> getCities() {
		return citiesNotInMst;
	}

	public HashMap<String, LinkedList<Edge>> getAllEdges() {
		return allEdges;
	}

	public void read() throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			// try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = null;
			boolean endOfSequence = false;
			while (!endOfSequence) {
				line = br.readLine().trim();
				if (line.contains("[") && line.contains("]")) {
					endOfSequence = true;
				} else
					makeCitiesNotInMst(line);

			}
			for (String city : citiesNotInMst) {
				allEdges.put(city, new LinkedList<Edge>());
			}
			while (line != null && !line.isEmpty()) {
				makeAllEdges(line);
				line = br.readLine();
			}
		}
	}

	private void makeAllEdges(String line) {
		String startCity = readStartC(line);
		String endCity = readEndC(line);
		int cost = readCost(line);
		allEdges.get(startCity).add(new Edge(startCity, endCity, cost));
		allEdges.get(endCity).add(new Edge(endCity, startCity, cost));
	}

	private int readCost(String line) {
		Pattern pattern = Pattern.compile("(\\d+)");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group());
		}

		else {
			System.out.println("FEEEEL");
			return -1;
		}

	}

	private String readEndC(String line) {
		return endCityFromString(line.substring(line.indexOf("--") + 2, line.length()));
	}

	private String endCityFromString(String substring) {
		String s = substring.trim();
		if (s.startsWith("\"")) {
			return s.substring(1, s.indexOf("[") - 2);
		} else
			return s.substring(0, s.indexOf("[") - 1);

	}

	private String readStartC(String line) {
		return cityFromString(line.substring(0, line.indexOf("--")));

	}

	private String cityFromString(String substring) {
		String s = substring.trim();
		if (s.startsWith("\"")) {
			return s.substring(1, s.length() - 1);
		} else
			return s;
	}

	private void makeCitiesNotInMst(String line) {
		if (line.startsWith("\"")) {
			citiesNotInMst.add(line.substring(1, line.length() - 1));
		} else
			citiesNotInMst.add(line);
	}
}
