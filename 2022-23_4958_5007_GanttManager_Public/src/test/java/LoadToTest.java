import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import backend.MainControllerFactory;
import dom2app.SimpleTableModel;

public class LoadToTest {
	private SimpleTableModel fileToTest;
	
	@Before
	public void setUp() throws Exception {
		MainControllerFactory mcf = new MainControllerFactory();
		fileToTest = mcf.load("./src/test/resources/input/Eggs.tsv", "\t");
	}

	@Test
	public final void testLoad() {
		String result = "Final\tfor\tGanttManager Project\n"
				+ "TaskId	TaskText	MamaId	Start	End	Cost\t\n"
				+ "100	Prepare Fry	0	1	12	60.0\t\n"
				+ "101	Turn on burner (low)	100	1	1	10.0\t\n"
				+ "102	Break eggs and pour into fry	100	2	4	10.0\t\n"
				+ "103	Steer mixture to avoid sticking	100	5	10	10.0\t\n"
				+ "105	Salt, pepper	100	5	5	10.0\t\n"
				+ "104	Throw yellow cheese into fry	100	6	12	10.0\t\n"
				+ "106	Turn burner off	100	12	12	10.0\t\n"
				+ "200	Prepare the bread	0	10	12	20.0\t\n"
				+ "201	Heat bread in toaster	200	10	12	10.0\t\n"
				+ "202	Little bit of salt, galric spice to bread	200	12	12	10.0\t\n"
				+ "300	Serve eggs	0	13	20	30.0\t\n"
				+ "301	Put bread in plate	300	13	13	10.0\t\n"
				+ "302	Put eggs on bread	300	14	14	10.0\t\n"
				+ "303	Wash fry	300	15	20	10.0\t\n";
		assertEquals("test if MainControllerFactory.load works", result, fileToTest.toString());
	}
}
