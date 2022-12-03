import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import backend.MainControllerFactory;
import dom2app.SimpleTableModel;

public class TopLevelTasksToTest {
	private SimpleTableModel fileToTest;
	
	@Before
	public void setUp() throws Exception {
		MainControllerFactory mcf = new MainControllerFactory();
		fileToTest = mcf.load("./src/test/resources/input/Eggs.tsv", "\t");
		fileToTest = mcf.getTopLevelTasks();
	}
	
	@Test
	public final void testTopLevelTasks() {
		String result = "	for\t\n"
				+ "TaskId	TaskText	MamaId	Start	End	Cost\t\n"
				+ "100	Prepare Fry	0	1	12	60\t\n"
				+ "200	Prepare the bread	0	10	12	20\t\n"
				+ "300	Serve eggs	0	13	20	30\t\n";
		assertEquals("test if MainControllerFactory.getTopLevelTasks works", result, fileToTest.toString());
	}

}
