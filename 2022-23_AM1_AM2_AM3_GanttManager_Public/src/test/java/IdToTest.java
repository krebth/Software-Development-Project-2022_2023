import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import backend.MainControllerFactory;
import dom2app.SimpleTableModel;

public class IdToTest {
private SimpleTableModel fileToTest;
	
	@Before
	public void setUp() throws Exception {
		MainControllerFactory mcf = new MainControllerFactory();
		fileToTest = mcf.load("./src/test/resources/input/Eggs.tsv", "\t");
		fileToTest = mcf.getTaskById(105);
	}
	
	@Test
	public final void testId() {
		String result = "	for\t\n"
				+ "TaskId	TaskText	MamaId	Start	End	Cost\t\n"
				+ "105	Salt, pepper	100	5	5	10\t\n";
		assertEquals("test if MainControllerFactory.getTaskById works", result, fileToTest.toString());
	}
}
