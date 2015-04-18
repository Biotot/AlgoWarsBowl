import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class AlgoWarsMain {

	ArrayList<Integer> tasks;
	ArrayList<Machine> machines;
	public float min, max, pDifference;
	
	
	public static void main(String [] args){
		inputFile();
		//randomSim();
	}
	
	public static void randomSim(){
		int sims = 5000;
		float averageDifference = 0;
		float minDifference = Float.MAX_VALUE;
		float maxDifference = Float.MIN_VALUE;
		AlgoWarsMain biggest = null;
		for (int x = 0; x<sims; x++){
			AlgoWarsMain simulation = new AlgoWarsMain(true);
			averageDifference += simulation.pDifference;
			if (simulation.pDifference<minDifference) minDifference = simulation.pDifference;
			if (simulation.pDifference>maxDifference){
				maxDifference = simulation.pDifference;
				biggest = simulation;
			}
		}
		averageDifference /= sims;
		System.out.println("Average pDifference: " + averageDifference + "\tOver "+sims+" simulations");
		System.out.println("Max pDifference: " + maxDifference + "\t Min pDifference " + minDifference);
		if (biggest != null)
			biggest.output(true);
		
	}
	
	public static void inputFile(){
		AlgoWarsMain tommy = new AlgoWarsMain(false);
		try {
			tommy.loadFile();
			tommy.allocateTasks();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AlgoWarsMain(boolean randomInput){
		
		if (randomInput){
			generateRandomInput();
		}else{
			try {
				loadFile();
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		allocateTasks();
		output(!randomInput);
	}
	
	
	public void allocateTasks(){
		
		//Set up the initial stacks
		Collections.sort(tasks);
		Collections.sort(machines);
		while (!tasks.isEmpty()){
			Machine best = machines.get(0);
			int task = tasks.get(tasks.size()-1);
			for (Machine macheen : machines){
				if (macheen.testLoad(task) < best.testLoad(task)){
					best = macheen;
				}
			}
			tasks.remove(tasks.size()-1);
			best.addTask(task);
		}
		
		//refine the results
		/*
		 * this currently breaks on random simulated inputs. Trying to figure it out
		 */
		Machine.sortStyle = 1;//sort by work load
		boolean tradeFound = true;
		while (tradeFound){
			Collections.sort(machines);
			tradeFound = false;

			//System.out.println(machines.get(0));
			for (int y = machines.size()-1; y>machines.size()/2; y--){
				if (machines.get(0).tradeTasks(machines.get(y))){
					tradeFound = true;
					break;
				}
				
			}
		}
		
		//Machine.sortStyle = 2;//sort by machine number
		Collections.sort(machines);
	}
	
	
	public void output(boolean verbose){
		min = Float.MAX_VALUE;
		max = Float.MIN_VALUE;
		for (Machine macheens : machines){
			float load = macheens.getWorkLoad();
			if (load<min) min = load;
			if (load>max) max = load;
			if (verbose)
				System.out.println(macheens);
		}
		System.out.println("Min: " + min + " \t\tMax: " + max);
		pDifference = ((max-min)/max);
		System.out.println("Difference: " + (max-min) + " \tPDifference: " + pDifference);
	}
	
	public void generateRandomInput(){

		Random rand = new Random();
		int taskCount = 1000;//rand.nextInt(949)+50;
		int machineCount = 50;//rand.nextInt(49)+1;
		if (machineCount>taskCount){
			machineCount = taskCount;
		}
		tasks = new ArrayList<Integer>();
		machines = new ArrayList<Machine>();
		
		for (int x = 0; x<taskCount; x++){
			tasks.add(rand.nextInt(9999)+1);
		}
		for (int x = 0; x<machineCount; x++){
			machines.add(new Machine(rand.nextInt(19)+1, x));
		}
	
	}
	
	public void loadFile() throws FileNotFoundException{
		tasks = new ArrayList<Integer>();
		machines = new ArrayList<Machine>();
		Scanner scan = new Scanner(new File("src/defaultInput.txt"));
		//throw out the two lines
		scan.nextLine();
		scan.nextLine();
		Scanner lineScanner = new Scanner(scan.nextLine());
		while (lineScanner.hasNextInt()){
			tasks.add(lineScanner.nextInt());
		}
		lineScanner.close();
		lineScanner = new Scanner(scan.nextLine());
		int machineCount = 0;
		while (lineScanner.hasNextInt()){
			machines.add(new Machine(lineScanner.nextInt(), machineCount));
			machineCount++;
		}
		lineScanner.close();
		scan.close();
	}
}
