import java.util.ArrayList;


public class Machine implements Comparable<Machine>{
	
	ArrayList<Integer> tasks;
	int strength, number;
	float workLoad;
	static int sortStyle = 0;
	
	public Machine (int runTime, int machineNumbah){
		tasks = new ArrayList<Integer>();
		strength = runTime;
		workLoad = 0;
		number = machineNumbah;
	}
	
	public void addTask(int taskTime){
		tasks.add(taskTime);
		workLoad += ((float)taskTime)/strength;
	}
	
	public void removeTask(int taskTime){
		tasks.remove(tasks.indexOf(taskTime));
		workLoad -= ((float)taskTime)/strength;
	}
	
	public float testLoad(int taskTime){
		return workLoad + ((float)taskTime)/strength;
	}
	public float testRemoveLoad(int taskTime){
		return workLoad - ((float)taskTime)/strength;
	}
	public float getWorkLoad(){
		return workLoad;
	}
	public int getNumber(){
		return number;
	}public int getStrength(){
		return strength;
	}
	@Override
	public int compareTo(Machine arg0) {
		switch(sortStyle){
		
			case 0:
				return (arg0.getStrength() - this.strength);
			case 1:
				return (int) (this.workLoad - arg0.getWorkLoad());
			case 2:
				return (this.number - arg0.getNumber());
			
			default:
				return 0;
		}
	}
	
	public String toString(){
		String output = new String();
		output = tasks + " total time: " + workLoad;
		return output;
	}
	
	public boolean delegateTasks(Machine other){
		//iterate through tasks
		int toSwap = -1;
		float bestSavings = Float.MIN_VALUE;
		for (int currentTask : tasks){
			float potential = other.testLoad(currentTask);
			
			
		}
		
		return false;
	}
	
}
