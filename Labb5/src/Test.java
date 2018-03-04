import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	System.out.println(Blosum.getValue('A', 'B'));
		Reader r = new Reader();
		try {
			r.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s: r.getNames()){
			System.out.println(s);
		}
		for(String s: r.getCreatures().values()){
			System.out.println(s);
	}

}
}