import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Main2 {
	public static void main(String[] args) {
		Needleman algorithm = new Needleman();
		Reader r = new Reader();
		try {
			r.read2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, String> creatures = r.getCreatures();
		LinkedList<String> creaturesToMatch = r.getCreaturesToMatch();
		
		for(String s : creaturesToMatch){
			String creature1 = s.split(" ")[0].trim();
			String creature2 = s.split(" ")[1].trim();
			algorithm.allignScore(creatures.get(creature1), creatures.get(creature2));
			System.out.println(algorithm.getAlign1());
			System.out.println(algorithm.getAlign2());
			algorithm.clear();
		}
		
	}
}
