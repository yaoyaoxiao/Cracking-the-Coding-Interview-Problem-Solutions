/* 8.2 Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
The robot can only move in two directions, right and down, but certain cells are "off limits" such that
the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
the bottom right. */

ArrayList<Point> getPath(boolean[][] maze){
	if (maze == null || maze.length == 0) {
		return null;
	}
	ArrayList<Point> path = new ArrayList<Point>();
	HashSet<Point> visitedPoints = new HashSet<Point>();
	if (getPath(maze, maze.length-1, maze[0].length-1, path, visitedPoints)){
		return path;
	}
	return null;
}

boolean getPath(boolean[][] maze, int r, int c, ArrayList<Point> path, HashSet<Point> visitedPoints){
	if (r < 0 || c < 0 ||!maze[r][c]) { //if out of bounds or "off limit", return
		return false;
	}

	Point cur = new Point(r,c);
	if (visitedPoints.contains(cur)){ //if visited before, return
		return false;
	}

	boolean isStartPoint = (r == 0 && c == 0);
	// if there is a path from start to the current location, all this location
	if (isStartPoint || getPath(maze, r-1, c, path, visitedPoints) || getPath(maze, r, c-1, path, visitedPoints)){
		
		path.add(cur);
		return true;
	}
	visitedPoints.add(cur); //cache the visited points
	return false;
}

/*
1. Think about this problem recursively: if we want to get to (r,c), we need to get to (r-1,c) or (r,c-1) first. 

2. We would visit some point multiple times if we dont cache (O(2 ^(r+c))); Caching the visited points will reduce this to O(rc) since we will visit each cell just once.
*/