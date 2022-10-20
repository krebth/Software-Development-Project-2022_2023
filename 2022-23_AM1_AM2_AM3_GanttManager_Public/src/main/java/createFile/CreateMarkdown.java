package createFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import file.Task;

public class CreateMarkdown {
	private String path;
	private ArrayList<Task> sortedList;
	private String[] pColumnNames;
	private int lines;
	
	public CreateMarkdown (String path, ArrayList<Task> sortedList, String[] pColumnNames) {
		this.path = path;
		this.sortedList = sortedList;
		this.pColumnNames = pColumnNames;
		this.lines = 0;
	}
	
	public int toMarkdown() throws IOException {
		FileOutputStream outputStream = new FileOutputStream(path);
		PrintWriter outputWriter = new PrintWriter(outputStream);
		for (String i:pColumnNames) {
			if (i.equals(pColumnNames[pColumnNames.length-1])) {
				outputWriter.println("*"+i+"*");
				break;
			}
			outputWriter.print("*"+i+"*\t");
		}
		for (Task i:sortedList) {
			if (i.getMamaID()==0) {
				outputWriter.println("**"+i.getID()+"**\t"+"**"+i.getName()+"**\t"+"**"+i.getMamaID()+"**\t"+"**"+i.getStart()+"**\t"+"**"+i.getEnd()+"**\t"+"**"+i.getCost()+"**");
			} else {
				outputWriter.println(i.getID()+"\t"+i.getName()+"\t"+i.getMamaID()+"\t"+i.getStart()+"\t"+i.getEnd()+"\t"+i.getCost());
			}
			lines ++;
		}
		outputWriter.close();
		return lines;
	}
}
