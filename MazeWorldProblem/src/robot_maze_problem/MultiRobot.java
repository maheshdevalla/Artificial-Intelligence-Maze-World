/*
 * Author: Mahesh Devalla
 * Artificial Intelligence
 * Assignment: Robot Maze Problem
 */
package robot_maze_problem;

import java.util.ArrayList;
import java.util.Arrays;
public class MultiRobot extends AStarAlgorithm {

    private static int move[][] = {LoadMaze.up, LoadMaze.right, LoadMaze.down, LoadMaze.left, LoadMaze.noMotion};
    private LoadMaze loadedMaze;
    public int numOfRobots; 
    private int[] robotsEndLoc;
    public MultiRobot(LoadMaze loadedMaze, int[] robotsStartLoc, int[] robotsEndLoc, int currRobotMove)
    {
    	this.loadedMaze = loadedMaze;
        int[] currRobotsLoc = new int[robotsStartLoc.length+1];
        currRobotsLoc[robotsStartLoc.length] = currRobotMove;
        for(int i=0; i<robotsStartLoc.length; i++)
        	currRobotsLoc[i] = robotsStartLoc[i];   
        startNode = new MutliRobotNode(currRobotsLoc,0);
        this.robotsEndLoc = robotsEndLoc;
        this.numOfRobots = robotsStartLoc.length/2;// because each robot has two coordinates.  
        
    }

    public class MutliRobotNode implements SearchNode {

        public int[] currRobotLoc;
        private double cost;
        public MutliRobotNode(int[] currRobotLoc, double c) {
        	this.currRobotLoc = currRobotLoc;
            cost = c;
        }

        public int getnumOfRobotsX(int turn) {
            return currRobotLoc[2*turn];
        }

        public int getnumOfRobotsY(int turn) {
            return currRobotLoc[2*turn+1];
        }

        public int getcurrRobotMove(){
            return currRobotLoc[currRobotLoc.length-1];
        }

        public int getnumOfRobots(){
            return numOfRobots;
        }


        @Override
		public ArrayList<SearchNode> getSuccessors() {

            ArrayList<SearchNode> listOfNodes = new ArrayList<>();
            SearchNode node;
            for (int[] temp1: move) 
            {
                int[] temp2  = dupCurrRobotLoc(currRobotLoc);
                int value = currRobotLoc[currRobotLoc.length-1];     
                temp2[2*value] = currRobotLoc[2*value] + temp1[0];
                temp2[2*value+1] = currRobotLoc[2*value+1] + temp1[1];
                temp2[currRobotLoc.length-1] = (value+1)%numOfRobots;
                if(loadedMaze.isLegal(temp2[2*value], temp2[2*value+1]) && !collide(temp2, temp2[2*value], temp2[2*value+1]))
                {
                	node = new MutliRobotNode(temp2, getCost() + 1.0);
                    listOfNodes.add(node);
                }
            }
            return listOfNodes;
        }

        @Override
		public boolean goalTest() {
            for(int i=0; i<robotsEndLoc.length; i++){
                if(currRobotLoc[i] != robotsEndLoc[i])
                    return false;
            }
            return true;
        }

        @Override
        public boolean equals(Object temp) {
            return Arrays.equals(currRobotLoc, ((MutliRobotNode) temp).currRobotLoc);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(currRobotLoc);
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            for(int i=0; i<numOfRobots; i++)
                str.append("("+currRobotLoc[2*i]+","+currRobotLoc[2*i+1]+")");
            //str.append(" Robot moving currently...: "+currRobotLoc[currRobotLoc.length-1]);
            if(currRobotLoc[currRobotLoc.length-1]==0)
            {
            	str.insert(0," Robot A moves from:");
            }
            if(currRobotLoc[currRobotLoc.length-1]==1)
            {
            	str.insert(0," Robot B moves from:");
            }
            if(currRobotLoc[currRobotLoc.length-1]==2)
            {
            	str.insert(0," Robot C moves from:");
            }
            return str.toString();
        }

        @Override
		public double getHeuristics() {
        	double dist = 0;
            for(int i = 0; i<robotsEndLoc.length; i++)
            {
                dist += Math.abs(robotsEndLoc[i] - currRobotLoc[i]);
            }
            return dist;
        }
        
        @Override
		public double getCost() {
            return cost;
        }


     

        @Override
		public int compareTo(SearchNode temp) {

        	if(priority() > temp.priority())
                return 1;
            else if (priority() < temp.priority())
                return -1;
            else
            { 
                if(getHeuristics() > temp.getHeuristics())
                    return 1;
                else if(getHeuristics() < temp.getHeuristics())
                    return -1;
                else
                    return 0;
            }
        }

        @Override
		public double priority() {
            return getHeuristics() + getCost();
        }
        private boolean collide(int[] temp, int x, int y)
        {
            boolean res = false;
            for(int i = 0; i<numOfRobots; i++)
            {
                if(temp[2*i] == x && temp[2*i+1] == y) 
                {
                    if (res) 
                    	return true;
                    res = true;
                }
            }
            return false;
        }
        
        private int[] dupCurrRobotLoc(int[] temp)
        {
            int[] res = new int[temp.length];

            for(int i=0; i<temp.length; i++)
            	res[i] = temp[i];
            return res;
        }

    }

}
