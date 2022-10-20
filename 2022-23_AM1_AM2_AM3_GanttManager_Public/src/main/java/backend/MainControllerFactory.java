package backend;

import dom2app.SimpleTableModel;
import file.OpenFile;
import file.Task;
import filters.FilterTasks;
import sort.Compare;

import java.io.IOException;
import java.util.ArrayList;

public class MainControllerFactory implements IMainController {
	private ArrayList<Task> sortedList;
	private String name = "";
	private String prjName = "";
	private String[] pColumnNames = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
	
	public IMainController createMainController() {
		return new MainControllerFactory();
	}

	@Override
	public SimpleTableModel load(String fileName, String delimiter) {
		OpenFile file = new OpenFile(fileName, delimiter);
		Compare compareTasks = new Compare(file.toTaskList());
		sortedList = compareTasks.sort();
		ArrayList<String[]> stringArrayTaskList = new ArrayList<String[]>();
		stringArrayTaskList = compareTasks.toArrayStringTaskList(sortedList);
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,stringArrayTaskList);
		return simpleTable;
	}

	@Override
	public SimpleTableModel getTasksByPrefix(String prefix) {
		FilterTasks filter = new FilterTasks(sortedList);
		ArrayList<Task> samePrefix = new ArrayList<Task>();
		ArrayList<String[]> stringArrayTaskList = new ArrayList<String[]>();
		samePrefix = filter.getByPrefix(prefix);
		stringArrayTaskList = filter.toArrayStringTaskList(samePrefix);
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,stringArrayTaskList);
		return simpleTable;
	}

	@Override
	public SimpleTableModel getTaskById(int id) {
		FilterTasks filter = new FilterTasks(sortedList);
		ArrayList<Task> IDList = new ArrayList<Task>();
		ArrayList<String[]> stringArrayTaskList = new ArrayList<String[]>();
		IDList = filter.getByID(id);
		stringArrayTaskList = filter.toArrayStringTaskList(IDList);
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,stringArrayTaskList);
		return simpleTable;
	}

	@Override
	public SimpleTableModel getTopLevelTasks() {
		FilterTasks filter = new FilterTasks(sortedList);
		ArrayList<Task> topLevelList = new ArrayList<Task>();
		ArrayList<String[]> stringArrayTaskList = new ArrayList<String[]>();
		topLevelList = filter.getTopLevel();
		stringArrayTaskList = filter.toArrayStringTaskList(topLevelList);
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,stringArrayTaskList);
		return simpleTable;
	}

	@Override
	public int createReport(String path, ReportType type) {
		Report test = new Report(path,type,sortedList,pColumnNames);
		try {
			return test.makeFile();
		} catch (IOException e) {
			System.out.println("Error opening the file");
			return -1;
		}
	}


}
