package Main;

import java.io.IOException;
import java.util.LinkedList;

public class GS {
	// Fredrik Nyberg Hjalmar Danielsson
	public static void main(String[] args) {

		//String path = "/h/dk/c/htx10fny/AlgoDat/algodat-lab-1/Labb 1/TestFiler/test.txt";
		DataReader reader = new DataReader();
		try {
			reader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] women = reader.getWomenArray();
		String[] men = reader.getMenArray();
		int n = reader.getN();
		int[][] manPref = reader.getMenPref();
		int[][] ranking = reader.getWomenRanking();
		LinkedList<Integer> freeMen = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			freeMen.add(i);

		}
		
		int[] next = new int[n];
		int[] current = new int[n];
		for(int i = 0; i<n;i++){
			next[i]=0;
			current[i]=-1;
		}
		

		while (!freeMen.isEmpty() && next[freeMen.getFirst()] < n) {
			int man = freeMen.getFirst();
			int woman = manPref[man][next[man]];
			int man2 = current[woman];
			if (man2 == -1) {
				current[woman] = man;
				next[man]++;
				freeMen.removeFirst();
			} else {
				if (ranking[woman][man2] < ranking[woman][man]) {
					next[man]++;
				} else {
					current[woman] = man;
					next[man]++;
					freeMen.removeFirst();
					freeMen.addFirst(man2);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println(men[current[i]] + " -- " + women[i]);
		}
	}

}
