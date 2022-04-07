package DroidPD;

import StackPD.LinkedStack;

import StackPD.Stack;
import mazePD.Coordinates;
import mazePD.DroidInterface;
import mazePD.Maze;
import mazePD.Maze.Content;
import mazePD.Maze.Direction;

public class Droid implements DroidInterface { 

	String name;
	
	public Position[][][]map;
	
	public Droid(String name) {	
		setName(name);
	}
	
	public String getName() {return name;}
	
	public void setName(String name) {this.name = name;}

	public void enterMaze(Maze maze) {
		// TODO Auto-generated method stub
		
		map = new Position[maze.getMazeDim()][maze.getMazeDim()][maze.getMazeDepth()];
		
		
		LinkedStack<Coordinates> stack = new LinkedStack<Coordinates>();
		
		maze.enterMaze(this);
		
		Coordinates currentLocation = maze.getCurrentCoordinates(this);
		System.out.println("Starting location " + currentLocation);
		
		int x = currentLocation.getX();
		int y = currentLocation.getY();
		int z = currentLocation.getZ();
		
		map[x][y][z] = new Position(maze.scanCurLoc(this),true);
		stack.push(currentLocation);
		
		Content [] contents; 
		
		
		while (map[x][y][z].getContent()!= Content.END) {
			
			//scan around us in the maze 
			
			contents = maze.scanAdjLoc(this);
			
			//look through the scan info and update our map
			
			for(int i = 0; i < 4 ; i++) {
				
				if(i == 0) {
					if ((y-1 >= 0) && map[x][y-1][z] == null){
					map[x][y-1][z] = new Position(contents[i],false);
					}
				}
				if(i == 1) {
					if ((x+1 < maze.getMazeDim()) && map[x+1][y][z] == null){
						map[x+1][y][z] = new Position(contents[i],false);
						}
				}
				if(i == 2) {
					if ((y+1 < maze.getMazeDim()) && map[x][y+1][z] == null){
						map[x][y+1][z] = new Position(contents[i],false);
						}
				}
				if(i == 3) {
					if ((x-1 >= 0) && map[x-1][y][z] == null){
						map[x-1][y][z] = new Position(contents[i],false);
						}
				}
			}
	
			// pick a move
		
			boolean foundMove = false;
			
			 int i = 0;
			
			while (!foundMove && i < 4) {
				
				if(i == 0) {
					if ((y-1 >= 0) && !map[x][y-1][z].getVisited() && (map[x][y-1][z].getContent() == Content.EMPTY || map[x][y-1][z].getContent() == Content.END || map[x][y-1][z].getContent() == Content.PORTAL_DN)) {
					map[x][y-1][z].setVisited(true);
					maze.move(this, Direction.D00);
					currentLocation = maze.getCurrentCoordinates(this);
		
					
					 x = currentLocation.getX();
					 y = currentLocation.getY();
					 z = currentLocation.getZ();
					 foundMove = true;
					 stack.push(currentLocation);
					}
				}
					
				if(i == 1) {
					if ((x+1 < maze.getMazeDim()) && !map[x+1][y][z].getVisited() && (map[x+1][y][z].getContent() == Content.EMPTY || map[x+1][y][z].getContent() == Content.END || map[x+1][y][z].getContent() == Content.PORTAL_DN)){
						map[x+1][y][z].setVisited(true);
						maze.move(this, Direction.D90);
						
						currentLocation = maze.getCurrentCoordinates(this);
						
						
						 x = currentLocation.getX();
						 y = currentLocation.getY();
						 z = currentLocation.getZ();
						 foundMove = true;
						 stack.push(currentLocation);
					
					}
				}
					
				if(i == 2) {
					if ((y+1 < maze.getMazeDim()) && !map[x][y+1][z].getVisited() && (map[x][y+1][z].getContent() == Content.EMPTY || map[x][y+1][z].getContent() == Content.END || map[x][y+1][z].getContent() == Content.PORTAL_DN)){
						map[x][y+1][z].setVisited(true);
						maze.move(this, Direction.D180);
						currentLocation = maze.getCurrentCoordinates(this);
						
						
						 x = currentLocation.getX();
						 y = currentLocation.getY();
						 z = currentLocation.getZ();
						 foundMove = true;
						 stack.push(currentLocation);
						
						}
				}
				if(i == 3) {
					if ((x-1 >= 0) && !map[x-1][y][z].getVisited() && (map[x-1][y][z].getContent() == Content.EMPTY || map[x-1][y][z].getContent() == Content.END || map[x-1][y][z].getContent() == Content.PORTAL_DN)){
						map[x-1][y][z].setVisited(true);
						maze.move(this, Direction.D270);
						
						currentLocation = maze.getCurrentCoordinates(this);
						
						
						 x = currentLocation.getX();
						 y = currentLocation.getY();
						 z = currentLocation.getZ();
						 foundMove = true;
						 stack.push(currentLocation);
						}
					
				}
				i++;
			}
			
			if (!foundMove) {
			// if we don't find a move backup (pop stack, move to location on top of stack, set current location and xyz)
				stack.pop();
				
				Coordinates backupCoordinates = stack.top();
				
				
				if(backupCoordinates.getX() == currentLocation.getX()) {
					if (backupCoordinates.getY() > currentLocation.getY()) {
						maze.move(this, Direction.D180);
						
						currentLocation = maze.getCurrentCoordinates(this);
						
						
						 x = currentLocation.getX();
						 y = currentLocation.getY();
						 z = currentLocation.getZ();
						 foundMove = true;
						 
					}
					else {
					
						maze.move(this, Direction.D00);
						
						currentLocation = maze.getCurrentCoordinates(this);
						
						
						 x = currentLocation.getX();
						 y = currentLocation.getY();
						 z = currentLocation.getZ();
						 foundMove = true;
						
					}
				}
					
				if(!foundMove && backupCoordinates.getY() == currentLocation.getY()) {
					if (backupCoordinates.getX() > currentLocation.getX()) {
						maze.move(this, Direction.D90);
						
						currentLocation = maze.getCurrentCoordinates(this);
						
						
						 x = currentLocation.getX();
						 y = currentLocation.getY();
						 z = currentLocation.getZ();
						 foundMove = true;
						 
					}
					else {
					
						maze.move(this, Direction.D270);
						
						currentLocation = maze.getCurrentCoordinates(this);
						
						
						 x = currentLocation.getX();
						 y = currentLocation.getY();
						 z = currentLocation.getZ();
						 foundMove = true;
						
					}
				}
			
			}
			// test to see if were are at a Down Portal then move down update current location and xyz, make location as visited, push on stack
		
				if(map[x][y][z].getContent() == Content.PORTAL_DN) {
					
					maze.move(this, Direction.DN);
					currentLocation = maze.getCurrentCoordinates(this);
					
					
					 x = currentLocation.getX();
					 y = currentLocation.getY();
					 z = currentLocation.getZ();
					 
					 map[x][y][z] = new Position(maze.scanCurLoc(this),true);
					 stack.push(currentLocation);
					 	
				}
			System.out.println(currentLocation);

		}
		
		System.out.println("=================================");
		
		
		// Reverse Stack

		LinkedStack<Coordinates> temporaryStack = new LinkedStack<Coordinates>();
		
		while(stack.size() > 0) {
			
			temporaryStack.push(stack.top());
			stack.pop();
		} 
		
		while (temporaryStack.size() > 0) {
			
			Coordinates putback = temporaryStack.pop();
			
			System.out.println(putback);
			
			stack.push(putback);
			
		}
		
//		while(stack.size()>0) {
//		System.out.println(stack.top());
//		
//		reversedStack.push(stack.top());
//		
//		stack.pop();
//		}
		
			}
				
	}

	
// Reverse Stack

//LinkedStack<Coordinates> reversedStack = new LinkedStack<Coordinates>();
//
//while(stack.size() > 0) {
//	
//	reversedStack.push(stack.top());
//	stack.pop();
//} 
//
//while (reversedStack.size() > 0) {
//	
//	Coordinates putback = reversedStack.pop();
//	
//	System.out.println(putback);
//	
	//stack.push(putback);


// print out stack

//LinkedStack<Coordinates> s = null;
//for(int i = 0; i < s.size(); i++) {
//	
//	printSt
//	
//}

//public static void printStack(Stack<Coordinates> stack) {
//
//if(stack.isEmpty()) {
//	return;
//}
//
//Coordinates x = stack.top();
//
//stack.pop();
//
//printStack(stack);
//
//System.out.print(x);
//
//}

