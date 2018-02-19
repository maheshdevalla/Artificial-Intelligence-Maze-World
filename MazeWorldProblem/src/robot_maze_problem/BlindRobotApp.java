
/*
 * Author: Mahesh Devalla
 * Artificial Intelligence
 * Assignment: Robot Maze Problem
 */
package robot_maze_problem;
//import java.util.ArrayList;
import java.util.List;

import robot_maze_problem.FindPath.SearchNode;

public class BlindRobotApp{

   static LoadMaze loadedMaze=LoadMaze.getMazeData("Maze.txt");

    public static void main(String[] args) {
    	
    	System.out.println(loadedMaze);
        BlindRobot mazeProblem = new BlindRobot(loadedMaze, 3, 3,6,6);
        /*
       List<SearchNode> path1 = mazeProblem.breadthFirstSearch();
       mazeProblem.printStats();
       System.out.println("Path travelled by blind robot is as follows:");
       System.out.println(path1);*/
        
      /*List<SearchNode> path2 = mazeProblem.depthFirstPathCheckingSearch(5000);
      mazeProblem.printStats();
      System.out.println("Path travelled by blind robot is as follows:");
      System.out.println(path2);*/

      
      List<SearchNode> path3 = mazeProblem.aStar();
      mazeProblem.printStats();
      System.out.println("Path travelled by blind robot in A star is as follows:");
      System.out.println(path3);
        
    }
}