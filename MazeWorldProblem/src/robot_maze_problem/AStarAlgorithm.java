/*
 * Author: Mahesh Devalla
 * Artificial Intelligence
 * Assignment: Robot Maze Problem
 * Dr. Devin Balcom 
 * Credits: Missionaries and Cannibals problem stubs for updating memory and nodes explored 
 */
package robot_maze_problem;
import java.util.*;

public class AStarAlgorithm extends FindPath {
	public List<SearchNode> aStar() 
	{		
		resetStats();
		PriorityQueue<FindPath.SearchNode> pq = new PriorityQueue<>();
		HashMap<FindPath.SearchNode,Double> prio = new HashMap<>();
		HashMap<FindPath.SearchNode, SearchNode> hm = new HashMap<>();
		SearchNode node;
		prio.put(startNode,startNode.priority());
		hm.put(startNode, null);
		pq.add(startNode);	
		while(!pq.isEmpty())
		{
			node = pq.poll();// If queue is empty then dequeue the node
			//System.out.println("node...:"+node);
			if(node.goalTest())// check if node reached the goal node or not here(6,0)
			{
				return backchain(node,hm);
			}
			else if(prio.containsKey(node) && (node.priority() > prio.get(node)))
			{
				continue; // Continuing till the goal node is found
			}

			incrementNodeCount();// While goal not found then increment node count.
			updateMemory(pq.size() + prio.size() + hm.size());// updating existing memory for printing later
			for(SearchNode nextNode: node.getSuccessors())
			{
				if(!prio.containsKey(nextNode) || nextNode.priority() < prio.get(nextNode))
				{
					prio.put(nextNode, nextNode.priority());
					hm.put(nextNode, node);
					pq.add(nextNode);
					
				}
			}

		}
		return null;
	}
}
