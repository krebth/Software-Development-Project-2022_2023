package backend;

import dom2app.SimpleTableModel;
import file.OpenFile;
import file.Task;
import sort.Compare;
import java.util.ArrayList;
import java.util.HashMap;

public class MainControllerFactory implements IMainController {
	private ArrayList<Task> mainList;
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
		mainList = compareTasks.sort();
		ArrayList<String[]> stringTaskList = new ArrayList<String[]>();
		stringTaskList = compareTasks.toStringTaskList(mainList);
		
		
		
		
		
		
		
		
		
		
		
		/*HashMap<Integer,ArrayList<Integer>> posMamaID = new HashMap<Integer,ArrayList<Integer>>();
		//ArrayList<Integer> posMamaID = new ArrayList<Integer>();
		ArrayList<Integer> topLevel = new ArrayList<Integer>();
		ArrayList<Integer> topLevelID = new ArrayList<Integer>();
		//ArrayList<String[]> sortedTaskList = new ArrayList<String[]>();
		
		
		taskList = compare(topLevel,topLevelID,posMamaID,taskList);*/
		
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,stringTaskList);
		return simpleTable;
	}

	@Override
	public SimpleTableModel getTasksByPrefix(String prefix) {
		/*String name = "";
		String prjName = "";
		String[] pColumnNames = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,mainList);*/
		return null;
	}

	@Override
	public SimpleTableModel getTaskById(int id) {
		/*ArrayList<String[]> IDList = new ArrayList<String[]>();
		String name = "";
		String prjName = "";
		String[] pColumnNames = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
		for (int i=0; i<mainList.size(); i++) {
			if (id==Integer.parseInt(mainList.get(i)[0])) {
				IDList.add(mainList.get(i));
				break;
			}
		}
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,IDList);*/
		return null;
	}

	@Override
	public SimpleTableModel getTopLevelTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createReport(String path, ReportType type) {
		// TODO Auto-generated method stub
		return 0;
	}


}
