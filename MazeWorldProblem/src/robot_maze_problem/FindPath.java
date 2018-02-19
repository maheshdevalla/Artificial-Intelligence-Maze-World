/*
 * Author: Mahesh Devalla
 * Artificial Intelligence
 * Assignment: Robot Maze Problem
 * Credits: Dr. Devin Balcom
 * A little code taken with the concept from previous assignment of Missionaries and Cannibals
 */
package robot_maze_problem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public abstract class FindPath {
	public int nodesExplored;
	public int maxMemory;
	public SearchNode startNode;
	// used comparable interface to make use compraeTo() method for comparison objects
	public interface SearchNode extends Comparable<SearchNode> {
		public ArrayList<SearchNode> getSuccessors();
		public boolean goalTest();// To test if we reached goal node
		public double getCost(); // To get the cost of the algorithm
		public double getHeuristics();
		public double priority();
	}
	// A little code taken from missionaries and cannibals problem with slight modifications
	public List<SearchNode> breadthFirstSearch() {
		resetStats();
		Queue<SearchNode> queue = new LinkedList<>();
		HashMap<SearchNode, SearchNode> prevNode = new HashMap<>(); // to store the nodes and to check conditions later.
		//System.out.println("startNode...("+startNode+")");
		//System.out.println("");
		prevNode.put(startNode, null);
		queue.add(startNode);
		//If queue has elements increase the node count.
		while (queue.isEmpty()==false) {
			incrementNodeCount();
			updateMemory(queue.size() + prevNode.size());
			SearchNode currentNode = queue.remove();
			if (currentNode.goalTest()) {
				//System.out.println("");
				//System.out.println("currentNode...:"+currentNode);
				//System.out.println("reachedFrom...:"+reachedFrom);
				return backchain(currentNode, prevNode);
			}
			ArrayList<SearchNode> listOfNodes = currentNode.getSuccessors();
			//System.out.println("listOfNodes " + successors);
			for (SearchNode node : listOfNodes) {
				// if not visited
				//System.out.println("node..."+node);
				//System.out.println("currentNode..."+currentNode);
				if (!prevNode.containsKey(node)) {
					prevNode.put(node, currentNode);
					queue.add(node);
				}
			}
		}

		return null;
	}
	// A little code taken from missionaries and cannibals problem with slight modifications
	public List<SearchNode> backchain(SearchNode node,HashMap<SearchNode, SearchNode> hm) {

		LinkedList<SearchNode> solution = new LinkedList<>();
		//List<SearchNode> solution = new LinkedList<>();
		/* If parent reference taken for storing child object we get path in the reverse order*/
		//System.out.println("hashmap:..."+hm);
		while (node != null) {
			solution.addFirst(node);
			node = hm.get(node);
			//solution.add(node);
		}
		return solution;
	}

	public List<SearchNode> depthFirstMemoizingSearch(int maxDepth) {
		resetStats();
		HashMap<SearchNode, Integer> visited = new HashMap<>();
		visited.put(startNode, 0);
		return dfsrm(startNode, visited, 0, maxDepth);
	}
	// A little code taken from missionaries and cannibals problem with slight modifications
	private List<SearchNode> dfsrm(SearchNode currentNode,HashMap<SearchNode, Integer> visited, int depth, int maxDepth) 
	{
		updateMemory(visited.size());
		incrementNodeCount();
		// System.out.println(currentNode);
		// System.out.println("visited size " + visited.size());
		if (currentNode.goalTest()) {
			LinkedList<SearchNode> path = new LinkedList<SearchNode>();
			path.add(currentNode);
			return path;
		}
		if (depth == maxDepth) {
			return null;
		}
		ArrayList<SearchNode> successors = currentNode.getSuccessors();
		for (SearchNode listOfNodes : successors) {
			if (!visited.containsKey(listOfNodes) || depth < visited.get(listOfNodes)) {
				visited.put(listOfNodes, depth);
				List<SearchNode> path = dfsrm(listOfNodes, visited, depth + 1, maxDepth);
				if (path != null) {
				path.add(0, currentNode);
				return path;
				}
			}
		}
		return null;
	}
	public List<SearchNode> IDSearch(int maxDepth) {
		resetStats();
		for (int i = 0; i <= maxDepth; i++) {
			resetStats();
			HashSet<SearchNode> currentPath = new HashSet<>();
			List<SearchNode> path = dfsrpc(startNode, currentPath, 0, i);
			if (path != null) {
				return path;
			}
		}
		return null;
	}

	public List<SearchNode> depthFirstPathCheckingSearch(int maxDepth) {
		resetStats();
		HashSet<SearchNode> currentPath = new HashSet<>();
		return dfsrpc(startNode, currentPath, 0, maxDepth);
	}
	// A little code taken from missionaries and cannibals problem with slight modifications
	private List<SearchNode> dfsrpc(SearchNode currentNode,HashSet<SearchNode> currentPath, int depth, int maxDepth) {
		
		currentPath.add(currentNode);
		// System.out.println("current path size: " + currentPath.size());
		updateMemory(currentPath.size());
		incrementNodeCount();
		if (currentNode.goalTest()) {
			LinkedList<SearchNode> path = new LinkedList<SearchNode>();
			path.add(currentNode);
			return path;
		}
		if (depth == maxDepth) {
			currentPath.remove(currentNode);
			return null;
		}
		ArrayList<SearchNode> listOfNodes = currentNode.getSuccessors();

		for (SearchNode node : listOfNodes) {

			if (!currentPath.contains(node)) {
				List<SearchNode> listOfNodes2 = dfsrpc(node, currentPath, depth + 1,maxDepth);
				if (listOfNodes2 != null) {
					listOfNodes2.add(0, currentNode);
					return listOfNodes2;
				}
			}
		}
		currentPath.remove(currentNode); 
		return null;
	}

	protected void resetStats() {
		nodesExplored = 0;
		maxMemory = 0;
	}

	protected void printStats() {
		System.out.println("  Nodes explored during search:  "
				+ nodesExplored);
		System.out.println("  Maximum space usage during search "
				+ maxMemory);
	}

	protected void updateMemory(int currentMemory) {
		//System.out.println("currentMemory:"+currentMemory);
		maxMemory = Math.max(currentMemory, maxMemory);
		//System.out.println("maxMemory:"+maxMemory);
	}

	protected void incrementNodeCount() {
		nodesExplored++;
		//System.out.println(nodesExplored);
	}
}
