import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		Reader r = new Reader();
		try {
			r.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShamosHoey sh = new ShamosHoey(r.getPoints());
		//System.out.println("Punkterna: " + sh.shamosHoeyAlgorithm() + "\n" + "Avstånd: " + sh.shamosHoeyAlgorithm().distanceBetweenPoints());
		System.out.println(r.getSize() + " " + sh.shamosHoeyAlgorithm().distanceBetweenPoints());
	}
}
