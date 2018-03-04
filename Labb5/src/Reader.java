import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

public class Reader {

	HashMap<String, String> creatures;
	LinkedList<String> creatureNames;
	String path = "/h/d6/z/tpi13hda/git/algdes-labs-master/gorilla/data/HbB_FASTAs-in.txt";
	LinkedList<String> creaturesToMatch;

	public Reader() {
		creatures = new HashMap<String, String>();
		creatureNames = new LinkedList<String>();
		creaturesToMatch = new LinkedList<String>();
	}

	public void read() throws IOException {
		// try (BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in))) {

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				StringBuilder sb = new StringBuilder();
				if (line.startsWith(">")) {
					String name = line.substring(1).split(" ")[0].trim();
					creatureNames.add(name);
					line = br.readLine();
					while (line != null && !line.startsWith(">")) {
						sb.append(line);
						line = br.readLine();
					}
					creatures.put(name, sb.toString());

				}
			}

		}
	}

	public void read2() throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
//		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			String[] nq = line.split(" ");
			int n = Integer.parseInt(nq[0].trim());
			int q = Integer.parseInt(nq[1].trim());
			for(int i = 0; i<n;i++){
				String name = br.readLine().trim();
				creatureNames.add(name);
				creatures.put(name, br.readLine().trim());			
			}
			for(int i = 0; i < q; i++){
				creaturesToMatch.add(br.readLine());
			}

		}
	}

	public HashMap<String, String> getCreatures() {
		return creatures;
	}

	public LinkedList<String> getNames() {
		return creatureNames;
	}
	
	public LinkedList<String> getCreaturesToMatch(){
		return creaturesToMatch;
	}

}
