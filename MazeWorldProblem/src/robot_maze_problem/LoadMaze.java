/*
 * Author: Mahesh Devalla
 * Artificial Intelligence
 * Assignment: Robot Maze Problem
 */
package robot_maze_problem;

import java.util.List;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;



public class LoadMaze {
	
	public static int[] up = {0, 1};
	public static int[] down = {0, -1};
	public static int[] left = {-1, 0};
	public static int[] right = {1, 0};
	public int xaxisLength;
	public int yaxisLength;
	public char[][] mazeArray;
	public static int[] noMotion= {0,0};
	final static Charset charSet = StandardCharsets.UTF_8;
		
	public static void main(String args[]) {
		//LoadMaze loadMaze = LoadMaze.getMazeData("Maze.txt");
		//System.out.println(loadMaze);
		//System.out.println(loadMaze.isLegal(0,2));
	}

	public static LoadMaze getMazeData(String mazefile) {	
		LoadMaze loadedMaze = new LoadMaze();
		try {
			List<String> list = readFile(mazefile);
			//System.out.println("list.size()...:"+list.size());
			loadedMaze.yaxisLength = list.size();
			//System.out.println("yaxisLength...:"+m.yaxisLength);
			int y = 0;
			loadedMaze.mazeArray = new char[loadedMaze.yaxisLength][];
			for (String row : list) {
				//System.out.println(line.length()+":::"+line);
				loadedMaze.xaxisLength = row.length();
				//System.out.println("x_axis"+m.xaxisLength);
				loadedMaze.mazeArray[loadedMaze.yaxisLength - y - 1] = new char[loadedMaze.xaxisLength];
				for (int x = 0; x < row.length(); x++) {
					loadedMaze.mazeArray[loadedMaze.yaxisLength - y - 1][x] = row.charAt(x);
				}
				y++;
				//System.out.println(row.length());
				}
			return loadedMaze;
			} 
		catch (IOException E) {
			System.out.println("Please check if the maze has any problems");
			return null;
		}
	}

	//To get the character present in the Maze at specific coordinate
	public char getChar(int x_cord, int y_cord) {
		return mazeArray[y_cord][x_cord];
	}
	
	// To check if the next step of the robot in the maze consists of "." or "#"(valid or not). 
	public boolean isLegal(int x_cord, int y_cord) {
		if(x_cord >= 0 && x_cord < xaxisLength && y_cord >= 0 && y_cord < yaxisLength) {
			boolean result= getChar(x_cord, y_cord) == '.';
			return result;
		}
		return false;
	}
	
	@Override
	public String toString(){
		try{
		String mazestr = "";
		for (int y = xaxisLength-1; y >= 0; y--) {
			for (int x = 0 ; x < yaxisLength; x++) {
				mazestr += mazeArray[y][x];
			}
			mazestr += "\n";
		}
		return mazestr;
		}
		catch(Exception e)
		{
			System.out.println("Please check if the maze is in the valid format or not");
		}
		return null;
	}
	// predefined functions to get the data with new input output(nio) package from java 8
	private static List<String> readFile(String mazeName) throws IOException {
		Path path = Paths.get(mazeName);
		return Files.readAllLines(path, charSet);
	}
}
