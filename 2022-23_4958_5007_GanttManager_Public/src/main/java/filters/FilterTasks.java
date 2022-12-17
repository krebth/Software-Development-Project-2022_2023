package filters;
import file.Task;
import java.util.ArrayList;

public class FilterTasks {
	private ArrayList<Task> sortedTasks;
	
	public FilterTasks(ArrayList<Task> sortedTasks) {
		this.sortedTasks = sortedTasks;
	}
	
	public ArrayList<String[]> toArrayStringTaskList(ArrayList<Task> taskList) {

		ArrayList<String[]> arrayStringTaskList = new ArrayList<String[]>();
		for (Task i:taskList) {
			arrayStringTaskList.add(i.toArrayString());
		}
		return arrayStringTaskList;
	}
	
	public ArrayList<Task> getByID(int id) {
		ArrayList<Task> IDList = new ArrayList<Task>();
		for (Task i:sortedTasks) {
			if (i.getID()==id) {
				IDList.add(i);
				break;
			}
		}
		return IDList;
	}
	
	public ArrayList<Task> getTopLevel() {
		ArrayList<Task> topLevel = new ArrayList<Task>();
		for (Task i:sortedTasks) {
			if (i.getMamaID()==0) {
				topLevel.add(i);
			}
		}
		return topLevel;
	}
	
	public ArrayList<Task> getByPrefix(String prefix) {
		ArrayList<Task> samePrefix = new ArrayList<Task>();
		String[] prefixArray = prefix.split("");
		for (Task i:sortedTasks) {
			String[] splitedTaskNames = i.getName().split("");
			boolean same = false;
			for (int j=0; j<prefixArray.length; j++) {
				if (!prefixArray[j].equals(splitedTaskNames[j])) {
					break;
				} else if(j==prefixArray.length-1) {
					if (prefixArray[j].equals(splitedTaskNames[j])) {
						same = true;
					}
				}
			}
			if (same) {
				samePrefix.add(i);
			}
		}
		return samePrefix;
	}
}
