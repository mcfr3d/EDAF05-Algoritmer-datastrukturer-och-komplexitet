package Main;

public class Edge implements Comparable<Edge>{
	
 private String home;
 private String dest;
 private int cost;
 
public Edge(String home, String dest, int cost){
	this.home = home;
	this.dest = dest;
	this.cost = cost;
}

public String getHome(){
	return home;
}

public String getDest(){
	return dest;
}

public int getCost(){
	return cost;
}

@Override
public String toString(){
	return home + " - " + dest + " = " + cost ;
}


@Override
public int compareTo(Edge o) {
	// TODO Auto-generated method stub
	
	return this.cost > o.cost ? 1 : this.cost < o.cost ? -1 : 0; //funkar inte med compareto(int)?
}

}