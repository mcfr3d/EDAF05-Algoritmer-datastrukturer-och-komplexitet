package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class GStest {
	public static void main(String[] args){
		String s = "" + System.getProperty("user.dir") + args[0];
		File file = new File(s);
		Scanner scan;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] men = {"Sheldon", "Rajesh", "Howard", "Leonard"};
		String[] women = {"Amy", "Penny", "Bernadette", "Priya"};
		int n = men.length;
		int[][] manPref = {
							{0, 1, 2, 3},
							{2, 1, 0, 3},
							{2, 1, 3, 0},
							{1, 3, 2, 0}
										};
		//converstion -2 /2 ?
		int[][] ranking = {
							{1, 4, 3, 2},
							{3, 2, 4, 1},
							{4, 2, 1, 3},
							{3, 4, 2, 1}
										};
		LinkedList<Integer> freeMen = new LinkedList<Integer>();
		for(int i = 0; i < n; i++){
			freeMen.add(i);
		}
		int[] next = {0, 0, 0, 0};
		int[] current = {-1, -1, -1, -1};
		
		while(!freeMen.isEmpty() && next[freeMen.getFirst()] < n){
			int man = freeMen.getFirst();
			int woman = manPref[man][next[man]];
			int man2 = current[woman];
			if(man2 == -1){
				current[woman] = man;
				next[man]++;
				freeMen.removeFirst();
			} else {
				if(ranking[woman][man2] < ranking[woman][man]){
					next[man]++;
				} else {
					current[woman] = man;
					next[man]++;
					freeMen.removeFirst();
					freeMen.addFirst(man2);
				}
			}
		}
		for(int i = 0; i < n; i++){
			System.out.println(men[current[i]] + " -- " + women[i]);
		}
	}
}
