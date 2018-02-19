/*
 * Author: Mahesh Devalla
 * Artificial Intelligence
 * Assignment: Robot Maze Problem
 */
package robot_maze_problem;
import java.util.List;

import robot_maze_problem.FindPath.SearchNode;

public class MultiRobotApp {

    static LoadMaze loadedMaze=LoadMaze.getMazeData("Maze.txt");
    
    public static void main(String[] args) throws Exception {
    	
    	System.out.println("The loaded maze is: \n");
    	System.out.println(loadedMaze);
    	int[] robotStartLoc = {0,0,1,6,4,5};
        int[] robotEndLoc = {6,1,6,2,6,0};
        int currRobotMove = 0;
        MultiRobot mazeProblem = new MultiRobot(loadedMaze, robotStartLoc, robotEndLoc,currRobotMove);     
        List<FindPath.SearchNode> path1 = mazeProblem.breadthFirstSearch();
       if(path1==null)
       {
    	   System.out.println("paths doesn't exist for breadthFirstSearch");
    	   
       }
       else
       {
    	   System.out.println("\n \n Breadth First Search:  ");
    	   mazeProblem.printStats();
    	   System.out.println("\n The path followed by Robot is as follows:");
    	   System.out.println(path1);
    	   
       }
        
        
        List<SearchNode> path2 = mazeProblem.depthFirstPathCheckingSearch(50);
        if(path2==null)
        {
     	   System.out.println("paths doesn't exist for depth First Search");
     	   
        }
        else
        {
     	   System.out.println("\n \n Depth First Search:  ");
     	   mazeProblem.printStats();
     	   System.out.println("\n The path followed by Robot is as follows:");
     	   System.out.println(path2);     	   
        }

        List<SearchNode> path3 = mazeProblem.aStar();
        if(path3==null)
        {
     	   System.out.println("paths doesn't exist for A Star Search");    	   
        }
        else
        {
     	   System.out.println("\n \n AStar Search:  ");
     	   mazeProblem.printStats();
     	   System.out.println("\n The path followed by Robot is as follows:");
     	   System.out.println(path3);   	   
        }
        
    }
}
