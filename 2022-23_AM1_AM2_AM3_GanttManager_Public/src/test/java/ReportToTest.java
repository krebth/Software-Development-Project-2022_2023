import static org.junit.Assert.*;
import java.io.File;
import java.nio.file.Files;

import org.junit.Test;

public class ReportToTest {

	@Test
	public void testTXT() throws Exception{
		File file1 = new File("./src/main/resources/output/shop.txt");
		File file2 = new File("./src/main/resources/output/my_shop.txt");
		
		String file1Contents = new String(Files.readAllBytes(file1.toPath()));
		String file2Contents = new String(Files.readAllBytes(file2.toPath()));
		
		assertEquals(file1Contents, file2Contents);
	}
	
	public void testHTML() throws Exception{
		File file1 = new File("./src/main/resources/output/shop.html");
		File file2 = new File("./src/main/resources/output/my_shop.html");
		
		String file1Contents = new String(Files.readAllBytes(file1.toPath()));
		String file2Contents = new String(Files.readAllBytes(file2.toPath()));
		
		file1Contents = file1Contents.replaceAll("\\s+","");
		file2Contents = file2Contents.replaceAll("\\s+","");
		
		assertEquals(file1Contents, file2Contents);
	}
	
	public void testMarkdown() throws Exception{
		File file1 = new File("./src/main/resources/output/shop.md");
		File file2 = new File("./src/main/resources/output/my_shop.md");
		
		String file1Contents = new String(Files.readAllBytes(file1.toPath()));
		String file2Contents = new String(Files.readAllBytes(file2.toPath()));
		
		file1Contents = file1Contents.replaceAll("\\s+","");
		file2Contents = file2Contents.replaceAll("\\s+","");
		
		assertEquals(file1Contents, file2Contents);
	}

}
