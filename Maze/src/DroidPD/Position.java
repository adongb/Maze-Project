package DroidPD;

import mazePD.Maze.Content;

public class Position {
	
	boolean alreadyVisited;
	
	public boolean getVisited() {
		return this.alreadyVisited;
	}
	
	public void setVisited(boolean alreadyVisited) {
		this.alreadyVisited = alreadyVisited;
	}
	
	
	Content cell;
	
	public Content getContent() {
		return this.cell;
	}
	
	public void setContent(Content cell) {
		this.cell = cell;
	}
	
	public Position(Content cell, boolean alreadyVisited) {
		
		setContent(cell);
		setVisited(alreadyVisited);
		
	}

}
