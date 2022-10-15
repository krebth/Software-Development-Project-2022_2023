package sort;

import file.Task;
import java.util.ArrayList;

public class Compare {
	private ArrayList<Task> taskList;
	private ArrayList<ArrayList<Task>> subtasks; 
	
	public Compare(ArrayList<Task> taskList) {
		this.taskList = taskList;
		subtasks = new ArrayList<ArrayList<Task>>();
	}
	
	public ArrayList<Task> getTopLevelTasks(ArrayList<Task> taskList) {
		ArrayList<Task> topLevelTasks = new ArrayList<Task>();
		for (Task i:taskList) {
			if(i.getMamaID()==0) {
				topLevelTasks.add(i);
			}
		}
		for(Task i:topLevelTasks) {
			int minStart = -1;
			int maxEnd = 0;
			int totalCost = 0;
			ArrayList<Task> subtaskList = new ArrayList<Task>();
			for (Task j:taskList) {
				if (i.getID()==j.getMamaID()) {
					if (minStart==-1) {
						minStart = j.getStart();
					}
					if (j.getStart()<minStart) {
						minStart = j.getStart();
					}
					if (j.getEnd()>maxEnd) {
						maxEnd = j.getEnd();
					}
					totalCost += j.getCost();
					subtaskList.add(j);
				}
			}
			i.setStart(minStart);
			i.setEnd(maxEnd);
			i.setCost(totalCost);
			subtasks.add(subtaskList);
		}
		return topLevelTasks;
	}
	
	public ArrayList<Task> sortTopLevelTasks(ArrayList<Task> topLevelTasks) {

		ArrayList<Task> sortedTopLevelTasks = new ArrayList<Task>();
		while (!topLevelTasks.isEmpty()) {
			ArrayList<Task> sameStart = new ArrayList<Task>();
			Task minTask = topLevelTasks.get(0);
			sameStart.add(minTask);
			for (Task i: topLevelTasks) {
				if (minTask.getID()!=i.getID()) {
					if(minTask.getStart()>i.getStart()) {
						minTask = i;
						sameStart = new ArrayList<Task>();
						sameStart.add(i);
					} else if(minTask.getStart()==i.getStart()) {
						sameStart.add(i);
					}
				}
			}
			if (sameStart.size()==1) {
				sortedTopLevelTasks.add(minTask);
				topLevelTasks.remove(minTask);
			} else {
				while (!sameStart.isEmpty()) {
					Task minTaskByID = sameStart.get(0);
					for (Task i:sameStart) {
						if (minTask.getID()!=i.getID()) {
							if(minTask.getID()>i.getID()) {
								minTaskByID = i;
							}
						}
					}
					sortedTopLevelTasks.add(minTaskByID);
					sameStart.remove(minTaskByID);
					topLevelTasks.remove(minTaskByID);
				}
			}
		}
		return sortedTopLevelTasks;
	}

	public ArrayList<Task> getSubtasks(ArrayList<Task> sortedTasks,ArrayList<Task> taskList) {
		ArrayList<Task> finalList = new ArrayList<Task>();
		for(Task i:sortedTasks) {
			ArrayList<Task> currentSubtask = new ArrayList<Task>();
			int x = 0;
			for (ArrayList<Task> j:subtasks) {
				if (i.getID()==((j.get(0)).getMamaID())) {
					currentSubtask = j;
					break;
				}
				x ++;
			}
			currentSubtask = sortTopLevelTasks(currentSubtask);
			finalList.add(i);
			for (Task q:currentSubtask) {
				finalList.add(q);
			}
			subtasks.remove(x);
		}
		return finalList;
	}
	
	public ArrayList<Task> sort() {
		ArrayList<Task> sortedTasks = new ArrayList<Task>();
		ArrayList<Task> topLevelTasks = getTopLevelTasks(taskList);
		sortedTasks = sortTopLevelTasks(topLevelTasks);
		sortedTasks = getSubtasks(sortedTasks,taskList);
		return sortedTasks;
	}
	
	public ArrayList<String[]> toStringTaskList(ArrayList<Task> taskList) {
		ArrayList<String[]> stringTaskList = new ArrayList<String[]>();
		for (Task i:taskList) {
			stringTaskList.add(i.toArrayString());
		}
		return stringTaskList;
	}
	
}
