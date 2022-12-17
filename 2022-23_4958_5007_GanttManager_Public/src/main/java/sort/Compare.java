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
			double totalCost = 0;
			boolean hasSubtask = false;
			ArrayList<Task> subtaskList = new ArrayList<Task>();
			for (Task j:taskList) {
				if (i.getID()==j.getMamaID()) {
					hasSubtask = true;
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
			if (hasSubtask) {
				i.setStart(minStart);
				i.setEnd(maxEnd);
				i.setCost(totalCost);
			}
			subtasks.add(subtaskList);
		}
		return topLevelTasks;
	}
	
	public ArrayList<Task> sortTasks(ArrayList<Task> tasks) {
		ArrayList<Task> sortedTasks = new ArrayList<Task>();
		while (!tasks.isEmpty()) {
			ArrayList<Task> sameStart = new ArrayList<Task>();
			Task minTask = tasks.get(0);
			sameStart.add(minTask);
			for (Task i: tasks) {
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
				sortedTasks.add(minTask);
				tasks.remove(minTask);
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
					sortedTasks.add(minTaskByID);
					sameStart.remove(minTaskByID);
					tasks.remove(minTaskByID);
				}
			}
		}
		return sortedTasks;
	}

	public ArrayList<Task> sortSubtasks(ArrayList<Task> sortedTopLevelTasks,ArrayList<Task> taskList) {
		ArrayList<Task> sortedList = new ArrayList<Task>();
		for(Task i:sortedTopLevelTasks) {
			ArrayList<Task> currentSubtask = new ArrayList<Task>();
			int x = 0;
			if (subtasks.get(0).size()!=0) {
				for (ArrayList<Task> j:subtasks) {
					if (i.getID()==((j.get(0)).getMamaID())) {
						currentSubtask = j;
						break;
					}
					x ++;
				}
			}
			currentSubtask = sortTasks(currentSubtask);
			sortedList.add(i);
			for (Task q:currentSubtask) {
				sortedList.add(q);
			}
			subtasks.remove(x);
		}
		return sortedList;
	}
	
	public ArrayList<Task> sort() {
		ArrayList<Task> sortedTasks = new ArrayList<Task>();
		ArrayList<Task> sortedTopLevelTasks = new ArrayList<Task>();
		ArrayList<Task> topLevelTasks = getTopLevelTasks(taskList);
		sortedTopLevelTasks = sortTasks(topLevelTasks);
		sortedTasks = sortSubtasks(sortedTopLevelTasks,taskList);
		return sortedTasks;
	}
	
	public ArrayList<String[]> toArrayStringTaskList(ArrayList<Task> taskList) {

		ArrayList<String[]> arrayStringTaskList = new ArrayList<String[]>();
		for (Task i:taskList) {
			arrayStringTaskList.add(i.toArrayString());
		}
		return arrayStringTaskList;
	}
	
}
