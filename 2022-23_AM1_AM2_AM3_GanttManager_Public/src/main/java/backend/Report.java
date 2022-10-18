package backend;

import java.io.IOException;
import java.util.ArrayList;
import createFile.CreateText;
import createFile.CreateHtml;
import file.Task;

public class Report {
	private ReportType type;
	private String path;
	private ArrayList<Task> sortedList;
	private String[] pColumnNames;
	
	public Report(String path, ReportType type, ArrayList<Task> sortedList, String[] pColumnNames) {
		this.type = type;
		this.path = path;
		this.sortedList = sortedList;
		this.pColumnNames = pColumnNames;
	}
	
	public int makeFile() throws IOException {
		switch (type) {
		case TEXT:
			CreateText text = new CreateText(path, sortedList, pColumnNames);
			text.toText();
			break;
		/*case MD:
			createMd(path);
			break;*/
		case HTML:
			CreateHtml html = new CreateHtml(path,sortedList,pColumnNames);
			html.toHtml();
			break;
		default:
			System.out.println("Error");
			break;
		}
		return 0;
	}
	
	/*public void createText(String path) {
		File file = new File(path);
	}*/
}
