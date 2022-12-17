import static org.junit.Assert.*;
import java.io.File;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;

import app.AppController;

public class ReportToTest {
	private AppController appController;
	
	@Before
	public void setUp() throws Exception {
		appController = new AppController();
		appController.load("./src/test/resources/input/Shop.tsv", "\t");
	}

	@Test
	public void testTXT() throws Exception{
		String path = "./src/test/resources/output/shop.txt";
		String myPath = "./src/test/resources/output/my_shop.txt";
		if (appController.createReportText(myPath)>0) {
			File file1 = new File(path);
			File file2 = new File(myPath);
			
			String file1Contents = new String(Files.readAllBytes(file1.toPath()));
			String file2Contents = new String(Files.readAllBytes(file2.toPath()));
			
			assertEquals(file1Contents, file2Contents);
		}
	}
	
	@Test
	public void testHTML() throws Exception{
		String path = "./src/test/resources/output/shop.html";
		String myPath = "./src/test/resources/output/my_shop.html";
		if (appController.createReportHtml(myPath)>0) {
			File file1 = new File(path);
			File file2 = new File(myPath);
			
			String file1Contents = new String(Files.readAllBytes(file1.toPath()));
			String file2Contents = new String(Files.readAllBytes(file2.toPath()));
			
			file1Contents = file1Contents.replaceAll("\\s+","");
			file2Contents = file2Contents.replaceAll("\\s+","");
			
			assertEquals(file1Contents, file2Contents);
		}
	}
	
	@Test
	public void testMarkdown() throws Exception{
		String path = "./src/test/resources/output/shop.md";
		String myPath = "./src/test/resources/output/my_shop.md";
		if (appController.createReportMd(myPath)>0) {
			File file1 = new File(path);
			File file2 = new File(myPath);
			
			String file1Contents = new String(Files.readAllBytes(file1.toPath()));
			String file2Contents = new String(Files.readAllBytes(file2.toPath()));
			
			file1Contents = file1Contents.replaceAll("\\s+","");
			file2Contents = file2Contents.replaceAll("\\s+","");
			
			assertEquals(file1Contents, file2Contents);
		}
	}
}
