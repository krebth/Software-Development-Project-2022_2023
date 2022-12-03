import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import backend.MainControllerFactory;
import dom2app.SimpleTableModel;

public class PrefixToTest {
	private SimpleTableModel fileToTest;
	
	@Before
	public void setUp() throws Exception {
		MainControllerFactory mcf = new MainControllerFactory();
		fileToTest = mcf.load("./src/test/resources/input/Eggs.tsv", "\t");
		fileToTest = mcf.getTasksByPrefix("P");
	}
	
	@Test
	public final void testPrefix() {
		String result = "	for\t\n"
				+ "TaskId	TaskText	MamaId	Start	End	Cost\t\n"
				+ "100	Prepare Fry	0	1	12	60\t\n"
				+ "200	Prepare the bread	0	10	12	20\t\n"
				+ "301	Put bread in plate	300	13	13	10\t\n"
				+ "302	Put eggs on bread	300	14	14	10\t\n";
		assertEquals("test if MainControllerFactory.getTasksByPrefix works", result, fileToTest.toString());
	}

}
