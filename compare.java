import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public ArrayList<String[]> compare(ArrayList<Integer> topLevel,ArrayList<Integer> topLevelID,HashMap<Integer,ArrayList<Integer>> posMamaID, ArrayList<String[]> taskList) {
	ArrayList<Integer> posMinStart = new ArrayList<Integer>();
	ArrayList<String[]> topFinal = new ArrayList<String[]>();
	for (int i = 0; i<=topLevel.size(); i++) {
		int topID = Integer.parseInt(taskList.get(i)[0]);
		ArrayList<Integer> subtaskID = posMamaID.get(topID);
		int minStart = taskList.get(subtaskID.get(0))[3];
		for (int j = 1; j<=subtaskID.size(); j++) {
			if (minStart>taskList.get(subtaskID.get(j))[3]) {
				minStart = taskList.get(subtaskID.get(j))[3];
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
					toplevelID.set(i,id);
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
		topFinal.add(taskList.get(topLevel.get(posMin));
		topLevel.remove(posMin);
		topLevelID.remove(posMin);
		posMinStart.remove(posMin);
	}
	return topFinal;
}
