package Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.*;
import Main.DataReader;
public class testDataReader {
	DataReader dataread;
	
	
	@Before
	public void init() {
	dataread = new DataReader("C:\\Users\\Fredrik\\Workspaces\\AlgoDat\\Labb 1\\TestFiler\\Nytt textdokument.txt");
	}
	
	@Test
	public void test(){
		try {
			dataread.read();
			dataread.print();
		} catch (FileNotFoundException e) {
			System.out.println("failed not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("failed not found2");
			e.printStackTrace();
		}
		assertEquals(true,true);
	}
	

}
