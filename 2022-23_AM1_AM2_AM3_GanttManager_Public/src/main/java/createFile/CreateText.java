package createFile;

import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;

import file.Task;

public class CreateText {
	private String path;
	private ArrayList<Task> sortedList;
	private String[] pColumnNames;
	private int lines;
	
	public CreateText (String path, ArrayList<Task> sortedList, String[] pColumnNames) {
		this.path = path;
		this.sortedList = sortedList;
		this.pColumnNames = pColumnNames;
		this.lines = 0;
	}
	
	public void toText() throws IOException {
		FileOutputStream outputStream = new FileOutputStream(path);
		PrintWriter outputWriter = new PrintWriter(outputStream);
		for (String i:pColumnNames) {
			if (i.equals(pColumnNames[pColumnNames.length-1])) {
				outputWriter.println(i);
				break;
			}
			outputWriter.print(i+"\t");
		}
		for (Task i:sortedList) {
			outputWriter.println(i.getID()+"\t"+i.getName()+"\t"+i.getMamaID()+"\t"+i.getStart()+"\t"+i.getEnd()+"\t"+i.getCost());
		}
		outputWriter.close();
		
	}

}
