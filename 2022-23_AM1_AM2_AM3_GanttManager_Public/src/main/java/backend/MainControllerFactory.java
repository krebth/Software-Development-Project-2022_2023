package backend;

import dom2app.SimpleTableModel;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainControllerFactory implements IMainController {
	private ArrayList<String[]> mainList;
	
	public IMainController createMainController() {
		return new MainControllerFactory();
	}

	@Override
	public SimpleTableModel load(String fileName, String delimiter) {
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
		String name = "";
		String prjName = "";
		String[] pColumnNames = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
		ArrayList<String[]> taskList = new ArrayList<String[]>();
		HashMap<Integer,ArrayList<Integer>> posMamaID = new HashMap<Integer,ArrayList<Integer>>();
		//ArrayList<Integer> posMamaID = new ArrayList<Integer>();
		ArrayList<Integer> topLevel = new ArrayList<Integer>();
		ArrayList<Integer> topLevelID = new ArrayList<Integer>();
		//ArrayList<String[]> sortedTaskList = new ArrayList<String[]>();
		int lines = 0;
		while(inputReader.hasNextLine()) {
			String line = inputReader.nextLine();
			String[] tasks = line.split(delimiter);
			taskList.add(tasks);
			lines ++;
		}
		inputReader.close();
		for (int i=0; i<lines; i++) {
			int mamaID = Integer.parseInt(taskList.get(i)[2]);
			if (mamaID != 0) {
				if (!posMamaID.containsKey(mamaID)) {
					posMamaID.put(mamaID,new ArrayList<Integer>());
					(posMamaID.get(mamaID)).add(i);
				} else {
					(posMamaID.get(mamaID)).add(i);
				}
			} else {
				topLevel.add(i);
				topLevelID.add(Integer.parseInt(taskList.get(i)[0]));
			}
		}
		taskList = compare(topLevel,topLevelID,posMamaID,taskList);
		mainList = taskList;
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,taskList);
		return simpleTable;
	}

	@Override
	public SimpleTableModel getTasksByPrefix(String prefix) {
		String name = "";
		String prjName = "";
		String[] pColumnNames = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,mainList);
		return simpleTable;
	}

	@Override
	public SimpleTableModel getTaskById(int id) {
		ArrayList<String[]> IDList = new ArrayList<String[]>();
		String name = "";
		String prjName = "";
		String[] pColumnNames = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
		for (int i=0; i<mainList.size(); i++) {
			if (id==Integer.parseInt(mainList.get(i)[0])) {
				IDList.add(mainList.get(i));
				break;
			}
		}
		SimpleTableModel simpleTable = new SimpleTableModel(name,prjName,pColumnNames,IDList);
		return simpleTable;
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
	
	public ArrayList<String[]> compare(ArrayList<Integer> topLevel,ArrayList<Integer> topLevelID,HashMap<Integer,ArrayList<Integer>> posMamaID, ArrayList<String[]> taskList) {
		ArrayList<Integer> posMinStart = new ArrayList<Integer>();
		ArrayList<String[]> topFinal = new ArrayList<String[]>();
		for (int i = 0; i<topLevel.size(); i++) {
			int topID = Integer.parseInt(taskList.get(topLevel.get(i))[0]);
			ArrayList<Integer> subtaskID = posMamaID.get(topID);
			int minStart = Integer.parseInt(taskList.get(subtaskID.get(0))[3]);
			for (int j = 1; j<subtaskID.size(); j++) {
				if (minStart>Integer.parseInt(taskList.get(subtaskID.get(j))[3])) {
					minStart = Integer.parseInt(taskList.get(subtaskID.get(j))[3]);
				}
			}
			posMinStart.add(minStart);
		}
		while (posMinStart.size()!=0) {
			int posMin = 0;
			for (int i=posMin+1; i<posMinStart.size(); i++) {
				if (posMinStart.get(posMin)==posMinStart.get(i)) {
					if (topLevelID.get(posMin)>topLevelID.get(i)) {
						int id = topLevelID.get(posMin); 
						topLevelID.set(posMin,topLevelID.get(i)); 
						topLevelID.set(i,id);
						int pos = topLevel.get(posMin); 
						topLevel.set(posMin,topLevel.get(i)); 
						topLevel.set(i,pos);
					}else{
						continue;
					}
				}else if (posMinStart.get(posMin)>posMinStart.get(i)) {
					posMin = i;
				}else{
					continue;
				}
			}
			topFinal.add(taskList.get(topLevel.get(posMin)));
			topFinal = compareSubtasks(posMamaID.get(topLevelID.get(posMin)),taskList,topFinal);
			topLevel.remove(posMin);
			topLevelID.remove(posMin);
			posMinStart.remove(posMin);
		}
		return topFinal;
	}
	
	public ArrayList<String[]> compareSubtasks(ArrayList<Integer> subtasks, ArrayList<String[]> taskList, ArrayList<String[]> topFinal) {
	    ArrayList<Integer> posMinStart = new ArrayList<Integer>();
	    ArrayList<Integer> subtasksID = new ArrayList<Integer>();
	    for (int i = 0; i<subtasks.size(); i++) {
	        subtasksID.add(Integer.parseInt(taskList.get(subtasks.get(i))[0]));
	        posMinStart.add(Integer.parseInt(taskList.get(subtasks.get(i))[3]));
	    }
	    while (posMinStart.size()!=0) {
	        int posMin = 0;
	        for (int i=posMin+1; i<posMinStart.size(); i++) {
	            if (posMinStart.get(posMin)==posMinStart.get(i)) {
	                if (subtasksID.get(posMin)>subtasksID.get(i)) {
	                    int id = subtasksID.get(posMin); 
	                    subtasksID.set(posMin,subtasksID.get(i)); 
	                    subtasksID.set(i,id);
	                    int pos = subtasks.get(posMin); 
	                    subtasks.set(posMin,subtasks.get(i)); 
	                    subtasks.set(i,pos);
	                }else{
	                    continue;
	                }
	            }else if (posMinStart.get(posMin)>posMinStart.get(i)) {
	                posMin = i;
	            }else{
	                continue;
	            }
	        }
	        topFinal.add(taskList.get(subtasks.get(posMin)));
	        subtasks.remove(posMin);
	        subtasksID.remove(posMin);
	        posMinStart.remove(posMin);
	    }
	    return topFinal;
	}

}
