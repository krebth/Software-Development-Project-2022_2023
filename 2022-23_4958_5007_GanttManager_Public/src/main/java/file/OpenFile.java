package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OpenFile {
	private String fileName;
	private String delimiter;
	
	public OpenFile(String fileName, String delimiter) {
		this.fileName = fileName;
		this.delimiter = delimiter;
	}
	
	public ArrayList<Task> toTaskList() {
		Scanner inputReader = null;
		try
		{
			inputReader = new Scanner(new FileInputStream(fileName));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File "+fileName+" was not found");
			System.out.println("or could not be opened.");
			System.exit(0);
		}
		ArrayList<Task> taskList = new ArrayList<Task>();
		//int lines = 0;
		while(inputReader.hasNextLine()) {
			String line = inputReader.nextLine();
			String[] tasks = line.split(delimiter);
			Task newTask;
			if (tasks.length==3) {
				newTask = new Task(Integer.parseInt(tasks[0]),tasks[1],Integer.parseInt(tasks[2]));
			} else {
				newTask = new Task(Integer.parseInt(tasks[0]),tasks[1],Integer.parseInt(tasks[2]),Integer.parseInt(tasks[3]),Integer.parseInt(tasks[4]),Integer.parseInt(tasks[5]));
			}
			taskList.add(newTask);
			//lines ++;
		}
		inputReader.close();
		return taskList;
	}
}
