package Main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
import java.util.stream.Collectors;

 public class DataReader {
	// 	private String path;
		private int n;
		private ArrayList<String> women;
		private ArrayList<String> men;
		private ArrayList<int[]> mensPref;
		private ArrayList<int[]> womensRank;
		
		public  DataReader(){

	//		this.path = path;
			women = new ArrayList<String>();
			men = new ArrayList<String>();
			mensPref = new ArrayList<int[]>();
			womensRank = new ArrayList<int[]>();
		}
		//BufferedReader br = new BufferedReader(new FileReader(path))) 
		//public  DataReader(String path)
		public void read() throws FileNotFoundException, IOException{
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			makeNameVector(br);
			makePreferenceList(br);
			

		}
	}		
		
		public String[] getWomenArray() {
			return women.toArray(new String[women.size()]);
			
		}
		public String[] getMenArray(){
			return men.toArray(new String[men.size()]);
			
		}
		public int getN(){
			return n;
		}
		
		public int[][] getMenPref(){
			int[][] mensPrefArray = new int[n][n];
			for(int i = 0; i < n; i++){
				mensPrefArray[i] = mensPref.get(i);
			}
			return mensPrefArray;
	
		}
		
		public int[][] getWomenRanking(){
			int[][] womenRankArray = new int[n][n];
			for(int i = 0; i < n; i++){
				womenRankArray[i] = womensRank.get(i);
			}
			return womenRankArray;
	
			
		}
		
		
	private void makePreferenceList(BufferedReader br) throws IOException {
		String line = br.readLine();
		while (line != null && !line.isEmpty() && line.length()!=0) {
			boolean isMan = isMan(line);
			if (isMan) {
				int index = getManIndex(line);
				int[] preferedWomen = getPreferences(line);
				mensPref.add(index, preferedWomen);
			}
			else{
				int index = getWomenIndex(line);
				int[] womenRanking = getRankings(line);
				womensRank.add(index, womenRanking);
				
			}
			line = br.readLine();
		}

	}

		private int[] getRankings(String line) {
			String preferedMen = getPreferdPeople(line).trim();
			List<String> list = Arrays.asList(preferedMen.split("\\s"));
			List<Integer> men = list.stream().map(s -> Integer.parseInt(s)).map(s -> (s-1)/2).collect(Collectors.toList());
			int[] menRanking = getRankedArray(men);
		return menRanking;
	}
		

		private int[] getRankedArray(List<Integer> men2) {
			int[] ranked = new int[n];
			for(int i = 0; i<n; i++){
			 ranked[i] = men2.indexOf(i);
			}
			return ranked;
		}

		private int getWomenIndex(String line) {
			try (Scanner s = new Scanner(line)) {
				int index =  s.useDelimiter(":").nextInt();
				index = (index-2)/2;
				return index;
			}
		}
	

		private boolean isMan(String line) {
			try (Scanner s = new Scanner(line)) {
				int index =  s.useDelimiter(":").nextInt();
				 return index % 2 != 0;
			}

		}

		private int[] getPreferences(String line) {
			String preferedWomen = getPreferdPeople(line).trim();
			List<String> list = Arrays.asList(preferedWomen.split("\\s"));
			int[] women = list.stream().mapToInt(s -> Integer.parseInt(s)).map(s -> (s-2)/2).toArray();
	//		List<Integer> preferences = list.stream().map(s -> Integer.parseInt(s)).map(s -> (s-2)/2).collect(Collectors.toList());
			return women;
		}

	private String getPreferdPeople(String line) {
			
			return line.substring(line.indexOf(':')+1);
		}

	private int getManIndex(String line) {
		try (Scanner s = new Scanner(line)) {
			int index =  s.useDelimiter(":").nextInt();
			index = (index-1)/2;
			return index;
		}
	}

		private void makeNameVector(BufferedReader br) throws IOException {
			String line = br.readLine();
			boolean endOfSequence = false;
			while (line != null && !line.isEmpty() && !endOfSequence) {
				if (line.startsWith("n")) {
					Pattern pattern = Pattern.compile("(\\d+)");
					Matcher matcher = pattern.matcher(line);
					if(matcher.find())
					n = Integer.parseInt(matcher.group());
				//	System.out.println(n);
				} else if (Character.isDigit(line.charAt(0))) {
					int digit = getDigitFromString(line);
					String name = getNameFromString(line);
					if (digit % 2 == 0) {
					women.add(name);
					} else {
						men.add(name);
					}
				}
				line = br.readLine();
				if (line == "")
					endOfSequence = true;
			}
		}

		private String getNameFromString(String line) {
			Pattern pattern = Pattern.compile("([a-zA-Z]+)");
			Matcher matcher = pattern.matcher(line);
			String s = "här blev det fel";
			if(matcher.find()){
		    s = matcher.group();}
			return s;
		}

		private int getDigitFromString(String line) {
			try (Scanner s = new Scanner(line)){
				return s.nextInt();
			}
			}

	public void print() {
		System.out.println(n);
		System.out.println();
		for (String s : women) {
			System.out.println(s);
		}
		System.out.println();
		for (String s : men) {
			System.out.println(s);
		}
		System.out.println();
		for (int[] i : mensPref) {
			StringBuilder s = new StringBuilder();
			for (int j : i) {
				s.append(j + ",");
			}
			System.out.println(s.toString());
		}
		System.out.println();
		for (int[] i : womensRank) {
			StringBuilder s = new StringBuilder();
			for (int j : i) {
				s.append(j + ",");
			}
			System.out.println(s.toString());
		}

	}

}