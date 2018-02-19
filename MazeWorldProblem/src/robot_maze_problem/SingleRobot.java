/*
 * Author: Mahesh Devalla
 * Artificial Intelligence
 * Assignment: Robot Maze Problem
 */
package robot_maze_problem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SingleRobot extends AStarAlgorithm {
	
	private int x_Cord_Start, y_Cord_Start, x_Cord_Goal, y_Cord_Goal;
	private static int move[][] = {LoadMaze.up, LoadMaze.right, LoadMaze.down, LoadMaze.left}; 
	private LoadMaze loadedMaze;
	static HashMap<Integer,Integer> hm;
	
	public SingleRobot(LoadMaze m, int x1, int y1, int x2, int y2) {
		startNode = new SingleRobotNode(x1,y1, 0);
		setX_Cord_Start(x1);
		setY_Cord_Start(y1);
		x_Cord_Goal = x2;
		y_Cord_Goal = y2;
		loadedMaze= m;		
	}
		
	public int getX_Cord_Start() {
		return x_Cord_Start;
	}

	public void setX_Cord_Start(int x_Cord_Start) {
		this.x_Cord_Start = x_Cord_Start;
	}

	public int getY_Cord_Start() {
		return y_Cord_Start;
	}

	public void setY_Cord_Start(int y_Cord_Start) {
		this.y_Cord_Start = y_Cord_Start;
	}

	public class SingleRobotNode implements SearchNode {

		public int[] currentNodeArray; 
		private double cost;  
		public SingleRobotNode(int x, int y, double c) {
			this.cost = c;
			currentNodeArray = new int[2];
			this.currentNodeArray[0] = x;
			this.currentNodeArray[1] = y;
			//System.out.println(currentNodeArray[0]);
			//System.out.println(currentNodeArray[1]);	
		}
		
		public int getXCord() {
			return currentNodeArray[0];
		}
		
		public int getYCord() {
			return currentNodeArray[1];
		}

		@Override
		public ArrayList<SearchNode> getSuccessors() {

			ArrayList<SearchNode> listOfNodes = new ArrayList<>();
			//System.out.println("");
			for (int[] arr: move) {
				//System.out.println("arr[0]..."+arr[0]+"...arr[1]..."+arr[1]);
				int xNew = currentNodeArray[0] + arr[0];
				int yNew = currentNodeArray[1] + arr[1]; 	
				//System.out.println("testing next node \t " + xNew + " " + yNew);
				if(loadedMaze.isLegal(xNew, yNew)) {				
					 //System.out.println("" + " (" + xNew + ", " + yNew+")");
					//hm.put(xNew, yNew);
					//arr1.add(xNew);
					//arr2.add(yNew);
					 //System.out.print("");
					SearchNode node = new SingleRobotNode(xNew, yNew, getCost() + 1.0);
					//System.out.println("current_size"+listOfNodes.size());
					listOfNodes.add(node);
				}				
			}
			return listOfNodes;

		}
		
		@Override
		public double priority() {
			return getHeuristics() + getCost();
		}
		
		@Override
		public boolean equals(Object temp) {
			return Arrays.equals(currentNodeArray,((SingleRobotNode) temp).currentNodeArray);
		}
		
		@Override
		public double getHeuristics() {
			
			double rem_distnc_x = x_Cord_Goal - currentNodeArray[0];
			double rem_distnc_y = y_Cord_Goal - currentNodeArray[1];
			return Math.abs(2*Math.max(rem_distnc_x, rem_distnc_y));
		}
		
		@Override
		public int compareTo(SearchNode temp) {
			return (int) Math.signum(priority() - temp.priority());
		}	

		@Override
		public String toString() {
			return new String(currentNodeArray[0] + ", " + currentNodeArray[1] + " ");
		}

		@Override
		public int hashCode() {
			return currentNodeArray[0] * 100 + currentNodeArray[1]; 
		}	
		@Override
		public boolean goalTest()
		{
			return currentNodeArray[0] == x_Cord_Goal && currentNodeArray[1] == y_Cord_Goal;
		}		
		@Override
		public double getCost() {
			return cost;
		}
	}

}
