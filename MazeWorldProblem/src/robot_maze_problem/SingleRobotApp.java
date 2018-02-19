/*
 * Author: Mahesh Devalla
 * Artificial Intelligence
 * Assignment: Robot Maze Problem
 */
package robot_maze_problem;
import java.util.List;
import robot_maze_problem.FindPath;
import robot_maze_problem.FindPath.SearchNode;

public class SingleRobotApp{
	
	static LoadMaze loadedMaze=LoadMaze.getMazeData("Maze.txt");
	
	
public static void main(String[] args){
	System.out.println("The loaded maze is: \n");
	System.out.println(loadedMaze);// Printing the maze for a clear understanding. 
		int x_cord_start = 3;
		int y_cord_start = 3;
		int x_cord_goal = 6;
		int y_cord_goal = 0;
		

		SingleRobot mazeProblem = new SingleRobot(loadedMaze, x_cord_start, y_cord_start, x_cord_goal,y_cord_goal);
		List<FindPath.SearchNode> path1;
		//System.out.println("");
		path1 = mazeProblem.breadthFirstSearch();
		//System.out.priznt(path);
		System.out.println("Breadth First Search:  ");
		mazeProblem.printStats();
		System.out.println("\n The path followed by Robot is as follows:");
		//path.forEach(System.out::println);
		try{
			for (FindPath.SearchNode node : path1) {
			    System.out.print("("+node+"),");
			}
			}
			catch(Exception e)
			{
				System.out.println("Path doesn't exist");
			}
		//for (SearchProblem.SearchNode i : path) map.put(1,i);
		//System.out.println(map);
		//System.out.println(path);
		List<SearchNode> path2 = mazeProblem.depthFirstPathCheckingSearch(5000);
		System.out.println("\n \n Depth First Search:  ");
		mazeProblem.printStats();
		System.out.println("\n The path followed by Robot is as follows:");
		try{
			for (FindPath.SearchNode element : path2) {
			    System.out.print("("+element+"),");
			}
			}
			catch(Exception e)
			{
				System.out.println("Path doesnot exist");
			}

		List<SearchNode> path3 = mazeProblem.aStar();
		System.out.println("\n \n AStar Search:");
		mazeProblem.printStats();
		System.out.println("\n The path followed by Robot is as follows:");
		try{
			for (FindPath.SearchNode element : path3) {
			    System.out.print("("+element+"),");
			}
			}
			catch(Exception e)
			{
				System.out.println("Path doesnot exist");
			}
	}	
}