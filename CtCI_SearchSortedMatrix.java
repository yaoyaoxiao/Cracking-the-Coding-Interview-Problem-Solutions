/* 10.9 Sorted Matrix Search: Given an M x N matrix in which each row and each column is sorted in
ascending order, write a method to find an element.*/

 /*
A naive way to do this is to utilize the sorted rows and colums: start from a corner, upper right corner for example, then move left if too big, move down if too small.
 */

boolean searchSortedMatrix(int[][] matrix, int num){
	if (matrix == null || matrix[0] == null) {
		return false;
	}
	int row = 0;
	int col = matrix[0].length - 1;
	while (row < matrix.length && col >= 0){
		if (matrix[row][col] == num){
			System.out.println("row: " + row  + " column: " + col);
			return true;
		}
		if (matrix[row][col] < num){
			row++;
		}
		else{
			col--;
		}
	}
	return false;
}

/*
1. We can improve the above by using binary search. Based on the fact that for element at [r][c], any elements on its upper left side (row <= r-1 && col <= c -1) are smaller; and all the elements on its lower right side (row >= r+1 && col >= c+1) are larger. 
2. if we look along the diagnal, it is sorted. So we can binary search the target element and we will find a pair of (low, high) elements which help us to partition the grid into four quadrants. Then we recursively search the lower left and upper right quadrants.
*/

Coordinate searchSortedMatrix(int[][] matrix, int num){
	Coordinate start = new Coordinate(0,0);
	Coordinate end = new Coordinate(matrix.length-1, matrix[0].length-1);
	return searchSortedMatrix(matrix, num, start, end);
}

Coordinate searchSortedMatrix(int[][] matrix, int num, Coordinate start, Coordinate end){
	//check whether the coordinates are inbounds
	if (!start.inbounds(matrix) || !end.inbounds(matrix)){
		return null;
	}
	//check if the start coordinate matches or before the end coordinate
	if (matrix[start.row][start.column] == num){
		return start;
	}
	else if (!start.isBefore(end)){
		return null;
	}

	//since is might not be a square matrix, the end of the diagonal might not be the same as the end of the matrix
	Coordinate startPos = (Coordinate)start.clone();
	int distance = Math.min(end.row - start.row, end.column - start.column);
	Coordinate endPos = new Coordinate(start.row + distance, start.column + distance);
	Coordinate pivot =  new Coordinate(0,0);

	//binary search along the diagonal and find the first element > num
	while (startPos.isBefore(endPos)){
		pivot.setAverage(startPos, endPos);
		if (num > matrix[pivot.row][pivot.column]){
			startPos.row = pivot.row + 1;
			startPos.column = pivot.column + 1;
		}
		else{
			endPos.row = pivot.row - 1;
			endPos.column = pivot.column - 1;
		}
	}

	//partition the grid and search the lower left and the upper right corner recursively
	return partitionAndSearch(matrix, num, start, end, startPos); //note that we should use startPos here, not pivot. 
}

Coordinate partitionAndSearch(int[][]matrix, int num, Coordinate start, Coordinate end, Coordinate pivot){
	//find the start and end of the lower left corner and the upper right corner
	Coordinate lowerLeftStart = new Coordinate(pivot.row, start.column);
	Coordinate lowerLeftEnd = new Coordinate(end.row, pivot.column);
	Coordinate upperRightStart = new Coordinate(start.row, pivot.column);
	Coordinate upperRightEnd = new Coordinate(pivot.row, end.column);

	Coordinate result = searchSortedMatrix(matrix, num, lowerLeftStart, lowerLeftEnd);
	if (result == null){
		return searchSortedMatrix(matrix, num, upperRightStart, upperRightEnd);
	}
	return result;
}

public class Coordinate Implements Cloneable{
	public int row, column;
	public Coordinate(int r, int c){
		row = r;
		column = c;
	}
	public boolean inbounds(int[][] matrix){
		return (row >= 0 && row < matrix.length && column >= 0 && column < matrix[0].length);
	}

	public boolean isBefore(Coordinate pos){
		return row <= pos.row && column <= pos.column;
	}

	public Object clone(Coordinate pos){
		return new Coordinate(pos.row, pos.column);
	}

	public void setAverage(Coordinate low, Coordinate high){
		row = (low.row + high.row)/2;
		column = (low.column + high.column)/2;
	}
}