package file;

public class Task {
	private int id;
	private String name;
	private int mamaID;
	private int start;
	private int end;
	private int cost;
	
	public Task(int id, String name, int mamaID) {
		this.id = id;
		this.name = name;
		this.mamaID = mamaID;
		this.start = 0;
		this.end = 0;
		this.cost = 0;
	}
	
	public Task(int id, String name, int mamaID, int start, int end, int cost) {
		this.id = id;
		this.name = name;
		this.mamaID = mamaID;
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMamaID() {
		return mamaID;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String toString(int num) {
		return ""+num;
	}
	
	public String[] toArrayString() {
		String[] array = {toString(id),name,toString(mamaID),toString(start),toString(end),toString(cost)};
		return array;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
}
