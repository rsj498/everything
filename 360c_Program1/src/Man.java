import java.util.ArrayList;

public class Man {
	private int wifeNumber;		//proposed to every woman before this index
	private int numProposed;
	private int id;
	ArrayList<Integer> prefs;
	ArrayList<Integer> proposed; 
	public Man(int i, ArrayList<Integer> p){
		id = i;
		prefs = p;
		wifeNumber = 1;
		numProposed = 0;
		proposed = new ArrayList<Integer>();
	}
	
	public int getID(){
		return id;
	}
	public ArrayList<Integer> getProposedList(){
		return proposed;
	}
	public int getNumProposed(){
		return numProposed;
	}
	
	public int getWifeNumber(){
		return wifeNumber;
	}
	public void addProposed(int x){
		proposed.add(x);
	}
	public void incrementProposed(){
		numProposed++;
	}
	public void incrementWifeNumber(){
		wifeNumber++;
	}
	public ArrayList<Integer> getPref(){
		return prefs;
	}
	
	
}
