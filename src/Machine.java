import java.util.ArrayList;


public class Machine implements Comparable<Machine>{
	
	ArrayList<Integer> tasks;
	int strength, number;
	float workLoad;
	static int sortStyle = 0;
	
	public Machine (int runTime, int newNumber){
		tasks = new ArrayList<Integer>();
		strength = runTime;
		workLoad = 0;
		number = newNumber;
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
		return workLoad + (((float)taskTime)/strength);
	}
	public float testRemoveLoad(int taskTime){
		return workLoad - (((float)taskTime)/strength);
	}
	public float getWorkLoad(){
		return workLoad;
	}
	public int getNumber(){
		return number;
	}public int getStrength(){
		return strength;
	}public ArrayList<Integer> getTasks(){
		return tasks;
	}
	@Override
	public int compareTo(Machine arg0) {
		switch(sortStyle){
		
			case 0:
				return (arg0.getStrength() - this.strength);
			case 1:
				
				if (arg0.getWorkLoad() > this.workLoad) return 1;
				if (arg0.getWorkLoad() < this.workLoad) return -11;
				else return 0;
				//this doesn't work and idk why
				
				//return (int) (arg0.getWorkLoad() -this.workLoad);
				
			case 2:
				return (this.number - arg0.getNumber());
			
			default:
				return 0;
		}
	}
	
	public String toString(){
		String output = new String();
		output = tasks + "Strength :" + strength +  " total time: " + workLoad;
		return output;
	}
	
	public boolean tradeTasks(Machine other){
		//iterate through tasks
		float bestResult = workLoad;
		int thisSwap = -1, otherSwap = -1;
		
		for (int currentTask : tasks){
		    //float largeValue = testRemoveLoad(currentTask);
			for (int otherTask : other.getTasks()){
				int taskDifference = currentTask - otherTask;
				
				float added = other.testLoad(taskDifference);
				float removed = testRemoveLoad(taskDifference);
				
				if ((added < bestResult)&&(removed < bestResult)){
					
					bestResult = (added > removed)? added : removed; 
					thisSwap = currentTask;
					otherSwap = otherTask;
				}
			}
			
		}
		
		if (thisSwap != otherSwap){
			//System.out.print(thisSwap + ">" + otherSwap + " ");
			
			other.addTask(thisSwap);
			other.removeTask(otherSwap);

			removeTask(thisSwap);
			addTask(otherSwap);
			return true;
		}
		
		return false;
	}

	
}
