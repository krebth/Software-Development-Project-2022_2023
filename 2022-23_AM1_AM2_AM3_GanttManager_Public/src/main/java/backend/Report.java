package backend;

import java.io.IOException;
import java.util.ArrayList;
import createFile.CreateText;
import createFile.CreateHtml;
import createFile.CreateMarkdown;
import file.Task;

public class Report {
	private ReportType type;
	private String path;
	private ArrayList<Task> sortedList;
	private String[] pColumnNames;
	private int lines;
	
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
			lines = text.toText();
			break;
		case MD:
			CreateMarkdown markdown = new CreateMarkdown(path,sortedList,pColumnNames);
			lines = markdown.toMarkdown();
			break;
		case HTML:
			CreateHtml html = new CreateHtml(path,sortedList,pColumnNames);
			lines = html.toHtml();
			break;
		default:
			System.out.println("Error");
			return -1;
		}
		return lines;
	}
}
