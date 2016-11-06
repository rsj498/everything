import java.util.HashMap;
import java.util.Set;
public class Node {
	private int value;
	boolean visited;
	private Node parent;
	private int timeVisited;
	HashMap<Node, Integer> adjacent;
	public Node(int value){
		this.value = value;
		visited = false;
		parent = null;
		adjacent = new HashMap<Node, Integer>();
		timeVisited = -1;
	}
	

	public HashMap<Node,Integer> getAdjacency() {
		return adjacent;
	}
	
	public boolean isVisited(){
		return visited;
	}
	public void setVisited(){
		visited = true;
	}
	public void setTimeVisited(int t){
		timeVisited = t;
	}
	public int getValue(){
		return value;
	}
	public Node getParent(){
		return parent;
	}
	public int getTimeVisited(){
		return timeVisited;
	}
	public void setParent(Node n){
		parent = n;
	}
}