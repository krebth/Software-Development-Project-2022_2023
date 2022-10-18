package createFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import file.Task;

public class CreateHtml {
	private String path;
	private ArrayList<Task> sortedList;
	private String[] pColumnNames;
	private int lines;
	
	public CreateHtml (String path, ArrayList<Task> sortedList, String[] pColumnNames) {
		this.path = path;
		this.sortedList = sortedList;
		this.pColumnNames = pColumnNames;
		this.lines = 0;
	}
	
	public void toHtml() throws IOException {
		FileOutputStream outputStream = new FileOutputStream(path);
		PrintWriter outputWriter = new PrintWriter(outputStream);
		outputWriter.println("<!doctype html>\n<html>\n<head>\n<meta http-equiv=\"Content-Type\" content\"text/html; charset=windows-1253\">\n<title>Gantt Project Data</title>\n</head>\n<body>\n\n<table>\n<tr>");
		for (String i:pColumnNames) {
			if (i.equals(pColumnNames[pColumnNames.length-1])) {
				outputWriter.println("<td>"+i+"</td>\t</tr>\n");
				break;
			}
			outputWriter.print("<td>"+i+"</td>\t");
		}
		for (Task i:sortedList) {
			outputWriter.println("<tr>\n<td>"+i.getID()+"</td>\t<td>"+i.getName()+"</td>\t<td>"+i.getMamaID()+"</td>\t<td>"+i.getStart()+"</td>\t<td>"+i.getEnd()+"</td>\t<td>"+i.getCost()+"</td>\t</tr>\n");
		}
		outputWriter.println("</table></body>\n</html>");
		outputWriter.close();
		
	}
}
