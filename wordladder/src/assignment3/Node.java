package assignment3;

import java.util.ArrayList;
import java.util.Set;

public class Node {
//	private boolean visited;
	private boolean gray;
	private boolean black;
	private Node parent;
	private String str;
	private Helpers helper;
	public Node(Node p, String word){
		gray = false;
		black = false;
		parent = p;
		str = word;
		helper = new Helpers();
	}
	
	public Node getParent() {
		// TODO Auto-generated method stub
		return parent;
	}
	
	public boolean isGray(){
		return gray;
	}

	public boolean isBlack(){
		return black;
	}
	
	public String getString(){
		return str;
	}

	public void turnGray(){
		gray = true;
	}
	
	public void turnBlack(){
		black = true;
	}
	
/*	public boolean isNeighbor(String wordA, String wordB) {
		boolean diffByOne = false;
		for (int i = 0; i < wordA.length(); i++) {
			if (wordA.charAt(i) != wordB.charAt(i)) {
				if (diffByOne) {
					return false;
				}
				else {
					diffByOne = true;
				}
			}
		}
		if (diffByOne) {
			return true;
		}
		return false;
	}
	*/

	public ArrayList<Node> getNodeNeighbors(Set<String> dict, ArrayList<String> visited) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
		for (String word : dict) {
			if (helper.isNeighbor(str, word)&&!visited.contains(word)) {
				neighbors.add(new Node(this, word));
	//			dict.remove(word);
				visited.add(word);
			}
		}
		return neighbors;
	}


}
