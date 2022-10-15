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