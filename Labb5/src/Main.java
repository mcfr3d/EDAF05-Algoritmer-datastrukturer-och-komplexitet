import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		Needleman algorithm = new Needleman();
		Reader r = new Reader();
		try {
			r.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LinkedList<String> names = r.getNames();
		HashMap<String,String> creatures = r.getCreatures();
		
		HashSet<String> visited = new HashSet<String>();
		
		for(String name1 : names){
			for(String name2 : names){
				if(!name1.equals(name2) && !visited.contains(name2)){
				int	score = algorithm.allignScore(creatures.get(name1), creatures.get(name2));
				System.out.println(name1 + "--" + name2 +": " + score);
				System.out.println(algorithm.getAlign1());
				System.out.println(algorithm.getAlign2());
				algorithm.clear();
				}
			}
			visited.add(name1);
		}
		
			
		
	}
}
