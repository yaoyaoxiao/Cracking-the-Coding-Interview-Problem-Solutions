/* 8.12 Eight Queens: Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board
so that none of them share the same row, column, or diagonal. In this case, "diagonal" means all
diagonals, not just the two that bisect the board. */

/* put queens row by row, since each row is processed once, we dont need to check whether there are more than one queen at the same row, we only need to check whether there are queens sharing same colomn/diagnal. 

No need to store the board as a 8*8 matrix since ony row can have only one queen. So, we just need to use an array to save the column number for each row. */

ArrayList<Integer[]> arrangingQueens(){
	int gridSize = 8;
	ArrayList<Integer[]> result = new ArrayList<Integer[]>();
	Integer[] positions = new Integer[gridSize];
	arrangingQueens(0, positions, result);
	return result;
}

void arrangingQueens(int r, Integer[] positions, ArrayList<Integer[]> result){
	int gridSize = positions.length;
	if (r == gridSize) { //all the rows are processed
		result.add(positions.clone());
	}
	else{
		for (int c = 0; c < gridSize; c++){
			if (checkPoistion(r,c,positions)){
				positions[r] = c;
				arrangingQueens(r+1, positions, result);
			}
		}
	}
}

boolean checkPoistion(int r, int c, Integer[] positions){
	for (int r2 = 0; r2 < r; r2++){ //check if the colum is ocupied already
		int c2 = positions[r2];
		if (c == c2){
			return false;
		}
		int rowDistance = r - r2; //r is always larger than r2, no need to get abs
		int colDistance = Math.abs(c -c2);
		if (rowDistance == colDistance) { //if the distance between two rows equals to the distance between two columns, they are in the same diagonal.
			return false;
		}
	}
	return true;
}