import java.util.ArrayList;


public class Machine implements Comparable<Machine>{
	
	ArrayList<Integer> tasks;
	int powa, number;
	float workLoad;
	static boolean outputSort = false;
	
	public Machine (int runTime, int machineNumbah){
		tasks = new ArrayList<Integer>();
		powa = runTime;
		workLoad = 0;
		number = machineNumbah;
	}
	
	public void addTask(int taskTime){
		tasks.add(taskTime);
		workLoad += ((float)taskTime)/powa;
	}
	
	public float testLoad(int taskTime){
		return workLoad + ((float)taskTime)/powa;
	}
	public float getWorkLoad(){
		return workLoad;
	}
	public int getNumber(){
		return number;
	}public int getPowa(){
		return powa;
	}
	@Override
	public int compareTo(Machine arg0) {
		if (outputSort){
			return (this.number - arg0.getNumber());
		}else{
			//float difference = this.workLoad - arg0.getWorkLoad();
			//if (difference == 0){
			
			//This is purely sorted by power now
			return (arg0.getPowa() - this.powa);
			//}else{
				//return (int) (difference);
			//}
		}
	}
	
	public String toString(){
		String output = new String();
		output = tasks + " total time: " + workLoad;
		return output;
	}
	
	
}
