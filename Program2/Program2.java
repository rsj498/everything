
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Program2 {
	private static List<Node> nodes = new ArrayList<Node>();
	private static ArrayList<String> traces = new ArrayList<String>();
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub	
		Scanner file = new Scanner(new File(args[0]));		
		makeGraph(file);
		Node startNode = nodes.get(file.nextInt());
		Node endNode = nodes.get(file.nextInt());
		int startTime = file.nextInt();
		int endTime = file.nextInt();
		Node n = BFS(startNode,endNode,startTime,endTime);
		if(n == null){
			System.out.println("0");
		}
		else{
			ArrayList<Node> path = getPath(n);
			ArrayList<String> tracePath = getTracePath(path, startTime, endTime);
			System.out.println(tracePath.size());
			for(int i = 0; i < tracePath.size(); i++){
				System.out.println(tracePath.get(i));
			}
			
		}
		
	}

	 private static void makeGraph(Scanner file){
		 int n = file.nextInt();
			int m = file.nextInt();
			file.nextLine();
			for(int i = 0; i< n; i++){
				nodes.add(new Node(i));
			}
			for(int i = 0; i < m; i++){
				String s = file.nextLine();
				traces.add(s);
				String[] nums = s.split(" ");
				int a = Integer.parseInt(nums[0]);
				int b = Integer.parseInt(nums[1]);
				int t = Integer.parseInt(nums[2]);
				nodes.get(a).getAdjacency().put(nodes.get(b), t);
				nodes.get(b).getAdjacency().put(nodes.get(a), t);
			}
	 }
	 private static Node BFS(Node startNode, Node endNode,int startTime,int endTime){
		 Queue<Node> q = new LinkedList<>();
		 q.add(startNode);
		 startNode.setVisited();
		 startNode.setTimeVisited(startTime);
		 while(!q.isEmpty()){
			 Node head = q.remove();		
			 HashMap<Node,Integer> h = head.getAdjacency();
			 int currTime = head.getTimeVisited();
			 for(Node n: h.keySet()) {
				 if(h.get(n) >= currTime && h.get(n) <= endTime) {
					 if(!n.isVisited()){		 
						 n.setVisited();
						 n.setTimeVisited(h.get(n));
						 n.setParent(head);
						 q.add(n);
						 if(n.getValue()==endNode.getValue()){				 
							 return n;
						 }
					 }
				}
			 }
		 }
		 return null;
	 }
	 private static ArrayList<Node> getPath(Node n){
		ArrayList<Node> path = new ArrayList<Node>();
		 while(n!=null){
			 path.add(0, n);
	//		 System.out.print(n.getValue() + " " + n.getTimeVisited() + " ok");

			 n = n.getParent();
		 }
		 return path;
	 }
	 private static ArrayList<String> getTracePath(ArrayList<Node> path, int start, int end){
		 ArrayList<String> t = new ArrayList<String>();
		 for(int k = 0; k<path.size()-1;k++){
			 int a = path.get(k).getValue();
			 int b = path.get(k+1).getValue();
			 for(int i = 0; i < traces.size();i++){
				 String sub = traces.get(i).substring(0, 3);
				 if(sub.contains(Integer.toString(a)) && sub.contains(Integer.toString(b))){
					 int time = Integer.parseInt(Character.toString(traces.get(i).charAt(4)));
					 if( time<= end && time >= start )
					 t.add(traces.get(i));
				 }
				 
			 }		 
		 }
		 return t;
	 }
}
 