package UI;

import DroidPD.Droid;
import mazePD.Maze;
import mazePD.Maze.*;


public class DroidUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		Maze maze = new Maze(10,3,MazeMode.TEST);
		
		printMaze(maze);
		
		Droid droid = new Droid("Ado");
		
		droid.enterMaze(maze);
		
	}
	
	public static void printMaze(Maze maze){
		
		System.out.println("Maze Test");
		
		System.out.println("Maze - "+maze.toString());
		
		int levels = maze.getMazeDepth();
		
		for (int z=0;z<levels;z++)
		{
		  System.out.println("Level - "+z );
		  String[] mazeArray = maze.toStringLevel(z);
		  for (int y=0;y<10;y++)  
			  System.out.println(y+" "+mazeArray[y]);

		
	}

}
}
