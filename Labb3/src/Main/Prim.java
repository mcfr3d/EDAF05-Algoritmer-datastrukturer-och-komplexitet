package Main;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Prim {

	private HashMap<String, LinkedList<Edge>> allEdges;
	private LinkedList<String> citiesNotInMst;
	
	private LinkedList<Edge> mst;
//	private LinkedList<String> citiesInMst;
	private HashSet<String> citiesInMst;
	private PriorityQueue<Edge> edgePriority;

	public Prim() {
		mst = new LinkedList<Edge>();
//		citiesInMst2 = new LinkedList<String>();
		citiesInMst = new HashSet<String>();
		edgePriority = new PriorityQueue<Edge>();
		generateLists();
	}
//m = antal edges
	// n = antal noder
	public void primAlgorithm(){
		citiesInMst.add(citiesNotInMst.getFirst());   // add and getFirst for LinkedList is O(1) add for set O(1)
		citiesNotInMst.removeFirst(); // removeFirst for LinkedList is O(1)
		edgePriority.addAll(allEdges.get(citiesInMst.iterator().next())); // addAll does add which is O(log(n)), HashMap get is O(1) (rare worst case O(n)), getFirst for LinkedList is O(1)
		while(!citiesNotInMst.isEmpty() && !edgePriority.isEmpty()){   // O(1) for both
			Edge e = edgePriority.poll();   //poll is O(log(n))
			if(!citiesInMst.contains(e.getDest())){   //contains is O(n)? for linkedlist,   for set O(1)
				citiesInMst.add(e.getDest()); //O(1)
				mst.add(e); // O(1)
				edgePriority.addAll(allEdges.get(e.getDest()));   //map.get O(1), add priority O(log(n)). addAll for PriorityQueue??
//mlog(n)
			}
		}
	}
	public void generateLists(){
		Reader r = new Reader();
		try {
			r.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		citiesNotInMst = r.getCities();
		allEdges = r.getAllEdges();
	}
	
	public void print(){
		int totalCost = 0;
		for(Edge e: mst){
			System.out.println(e);
			totalCost += e.getCost();
		}
		
	System.out.println("total cost = " + totalCost);	
	}
	
	
	/*private void generateLists2() {
		citiesNotInMst = new LinkedList<String>();
		for (char i = 'A'; i <= 'H'; i++) {
			citiesNotInMst.add("" + i);
		}
		allEdges = new HashMap<String, LinkedList<Edge>>();

		allEdges.put("A", new LinkedList<Edge>());
		allEdges.put("B", new LinkedList<Edge>());
		allEdges.put("C", new LinkedList<Edge>());
		allEdges.put("D", new LinkedList<Edge>());
		allEdges.put("E", new LinkedList<Edge>());
		allEdges.put("F", new LinkedList<Edge>());
		allEdges.put("G", new LinkedList<Edge>());
		allEdges.put("H", new LinkedList<Edge>());

		allEdges.get("A").add(new Edge("A", "H", 16));
		allEdges.get("A").add(new Edge("A", "E", 38));
		allEdges.get("A").add(new Edge("A", "C", 26));
		allEdges.get("A").add(new Edge("A", "G", 58));

		allEdges.get("B").add(new Edge("B", "F", 32));
		allEdges.get("B").add(new Edge("B", "H", 19));
		allEdges.get("B").add(new Edge("B", "C", 36));
		allEdges.get("B").add(new Edge("B", "D", 29));
		
		allEdges.get("C").add(new Edge("C", "A", 26));
		allEdges.get("C").add(new Edge("C", "B", 36));
		allEdges.get("C").add(new Edge("C", "D", 17));
		allEdges.get("C").add(new Edge("C", "H", 34));
		allEdges.get("C").add(new Edge("C","G",40));

		allEdges.get("D").add(new Edge("D", "B", 29));
		allEdges.get("D").add(new Edge("D", "C", 17));
		allEdges.get("D").add(new Edge("D", "G", 52));
		
		allEdges.get("E").add(new Edge("E", "A", 38));
		allEdges.get("E").add(new Edge("E", "F", 35));
		allEdges.get("E").add(new Edge("E", "G", 93));
		allEdges.get("E").add(new Edge("E", "H", 37));
		
		allEdges.get("F").add(new Edge("F", "B", 32));
		allEdges.get("F").add(new Edge("F", "H", 28));
		allEdges.get("F").add(new Edge("F", "E", 35));

		allEdges.get("G").add(new Edge("G", "A", 58));
		allEdges.get("G").add(new Edge("G", "E", 93));
		allEdges.get("G").add(new Edge("G", "D", 52));
		allEdges.get("G").add(new Edge("G","C",40));

		allEdges.get("H").add(new Edge("H", "A", 16));
		allEdges.get("H").add(new Edge("H", "B", 19));
		allEdges.get("H").add(new Edge("H", "C", 34));
		allEdges.get("H").add(new Edge("H", "E", 37));
		allEdges.get("H").add(new Edge("H", "F", 28));
	}*/

}